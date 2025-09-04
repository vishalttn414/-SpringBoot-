package Practice.Project1.Services;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class SqsQueueListener {
         private String sqsMessage;

//        @SqsListener("sqs-queue")  // 👈 use queue name, not full URL
//        public void listen(String message) {
//            System.out.println("Messgae " + message);
//            sqsMessage=message;
//        }

        public String getSqsMessage(){return sqsMessage;}
}


