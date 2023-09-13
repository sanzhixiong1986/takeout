## 第五-Redis

#### windows命令启动redis

启动服务器

进入redis文件夹然后看见有一个redis-server.exe的文件，然后使用

```
redis-server.exe redis.windows-config
```

启动redis服务器，客户端就是当前目录下 redis-cil 运行命令

```
redis-cil.exe 
```

直接就可以运行

#### 常用命令（字符串，哈希表，列表，集合，有序集合）

参考网址：https://www.runoob.com/redis/redis-strings.html

#### Spring data redis操作

##### 第一：maven坐标

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

##### 第二：配置文件进行配置

```yml
redis:
    host:localhost
    port:6379
    database:10
```

注意：database这个属性是redis数据库的编号，每一个数据库都是独立出来互相不干扰。如果配置password它会需要接受验证才能使用（这里我暂时没有用到password属性）

##### 第三：编写bean进行注入的代码编写

```java
package com.sky.config;

import com.sky.controller.admin.SetMealDishController;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class RedisConfiguration {
    private static final Logger log = LoggerFactory.getLogger(RedisConfiguration.class);
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        log.info("开始创建redis模板程序");
        RedisTemplate objectObjectRedisTemplate = new RedisTemplate();
        //设置链接工厂对象
        objectObjectRedisTemplate.setConnectionFactory(redisConnectionFactory);
        //色通知redis key序列化
        objectObjectRedisTemplate.setKeySerializer(new StringRedisSerializer());//注释了这句话以后redis图形工具会出现乱码
        return objectObjectRedisTemplate;
    }
}

```

##### 第四：使用方式

```java
redisTemplate.opsForValue().set(SHOP,status);
```

如果需要有一个服务器和客户端中间内存的数据可以使用redis进行存储操作。 .