package Practice.Project1.controller;

import Practice.Project1.Services.SqsQueueListener;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sqs")
public class SqsController {

    private final SqsTemplate sqsTemplate;
    private final SqsQueueListener sqsQueueListener;

    public SqsController(SqsTemplate sqsTemplate,SqsQueueListener sqsQueueListener) {
        this.sqsTemplate = sqsTemplate;
        this.sqsQueueListener=sqsQueueListener;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        sqsTemplate.send("https://sqs.ap-south-1.amazonaws.com/863518444862/sqs-queue",
                MessageBuilder.withPayload(message).build());
        return "Message sent: " + message;
    }

//    @GetMapping("/getmessage")
//    public String sendMessage() {
//        return sqsQueueListener.getSqsMessage();
//    }
//
    @SqsListener("sqs-queue")
    public void loadMessage(String message){
        System.out.println("Message from queue: " + message);
    }
}

