/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.componentcorp.hibernate.multicache;

import java.util.Map;
import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;

/**
 *
 * @author Ross Lamont
 */
public class MultiCache implements Cache{

    

    public Object read(Object arg0) throws CacheException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object get(Object arg0) throws CacheException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void put(Object arg0, Object arg1) throws CacheException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(Object arg0, Object arg1) throws CacheException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void remove(Object arg0) throws CacheException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear() throws CacheException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void destroy() throws CacheException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void lock(Object arg0) throws CacheException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void unlock(Object arg0) throws CacheException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long nextTimestamp() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getTimeout() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getRegionName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getSizeInMemory() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getElementCountInMemory() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getElementCountOnDisk() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Map toMap() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
