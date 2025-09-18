package Practice.Project1.Services;

import Practice.Project1.entity.Item;
import Practice.Project1.repository.ItemRepository;
import Practice.Project1.repository.ItemRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RedisElasticCacheService {
    @Autowired
    ItemRepositoryImpl itemRepository;

    public Item getById(Integer id) {
        return itemRepository.findById(id);
    }

    public Map<Integer,Item> findAll() {
        return itemRepository.findAll();
    }

    public void save(Item item) {
        itemRepository.save(item);
    }
}
