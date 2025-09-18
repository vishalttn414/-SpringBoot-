package Practice.Project1.controller;

import Practice.Project1.Services.RedisElasticCacheService;
import Practice.Project1.Services.SqsSender;
import Practice.Project1.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    SqsSender sqsSender;

    @Autowired
    RedisElasticCacheService redisElasticCacheService;

    @GetMapping("/{id}")
    public Item getById(@PathVariable Integer id) {
        return redisElasticCacheService.getById(id);
    }

    @GetMapping("/all")
    public Map<Integer,Item> findAll() {
        return redisElasticCacheService.findAll();
    }

    @PostMapping
    public boolean create(@RequestBody Item item) {
        System.out.println("Creating item...");
        sqsSender.send(item);
        return true;
    }
}
