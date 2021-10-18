package com.zyf.springbootredisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RedissonConf {

    @Value("${spring.redis.redisson.config}")
    public String redissonConfig;

    @Bean(name="redissonClinet")
    public RedissonClient redissonClinet() throws IOException {
        Config config = Config.fromYAML(this.getClass().getClassLoader().getResource(redissonConfig));
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
