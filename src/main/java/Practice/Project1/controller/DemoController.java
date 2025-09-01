package Practice.Project1.controller;

import Practice.Project1.Services.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private final NotificationService notificationService;

    public DemoController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/trigger")
    public String triggerEmail() {
        notificationService.sendEmail();
        return "Request received! Email will be sent in background.";
    }
}
