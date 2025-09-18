//package Practice.Project1.config;
//
//import Practice.Project1.entity.Item;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
//@Configuration
//public class ElastiCacheRedisConfig {
//
//    @Value("${spring.redis.host}")
//    private String redisHost;
//
//    @Value("${spring.redis.port}")
//    private int redisPort;
//
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        RedisStandaloneConfiguration redisConfig =
//                new RedisStandaloneConfiguration(redisHost, redisPort);
//
//        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
//                .useSsl()
//                .build();
//
//        return new JedisConnectionFactory(redisConfig, clientConfig);
//    }
//
//    @Bean
//    public RedisTemplate<String, Item> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
//        RedisTemplate<String, Item> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory);
//        return template;
//    }
//
//    @Bean
//    public CacheManager cacheManager(JedisConnectionFactory jedisConnectionFactory) {
//        return RedisCacheManager.builder(jedisConnectionFactory)
//                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig())
//                .build();
//    }
//}
