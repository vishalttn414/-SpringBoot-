package Practice.Project1.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class UserDTO {

    @NotEmpty(message = "Name cannot be empty")
    public String name;

    @Email(message = "Please enter a Valid Email format")
    public String email;

    @Min(value = 18, message = "Age must be at least 18")
    public int age;
}
