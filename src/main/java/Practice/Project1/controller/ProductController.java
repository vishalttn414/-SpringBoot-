package Practice.Project1.controller;

import Practice.Project1.Services.ProductService;
import Practice.Project1.entity.Address;
import Practice.Project1.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/id/{id}")
    public Product getProductById(@PathVariable String id){
        return productService.find(id);
    }
    @PostMapping("/add")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PostMapping("/delete")
    public String deleteProductById(@RequestBody Product product) {
        return productService.deleteById(product.getId());
    }

    @GetMapping("/listAll")
    public List<Product> listAllProducts() {
        return productService.listAllProducts();
    }

    @PostMapping("/addAll")
    public List<Product> addAll(@RequestBody List<Product> productList) {
        return productService.saveAll(productList);
    }

    @PatchMapping("/id/{id}")
    public String update(@PathVariable String id, @RequestBody Map<String,Object> updates ) {
        return productService.updateProduct(id,updates);
    }
}

