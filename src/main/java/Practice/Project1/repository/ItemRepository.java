package Practice.Project1.repository;

import Practice.Project1.entity.Item;

import java.util.Map;

public interface ItemRepository {

    void save(Item item);
    Map<Integer, Item> findAll();
    Item findById(Integer id);
    void update(Item item);
    void delete(Integer id);
}
