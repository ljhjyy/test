package com.easygo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.config
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-08 11:20
 * @Description: SpringBoot整合SpringData Redis
 */
@Configuration
public class RedisConfig {

    //  StringRedisTemplate  这个是Jedis和SpringBoot整合的工具类对象

    /**
     * 1.创建JedisPoolConfig对象。在该对象中完成一些链接池配置
     * @ConfigurationProperties:会将前缀相同的内容创建一个实体。
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.redis.jedis.pool")
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        System.out.println("默认值最大空闲数：" + config.getMaxIdle());
        System.out.println("默认值最小空闲数：" + config.getMinIdle());
        System.out.println("默认值最大链接数：" + config.getMaxTotal());
        return config;
    }

    /**
     * 2.创建JedisConnectionFactory：配置redis链接信息
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig config) {

        System.out.println("配置完毕最大空闲数：" + config.getMaxIdle());
        System.out.println("配置完毕最小空闲数：" + config.getMinIdle());
        System.out.println("配置完毕最大连接数：" + config.getMaxTotal());

        JedisConnectionFactory factory = new JedisConnectionFactory();
        //关联链接池的配置对象
        factory.setPoolConfig(config);
        return factory;
    }

    /**
     * 3.创建RedisTemplate:用于执行Redis操作的方法
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory factory) {

        System.out.println("连接的地址是:"+factory.getHostName());
        System.out.println("连接的端口是:"+factory.getPort());

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //关联
        template.setConnectionFactory(factory);

        //为key设置序列化器
        template.setKeySerializer(new StringRedisSerializer());
        //为value设置序列化器
        template.setValueSerializer(new StringRedisSerializer());

        return template;
    }
}
