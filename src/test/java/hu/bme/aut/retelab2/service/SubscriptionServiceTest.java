package hu.bme.aut.retelab2.service;

import hu.bme.aut.retelab2.Application;
import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.Subscription;
import hu.bme.aut.retelab2.dto.AdDTO;
import hu.bme.aut.retelab2.dto.NotifySubscribersRequest;
import hu.bme.aut.retelab2.dto.SubscriptionDTO;
import hu.bme.aut.retelab2.mapper.AdMapper;
import hu.bme.aut.retelab2.mapper.SubscriptionMapper;
import hu.bme.aut.retelab2.repository.AdRepository;
import hu.bme.aut.retelab2.repository.SubscriptionRepository;
import hu.bme.aut.retelab2.service.helper.SecretGenerator;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
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
    private SubscriptionService subscriptionService;

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private AdMapper adMapper;

    @MockBean
    private JavaMailSender emailSender;


    @After
    public void after(){
        Mockito.reset(emailSender);
    }

    private Subscription createSubsciption(Ad ad){
        return Subscription.builder()
                .fistName("Elek")
                .lastName("Teszt")
                .email("elek.teszt@gmail.com")
                .ad(ad)
                .build();
    }

    private Ad createAd(){
        Ad ad = new Ad();
        ad.setTitle("Test data");
        ad.setPrice(100.0);
        ad.setSessionToken("asd");
        ad.setDateCreated(new Date(System.currentTimeMillis()));
        return ad;
    }

    private Ad createAndSaveAd(){
        return adRepository.save(createAd());
    }

    @Test
    public void test_subscribe(){
        assertEquals(0, adRepository.count());
        Ad ad = createAndSaveAd();
        assertEquals(1, adRepository.count());

        assertEquals(0, subscriptionRepository.count());
        SubscriptionDTO response = subscriptionService.subscribe(
                subscriptionMapper.mapToSubscriptionDTO(createSubsciption(ad))
        );

        assertEquals(1, subscriptionRepository.count());
        assertEquals("Elek", response.getFistName());
        assertEquals("Teszt", response.getLastName());
        assertEquals("elek.teszt@gmail.com", response.getEmail());
        assertEquals(ad.getId(), response.getAdId());
    }

    @Test
    public void test_sendEmails(){
        Mockito.doNothing().when(emailSender).send(Mockito.any(SimpleMailMessage.class));

        assertEquals(0, adRepository.count());
        Ad ad = createAndSaveAd();
        assertEquals(1, adRepository.count());

        subscriptionRepository.save(createSubsciption(ad));

        subscriptionService.notifySubscibers(
                NotifySubscribersRequest.builder()
                        .item(adMapper.mapToAdDTO(ad))
                        .build()
        );

        Mockito.verify(emailSender, Mockito.times(1)).send(Mockito.any(SimpleMailMessage.class));
    }

}
