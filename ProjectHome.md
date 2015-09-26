It can be useful to leverage different caching engines for different purposes.  For example, you might want to use a fast local cache for read only items (such as ehCache) but need to used a powerful distributed cache for your read-write objects.

This simple class allows you to simultaneously use multiple CacheProviders for different Cache Regions.