package hu.bme.aut.retelab2.service;

import hu.bme.aut.retelab2.dto.SendMailRequest;
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




    public void sendEmail(String to, String firstName, String lastName){
        log.info("Email sent to " + to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("info@advert.com");
        message.setTo(to);
        message.setSubject("Ad alert");
        message.setText(
                "Dear " + firstName + "!\n\n" +
                        "The  " +
                        ""
        );
        this.emailSender.send(message);
    }

}