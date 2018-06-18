package com.starryard.modular.cache.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.val;

@RestController
@RequestMapping("/cache")
public class CacheController {
	@Autowired
	private StringRedisTemplate stringRedis;
	
	@Autowired private RedisTemplate<String, Object> redis;
	
	@Autowired private ReactiveRedisTemplate<String, Object> reactRedis;
	
	private final Map<String,String> SUCCESS = new HashMap() {{put("msg","success");}};
	private final Map<String,String> FAIL = new HashMap() {{put("msg","fail");}};
	
	@GetMapping("/{opt}/{key}/{value}")
	public Object add(@PathVariable("opt") String opt, @PathVariable("key") String key,
			@PathVariable(name="value") String value) {
		Map<String,String> result = SUCCESS;
		switch (opt) {
		case "set":
			stringRedis.opsForValue().set(key, value);
			return new ResponseEntity<>(HttpStatus.OK);
		case "get":
			val cachedValue = stringRedis.opsForValue().get(key);
			return new HashMap() {{put(key,cachedValue);}};
		case "del":
			return new ResponseEntity<>(HttpStatus.OK);
		default:
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
