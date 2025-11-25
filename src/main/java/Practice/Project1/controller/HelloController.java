package Practice.Project1.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api")
public class HelloController {

    private MessageSource messageSource;

    HelloController(MessageSource messageSource){
        this.messageSource=messageSource;
    }
    @GetMapping("/hello")
    public String sayHello(@RequestParam String name) {
        Locale locale= LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
    }

    @PostMapping("/greet/{named}")
    public ResponseEntity<?> greet(@RequestBody GreetRequest req) {
        return ResponseEntity.ok("hi "+req.getName());
    }
}