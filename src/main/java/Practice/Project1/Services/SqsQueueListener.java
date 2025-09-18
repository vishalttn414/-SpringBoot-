package Practice.Project1.Services;

import Practice.Project1.entity.Item;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqsQueueListener {

    @Autowired
    private  RedisElasticCacheService redisElasticCacheService;

    @SqsListener("user-cache-details")
    public void userCacheListener(Item item) {
        System.out.println("Received Message for user..." + item.getName());
        try{
            redisElasticCacheService.save(item);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Save Message in Cache");
    }
}


