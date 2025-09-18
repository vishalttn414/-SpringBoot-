package Practice.Project1.repository;


import Practice.Project1.entity.Item;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ItemRepositoryImpl implements ItemRepository{

    private HashOperations hashOperations;
    private RedisTemplate<String, Item> redisTemplate;

    public ItemRepositoryImpl(RedisTemplate<String, Item> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Item user) {
        hashOperations.put("usercache", user.getId(), user);
    }

    @Override
    public Map<Integer, Item> findAll() {
        return hashOperations.entries("usercache");
    }

    @Override
    public Item findById(Integer id) {
        return (Item) hashOperations.get("usercache", id);
    }

    @Override
    public void update(Item user) {
        save(user);
    }

    @Override
    public void delete(Integer id) {
        hashOperations.delete("usercache", id);
    }
}
