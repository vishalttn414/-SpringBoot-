package Practice.Project1.entity;

import Practice.Project1.CustomAnnotation.MaskType;
import Practice.Project1.CustomAnnotation.PiiMask;
import Practice.Project1.CustomAnnotation.PiiMaskSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

    @PiiMask(type = MaskType.name)
    private String name;

    @PiiMask(type = MaskType.name)
    private String category;
    private double price;

    private Address address;
}
