package Practice.Project1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(@RequestParam String name) {
        return "Hello " + name;
    }
    @PostMapping("/greet/{named}")
    public ResponseEntity greet(@RequestBody GreetRequest req) {
        return ResponseEntity.ok("hi "+req.getName());
    }
}