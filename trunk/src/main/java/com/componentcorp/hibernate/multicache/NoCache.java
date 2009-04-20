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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;

/**
 *
 * @author Ross Lamont
 */
public class NoCache implements Cache{

    private String region;
    private static final Map EMPTY= Collections.unmodifiableMap(new HashMap());

    public NoCache(String region) {
        this.region = region;
    }



    public Object read(Object arg0) throws CacheException {
        return null;
    }

    public Object get(Object arg0) throws CacheException {
        return null;
    }

    public void put(Object arg0, Object arg1) throws CacheException {
        return ;
    }

    public void update(Object arg0, Object arg1) throws CacheException {
        return ;
    }

    public void remove(Object arg0) throws CacheException {
        return ;
    }

    public void clear() throws CacheException {
        return;
    }

    public void destroy() throws CacheException {
        return;
    }

    public void lock(Object arg0) throws CacheException {
        return;
    }

    public void unlock(Object arg0) throws CacheException {
        return;
    }

    public long nextTimestamp() {
        return System.currentTimeMillis();
    }

    public int getTimeout() {
        return 0;
    }

    public String getRegionName() {
        return region;
    }

    public long getSizeInMemory() {
        return 0;
    }

    public long getElementCountInMemory() {
        return 0;
    }

    public long getElementCountOnDisk() {
        return 0;
    }

    public Map toMap() {
        return EMPTY;
    }

}
