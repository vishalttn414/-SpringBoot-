package Practice.Project1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/calculator")
public class CalculatorController {

    @GetMapping("/add")
    public int add(@RequestParam int a,@RequestParam int b){
        return a+b;
    }

    @PostMapping("/multiply")
    public ResponseEntity multiply(@RequestBody MultHelper multHelper){
        return ResponseEntity.ok(multHelper.getProduct());
    }

}
