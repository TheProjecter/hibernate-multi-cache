/*
 * Copyright 2009 Component Corp Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.componentcorp.hibernate.multicache;

import java.util.Properties;
import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.NoCacheProvider;

/**
 *
 * @author Ross Lamont
 */
public class EmptyCacheProvider extends NoCacheProvider{

    @Override
    public Cache buildCache(String regionName, Properties properties) throws CacheException {
        return new NoCache(regionName);
    }

}
