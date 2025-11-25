package Practice.Project1.controller;

import Practice.Project1.Services.ProductService;
import Practice.Project1.entity.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EntityModel<Product>> getProductById(@PathVariable String id) {
        Product product = productService.find(id);
        EntityModel<Product> entityModel=EntityModel.of(product);
        WebMvcLinkBuilder links=WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).listAllProducts());
        entityModel.add(links.withRel("all-Products"));
        if(product==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(entityModel);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable String id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> products = productService.listAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/addAll")
    public ResponseEntity<List<Product>> addAll(@RequestBody List<Product> productList) {
        List<Product> savedProducts = productService.saveAll(productList);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProducts); // 201 with saved list
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody Map<String,Object> updates ) {
        String message = productService.updateProduct(id, updates);
        return ResponseEntity.ok(message);
    }
}

