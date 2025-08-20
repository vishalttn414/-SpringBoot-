package Practice.Project1.controller;

import Practice.Project1.Services.UserService;
import Practice.Project1.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "User Management", description = "Operations related to managing users")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a new user", description = "Provide user details in the request body")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user data provided")
    })
    @PostMapping
    public String createUser(
            @Parameter(description = "User object to be created", required = true)
            @RequestBody User user) {
        userService.saveUser(user);
        return "User saved!";
    }

    @Operation(summary = "Get a user by ID", description = "Provide an ID to look up a specific user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public User getUser(
            @Parameter(description = "ID of the user to fetch", required = true)
            @PathVariable String id) {
        return userService.getUser(id);
    }

    @Operation(summary = "Get all users", description = "Returns a list of all users in the system")
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Delete a user by ID", description = "Provide an ID to delete the user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/delete/{id}")
    public String deleteUser(
            @Parameter(description = "ID of the user to delete", required = true)
            @PathVariable String id) {
        return userService.deleteUser(id);
    }

    @Operation(summary = "Update an existing user", description = "Provide updated user details in the request body")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user data provided"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/update")
    public User updateUser(
            @Parameter(description = "Updated user object", required = true)
            @RequestBody User user) {
        return userService.updateUser(user);
    }
}
