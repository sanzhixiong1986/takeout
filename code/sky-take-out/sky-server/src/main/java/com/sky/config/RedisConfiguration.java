package com.sky.config;

import com.sky.controller.admin.SetMealDishController;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configurable
public class RedisConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RedisConfiguration.class);

    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){

        log.info("开始创建redis模板程序");

        RedisTemplate objectObjectRedisTemplate = new RedisTemplate<>();
        //设置链接工厂对象
        objectObjectRedisTemplate.setConnectionFactory(redisConnectionFactory);
        //色通知redis key序列化
        objectObjectRedisTemplate.setKeySerializer(new StringRedisSerializer());

        return objectObjectRedisTemplate;
    }
}
