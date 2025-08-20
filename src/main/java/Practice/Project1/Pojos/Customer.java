package Practice.Project1.Pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    // Required and optional fields
    private String name;
    private String email;
    private int age;
    private String phoneNumber;
}