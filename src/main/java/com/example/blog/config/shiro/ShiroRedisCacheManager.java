package com.example.blog.config.shiro;

import com.example.blog.util.StringTools;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.Collection;
import java.util.Set;

/**
 * @ClassName ShiroRedisCacheManager
 * @Author chenxue
 * @Description
 * @Date 2019/7/9 19:17
 **/
public class ShiroRedisCacheManager implements CacheManager {
    private RedisCacheManager cacheManager;

    public RedisCacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(RedisCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        if(StringTools.isEmpty(name)) {
            return null;
        }
        return new ShiroRedisCache<K,V>(name,cacheManager);
    }
}
class ShiroRedisCache<K,V> implements Cache<K,V> {
    private RedisCacheManager cacheManager;
    private org.springframework.cache.Cache cache;
    public ShiroRedisCache(String name, RedisCacheManager cacheManager){
        if(StringTools.isEmpty(name) || cacheManager == null){
            throw new IllegalArgumentException("cacheManager or CacheName cannot be null");
        }
        this.cacheManager = cacheManager;
        this.cache = cacheManager.getCache(name);
    }

    @Override
    public V get(K k) throws CacheException {
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
