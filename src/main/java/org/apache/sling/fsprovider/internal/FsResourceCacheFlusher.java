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
package org.apache.sling.fsprovider.internal;

import org.apache.sling.fsprovider.internal.parser.ContentFileCache;

public interface FsResourceCacheFlusher {

    /**
     * Flush the resoure path from each and every cache
     *
     * @param path Nullable resource path
     */
    void flush(final String path);

    /**
     * Flush the resoure path from each and every cache
     *
     * @param path    Nullable resource path
     * @param cacheId the cache identifier to delete this path from
     */
    void flush(final String path, final String cacheId);

    /**
     * Flush the resource path from each and every cache + attempt to recache the same exact resource
     *
     * @param path
     */
    void refresh(final String path);

    /**
     * Flush the resource path from each and every cache + attempt to recache the same exact resource
     *
     * @param path    Nullable resource path
     * @param cacheId the cache identifier to delete this path from
     */
    void refresh(final String path, final String cacheId);

    //TODO registering/unregistering should probably only be available to the class(es) using it
    /**
     * Register a new cache to by the specified key
     *
     * @param cacheId
     * @param cache
     */
    void registerCache(final String cacheId, final ContentFileCache cache);

    /**
     * Unregister a cache by the specified key
     *
     * @param cacheId
     */
    void unregisterCache(final String cacheId);
}
