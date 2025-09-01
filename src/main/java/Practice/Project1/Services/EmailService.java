package Practice.Project1.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;

    EmailService(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    public void sendEmail(String to,String body,String subject){
        try{
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setTo(to);
            simpleMailMessage.setText(body);

            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            log.error("An Exception has Occurred while sending email:  " + e);
        }
    }
}
