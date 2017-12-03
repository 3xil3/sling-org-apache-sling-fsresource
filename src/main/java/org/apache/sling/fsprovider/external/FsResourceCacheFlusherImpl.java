/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.fsprovider.external;

import org.apache.sling.fsprovider.internal.parser.ContentFileCache;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component(name = "org.apache.sling.fsprovider.internal.ContentFileCacheFlusher",
        service = FsResourceCacheFlusher.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "=Sling File System Resource Provider Cache Flusher",
                Constants.SERVICE_VENDOR + "=The Apache Software Foundation"
        })
public class FsResourceCacheFlusherImpl implements FsResourceCacheFlusher {

    private Map<String, ContentFileCache> cacheMap = Collections.synchronizedMap(new HashMap<String, ContentFileCache>());

    @Deactivate
    protected void deactivate() {
        this.cacheMap.clear();
        this.cacheMap = null;
    }

    @Override
    public void flush(final String path) {
        for (Map.Entry<String, ContentFileCache> cacheEntry : cacheMap.entrySet()) {
            cacheEntry.getValue().remove(path);
        }
    }

    @Override
    public void flush(final String path, final String cacheId) {
        if (cacheMap.containsKey(cacheId)) {
            cacheMap.get(cacheId).remove(path);
        }
    }

    @Override
    public void refresh(final String path) {
        for (Map.Entry<String, ContentFileCache> cacheEntry : cacheMap.entrySet()) {
            cacheEntry.getValue().refresh(path);
        }
    }

    @Override
    public void refresh(final String path, final String cacheId) {
        if (cacheMap.containsKey(cacheId)) {
            cacheMap.get(cacheId).refresh(path);
        }
    }

    @Override
    public void registerCache(final String cacheId, final ContentFileCache cache) {
        if (cacheId != null && !cacheId.trim().isEmpty() && cache != null) {
            cacheMap.put(cacheId, cache);
        }
    }

    @Override
    public void unregisterCache(final String cacheId) {
        if (cacheId != null && !cacheId.trim().isEmpty()) {
            cacheMap.remove(cacheId);
        }
    }
}
