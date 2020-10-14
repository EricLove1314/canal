package com.fanxuankai.canal.redis.config;

import com.fanxuankai.canal.core.constants.Constants;
import com.fanxuankai.canal.redis.CanalRedisWorker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author fanxuankai
 */
@ConditionalOnProperty(prefix = Constants.PREFIX + ".configuration", name = "enabled", havingValue = "true",
        matchIfMissing = true)
@EnableConfigurationProperties(CanalRedisProperties.class)
public class CanalRedisAutoConfiguration {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Bean
    public CanalRedisWorker canalRedisWorker(CanalRedisProperties canalRedisProperties) {
        return CanalRedisWorker.newCanalWorker(canalRedisProperties.getConfiguration(),
                canalRedisProperties.getRedisConfiguration(), redisTemplate);
    }

}