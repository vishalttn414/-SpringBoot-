package Practice.Project1.Services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    private final RedisTemplate redisTemplate;

    RedisService(RedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }
     public <T> T get(String key,Class<T> entityClass){
        try{
            Object object=redisTemplate.opsForValue().get(key);
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            if(object==null) return null;
            return objectMapper.readValue(object.toString(),entityClass);
        } catch (Exception e) {
            log.error("An Error has occurred in RedisService get method  "+e);
            return null;
        }
     }

    public void set(String key,Object o,long expiryTime){
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String jsonValue=objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key,jsonValue,expiryTime, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("An Error has occurred in RedisService set method  " + e);
        }
    }

}
