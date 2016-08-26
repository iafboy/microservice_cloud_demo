package io.pivotal.microservices.accounts.cache;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import groovyjarjarantlr.collections.List;

@SuppressWarnings("unchecked")
@Component
public class RedisManager {
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}


	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}


	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}


	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}


	public Object get(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}
	
	public Map getByKeyPattern(final String pattern){
		Map result=null;
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if(keys.size()>0){
			result=Maps.newHashMap();
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			for(Serializable key:keys){
				result.put(key, operations.get(key));
			}
		}
		return result;
	}

	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}