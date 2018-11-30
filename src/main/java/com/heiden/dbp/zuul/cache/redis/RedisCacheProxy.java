package com.heiden.dbp.zuul.cache.redis;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * redis cache的代理类，目的是有一个统一的设置缓存的入口 可以通过这个类进行权限设置
 *
 * @author heiden
 */
@Service
public class RedisCacheProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheProxy.class);

    @Resource
    private RedisTemplate redisTemplate;

    public boolean hasKey(Serializable key) {
        try {
            if (redisTemplate != null) {
                return redisTemplate.hasKey(key);
            }
            LOGGER.debug("redisTemplate is null");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从缓存中获取List对象
     *
     * @param key 键值
     * @return 缓存中的数据
     */
    @SuppressWarnings("rawtypes")
    public List getList(Serializable key) {
        try {
            if (redisTemplate != null && redisTemplate.hasKey(key)) {
                BoundListOperations<Serializable, Serializable> operations = redisTemplate.boundListOps(key);
                List result = operations.range(0, operations.size());
                return result;
            }
            LOGGER.debug("redisTemplate is null or miss key = {}", key);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 向List中添加数据
     *
     * @param key     缓存中的key
     * @param val     需要缓存的对象
     * @param timeout 超时时间，单位为妙,数值大于0时有效，当timeout小于等于0时，表示没有设置失效时间
     */
    public <T extends Serializable> void addToList(Serializable key, List<T> val, long timeout) {
        try {
            BoundListOperations<Serializable, Serializable> operations = redisTemplate.boundListOps(key);
            operations.rightPushAll(val.toArray(new Serializable[0]));
            if (timeout > 0) {
                operations.expire(timeout, TimeUnit.SECONDS);
            }
            LOGGER.debug("redisTemplate is null or miss key = {}", key);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据key，在缓存中将对象删除
     *
     * @param key 缓存中对象的key值
     */
    public void remove(Serializable key) {
        try {
            if (redisTemplate != null && redisTemplate.hasKey(key)) {
                redisTemplate.delete(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置过期时间
     *
     * @param key     缓存中对象的key值
     * @param timeout 过期时间,单位为秒
     */
    public void expire(Serializable key, long timeout) {
        try {
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取缓存的值
     *
     * @param key 对应的key
     * @return 返回缓存的对象
     */
    public Object getValue(Serializable key) {
        try {
            if (redisTemplate != null && redisTemplate.hasKey(key)) {
                BoundValueOperations<Serializable, Serializable> boundValueOperations = redisTemplate
                        .boundValueOps(key);
                return boundValueOperations.get();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 设置缓存中的对象
     *
     * @param key     对应的缓存的对象的key
     * @param value   缓存对象
     * @param timeout 设置过期
     */
    public void setValue(Serializable key, Serializable value, long timeout) {
        try {
            BoundValueOperations<Serializable, Serializable> boundValueOperations = redisTemplate.boundValueOps(key);
            boundValueOperations.set(value, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置缓存中的对象
     *
     * @param key   对应的缓存的对象的key
     * @param value 缓存对象
     */
    public void setValue(Serializable key, Serializable value) {
        try {
            BoundValueOperations<Serializable, Serializable> boundValueOperations = redisTemplate.boundValueOps(key);
            boundValueOperations.set(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对key进行模糊查询
     *
     * @param key 缓存中对象的key值
     */
    public Set keys(Serializable key) {
        try {
            if (redisTemplate != null) {
                Set set = redisTemplate.keys(key);
                return set;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashSet();
    }


    public void increment(Serializable key,Integer subtrahend){
        try {
            if (redisTemplate != null) {
                BoundValueOperations<Serializable, Serializable> boundValueOperations = redisTemplate.boundValueOps(key);
                boundValueOperations.increment(subtrahend);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void increment(Serializable key,Integer subtrahend,long timeout){
        try {
            if (redisTemplate != null) {
                BoundValueOperations<Serializable, Serializable> boundValueOperations = redisTemplate.boundValueOps(key);
                boundValueOperations.increment(subtrahend);
                expire( key,timeout);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
