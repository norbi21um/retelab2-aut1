package hu.bme.aut.retelab2.service;

import hu.bme.aut.retelab2.dto.EmailRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class MailService {

    @Autowired
    private JavaMailSender emailSender;




    public void sendEmail(EmailRequest request){
        request.getSubscriptions().forEach(subscriptionDTO -> {
            log.info("Email sent to " + subscriptionDTO.getEmail());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("info@advert.com");
            message.setTo(subscriptionDTO.getEmail());
            message.setSubject("Ad alert");
            message.setText(
                    "Dear " + subscriptionDTO.getFistName() + " " + subscriptionDTO.getLastName() + "!\n\n" +
                            "The  " +
                            ""
            );
            this.emailSender.send(message);
        });
    }

}