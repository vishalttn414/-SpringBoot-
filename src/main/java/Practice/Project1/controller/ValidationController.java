package Practice.Project1.controller;

import Practice.Project1.Services.DemoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
public class ValidationController {
    @Autowired
    private DemoService demoService1;

    @Autowired
    private DemoService demoService2;

    @GetMapping("/service")
    public String getService(){
        return "Service 1"+ demoService1.getScopeInfo()+" \n"+"Service 2"+demoService2.getScopeInfo();
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok("User created");
    }
}
