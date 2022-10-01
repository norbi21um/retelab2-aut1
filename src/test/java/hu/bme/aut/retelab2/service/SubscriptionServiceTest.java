package hu.bme.aut.retelab2.service;

import hu.bme.aut.retelab2.Application;
import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.dto.AdDTO;
import hu.bme.aut.retelab2.mapper.AdMapper;
import hu.bme.aut.retelab2.repository.AdRepository;
import hu.bme.aut.retelab2.service.helper.SecretGenerator;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class SubscriptionServiceTest {

    @Autowired
    private MailService mailService;

    @MockBean
    private JavaMailSender emailSender;

    @After
    public void after(){
        Mockito.reset(emailSender);
    }

    @Test
    public void test_subscribe(){

    }

    @Test
    public void test_sendEmails(){

    }

}
