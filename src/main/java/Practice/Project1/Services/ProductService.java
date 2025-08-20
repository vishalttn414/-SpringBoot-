package Practice.Project1.Services;
import Practice.Project1.entity.Product;
import Practice.Project1.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private static final Logger logger= LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    private final MongoTemplate mongoTemplate;

    ProductService(ProductRepository productRepository,MongoTemplate mongoTemplate){
        this.productRepository=productRepository;
        this.mongoTemplate=mongoTemplate;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product find(String id){
        return productRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Product not found with given id: " + id));

    }
    public String deleteById(String id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return "The Product with the given id was deleted";
        }else{
            return "No Product was found with the given id";
        }
    }

    public List<Product> listAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> saveAll(List<Product> productList){
        return productRepository.saveAll(productList);
    }

    public String updateProduct(String id,Map<String,Object> updates){
        Query query = new Query(Criteria.where("id").is(id));
        Update update=new Update();
        logger.info("updates map is:   "+ updates);
        updates.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .forEach(entry -> update.set(entry.getKey(), entry.getValue()));
        mongoTemplate.updateFirst(query, update, Product.class);
        return "Product Updated SuccessFully";
    }
}

