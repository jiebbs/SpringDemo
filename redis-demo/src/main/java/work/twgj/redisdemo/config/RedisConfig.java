package work.twgj.redisdemo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


/**
 * @author weijie.zhu
 * @date 2023/9/27 15:13
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport{

    /**
     * 自定义缓存key生成策略
     * */
    @Bean
    @Override
    public KeyGenerator keyGenerator(){
        return (Object target, Method method, Object... params) ->{
            StringBuffer sb = new StringBuffer();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for(Object param:params){
                sb.append(param.toString());
            }
            return sb.toString();
        };
    }

    /**
     * 缓存管理器
     * */
    @Primary
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(this.cacheConfiguration(Duration.ofMinutes(5)))
                .withInitialCacheConfigurations(this.cacheConfigurationMap())
                .build();
        return cacheManager;
    }

    /**
     * redis缓存管理器配置列表；
     * 可以根据业务需要配置不同的过期时间；
     */
    private Map<String, RedisCacheConfiguration> cacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>(1);
        return configurationMap;
    }


    /**
     * redis缓存管理器的默认配置；
     * 使用fastJson序列化value,model不再需要实现Serializable接口；
     *
     * @param ttl 设置默认的过期时间，防止 redis 内存泄漏
     */
    private RedisCacheConfiguration cacheConfiguration(Duration ttl) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration
                .defaultCacheConfig()
                .computePrefixWith(cacheName -> "redisdemo".concat(":").concat(cacheName).concat(":"))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(ttl);
        return configuration;
    }
}
