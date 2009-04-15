/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.componentcorp.hibernate.multicache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;
import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.CacheProvider;
import org.hibernate.cache.NoCacheProvider;

/**
* Configures an instance of {@link MultiCache} to wrap another second level cache in Hibernate.
* To use set the hibernate property <i>hibernate.cache.provider_class</i> to the name of this class.
* <p/>
* This cache provider is typically used to get the best out of one cache (typically EHCache) for read only
* caching and use another cache (eg Memcached) for read write work.
* <p/>
* The following cache wide properties are used.
* <p/>
* <b>Cache wide properties</b>
* <table border='1'>
* <tr><th>Property</th><th>Default</th><th>Description</th></tr>
* <tr><td>hibernate.multicache.provider.keys</td><td></td>
* <td>Space delimited list of the key names of wrapped hibernate cache providers.</td></tr>
* <tr><td>hibernate.multicache.default</td><td>org.hibernate.cache.NoCacheProvider</td>
* <td>The class of the default provider.</td></tr>
* </table>
* <p/>
* <b>Cache region properties</b><br/>
* You can override the default cache provider on a per region buffer to get the best out of multiple caches.<br/>
* <table border='1'>
* <tr><th>Property</th><th>Default</th><th>Description</th></tr>
* <tr><td>hibernate.multicache.[region-name].provider</td>
* <td></td>
* <td>Set to the name of a subordinate cache provider to use for this region.</td></tr>
* <tr><td>hibernate.multicache.[region-name].region-filter</td>
* <td></td>
* <td>A regular expression used to identify regions served by this provider.  Regular
* expressions are evaluated in the order of the keys in hibernate.multicache.provider.keys</td></tr>
* </table>
*
* @author Ross Lamont
*/
public class MultiCacheProvider implements CacheProvider{

    private List<RegionFilter> subordinates;
    private CacheProvider defaultProvider;
    private static final String CONFIG_PROVIDERS_KEY = "hibernate.multicache.keys";
    private static final String CONFIG_DEFAULT_KEY = "hibernate.multicache.default";
    private static final String CONFIG_PREFIX = "hibernate.multicache.providers.";
    private static final String CONFIG_PROVIDER_SUFFIX = ".provider";
    private static final String CONFIG_REGION_FILTER_SUFFIX = ".region-filter";
    public Cache buildCache(String regionName, Properties properties) throws CacheException {
        for(RegionFilter filter: subordinates){
            if (filter.getPattern().matcher(regionName).matches()){
                return filter.getProvider().buildCache(regionName, properties);
            }
        }
        return defaultProvider.buildCache(regionName, properties);
    }

    public long nextTimestamp() {
        return defaultProvider.nextTimestamp();
    }

    public void start(Properties properties) throws CacheException {
        String currentKey="default";
        try {
            String defProvider = properties.getProperty(CONFIG_DEFAULT_KEY);
            if (defProvider !=null){
                Class<CacheProvider> providerClass = (Class<CacheProvider>) Class.forName(defProvider);
                defaultProvider = providerClass.newInstance();

            }
            else{
                defaultProvider=new NoCacheProvider();
            }
            subordinates=new ArrayList< RegionFilter>();
            String[] providerKeys = properties.getProperty(CONFIG_PROVIDERS_KEY, "").split(" ");
            if (providerKeys.length<1){
                throw new CacheException("Config parameter "+CONFIG_PROVIDERS_KEY+" must be provided!");
            }
            for (String key: providerKeys){
                if (key.length()<1){
                    throw new CacheException("Zero length key in "+CONFIG_PROVIDERS_KEY+" property.");
                }
                currentKey = key;
                String provider = properties.getProperty(CONFIG_PREFIX+key+CONFIG_PROVIDER_SUFFIX);
                if (provider == null){
                    throw new CacheException("Unable to find provider for multi-cache provider key: "+key);
                }
                String pattern = properties.getProperty(CONFIG_PREFIX+key+CONFIG_REGION_FILTER_SUFFIX);
                if (pattern == null){
                    throw new CacheException("Unable to find region-filter for multi-cache provider key: "+key);
                }
                Class<CacheProvider> providerClass = (Class<CacheProvider>) Class.forName(provider);
                CacheProvider p = providerClass.newInstance();
                p.start(properties);
                Pattern pat = Pattern.compile(pattern);
                subordinates.add(new RegionFilter(pat, p));
            }
        } catch (InstantiationException ex) {
            throw new CacheException("Unable to load Cache provider "+currentKey+":",ex);
        } catch (IllegalAccessException ex) {
            throw new CacheException("Unable to load Cache provider:",ex);
        } catch (ClassNotFoundException ex) {
            throw new CacheException("Unable to load Cache provider:",ex);
        }
    }

    public void stop() {
        while(!subordinates.isEmpty()){
            RegionFilter f = subordinates.remove(subordinates.size()-1);
            f.getProvider().stop();
        }
    }

    public boolean isMinimalPutsEnabledByDefault() {
        return false;
    }

}

class RegionFilter{
    private Pattern p;
    private CacheProvider prov;
    RegionFilter(Pattern p, CacheProvider prov){
        this.p=p;
        this.prov=prov;
    }

    public Pattern getPattern() {
        return p;
    }

    public CacheProvider getProvider() {
        return prov;
    }


}