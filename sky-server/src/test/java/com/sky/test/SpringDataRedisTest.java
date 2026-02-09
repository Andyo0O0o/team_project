package com.sky.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class SpringDataRedisTest {

     @Autowired
     private RedisTemplate redisTemplate;

     @Test
     public void testRedisTemplate(){
          System.out.println(redisTemplate);
          ValueOperations valueOperations = redisTemplate.opsForValue();
          HashOperations hashOperations = redisTemplate.opsForHash();
          ListOperations listOperations = redisTemplate.opsForList();
          SetOperations setOperations = redisTemplate.opsForSet();
          ZSetOperations zSetOperations = redisTemplate.opsForZSet();
     }

     /**
      * 操作字符串类型的数据
      */
     @Test
     public void testString(){
          //set get setex setnx
          //set
          redisTemplate.opsForValue().set("name","zhangsan");
          //get
          String name = (String) redisTemplate.opsForValue().get("name");
          System.out.println(name);
          //setex 设置过期时间
          redisTemplate.opsForValue().set("code","1234",20, TimeUnit.SECONDS);
          //setnx 如果不存在则设置
          redisTemplate.opsForValue().setIfAbsent("name","lisi");
          name = (String) redisTemplate.opsForValue().get("name");
          System.out.println(name);
     }

     @Test
     public void testHash(){
          //hset hget hdel hkeys hvals
          //hset
          HashOperations hashOperations = redisTemplate.opsForHash();
          hashOperations.put("user","name","zhangsan");
          hashOperations.put("user","age","20");
          //hget
          String name = (String) hashOperations.get("user","name");
          System.out.println(name);
          //hkeys
          Set keys = hashOperations.keys("user");
          //hvals
          List values = hashOperations.values("user");
          //hdel
          hashOperations.delete("user","age");
     }


}
