package Practice.Project1.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Async
    public void sendEmail(){
        try{
            Thread.sleep(4000);
            System.out.println("Email sent from a Background Thread");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
