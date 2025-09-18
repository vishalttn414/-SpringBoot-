package Practice.Project1.controller;

import Practice.Project1.Services.EventService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final EventService publisherService;

    public OrderController(EventService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/{orderId}")
    public String placeOrder(@PathVariable String orderId) {
        publisherService.publishOrderPlacedEvent(orderId);
        return "Order placed and event sent: " + orderId;
    }
}
