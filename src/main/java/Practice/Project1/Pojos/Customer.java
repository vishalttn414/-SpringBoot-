package Practice.Project1.Pojos;

import lombok.*;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    private String name;
    private String email;
    private int age;
    private String phoneNumber;
}