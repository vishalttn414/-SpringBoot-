package Practice.Project1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
@AllArgsConstructor
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private Address address;
}
