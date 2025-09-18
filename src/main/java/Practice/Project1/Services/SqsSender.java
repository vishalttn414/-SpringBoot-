package Practice.Project1.Services;

import Practice.Project1.entity.Item;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SqsSender {

    private final SqsTemplate sqsTemplate;

    public SqsSender(SqsTemplate sqsTemplate) {
        this.sqsTemplate = sqsTemplate;
    }

    public void send(Item item) {
        System.out.println("Sending user to SQS... " + item.getName());
        sqsTemplate.send("https://sqs.ap-south-1.amazonaws.com/863518444862/user-cache-details",
                MessageBuilder.withPayload(item).build());
    }
}
