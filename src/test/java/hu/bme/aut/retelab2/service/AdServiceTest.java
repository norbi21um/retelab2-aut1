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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class AdServiceTest {

    static final double MIN_VALUE = 0;

    static final double MAX_VALUE = 10000;

    static final String SESSION_TOKEN = "SDU642";

    @Autowired
    private AdRepository adRepository;


    @Autowired
    private AdService adService;

    @Autowired
    private AdMapper adMapper;

    @MockBean
    private SecretGenerator secretGenerator;

    private Ad createAd(){
        Ad ad = new Ad();
        ad.setTitle("Test data");
        ad.setPrice(100.0);
        ad.setSessionToken("asd");
        ad.setDateCreated(new Date(System.currentTimeMillis()));
        return ad;
    }

    @After
    public void after(){
        Mockito.reset(secretGenerator);
    }

    @Test
    public void test_createAd(){
        assertEquals(0, adRepository.count());
        Mockito.when(secretGenerator.generate()).thenReturn(SESSION_TOKEN);

        AdDTO response = adService.createAd(adMapper.mapToAdDTO(createAd()));
        assertEquals(1, adRepository.count());

        assertEquals("Test data", response.getTitle());
        assertEquals(100.0, response.getPrice(), 0.01);
        assertEquals(SESSION_TOKEN, response.getSessionToken());

        Mockito.verify(secretGenerator, Mockito.times(1)).generate();
    }

    @Test
    public void test_findAllAds(){
        assertEquals(0, adRepository.count());
        Ad ad1 = adRepository.save(createAd());

        Ad ad2 = createAd();
        ad2.setPrice(10001);
        ad2 = adRepository.save(ad2);

        Ad ad3 = createAd();
        ad3.setPrice(-1);
        ad3 = adRepository.save(ad3);

        assertEquals(3, adRepository.count());
        List<AdDTO> response = adService.findAllAds(MIN_VALUE, MAX_VALUE);

        assertEquals(1, response.size());
        assertEquals(100.0, response.get(0).getPrice(), 0.01);
        assertNull(response.get(0).getSessionToken());

        response.forEach(item -> {
            assertNull(item.getSessionToken());
        });
    }

    @Test
    public void test_updateAd_validSessionToken(){
        assertEquals(0, adRepository.count());
        Ad ad = adRepository.save(createAd());
        assertEquals(1, adRepository.count());
        ad.setTitle("New Title");
        AdDTO adDTO = adMapper.mapToAdDTO(ad);
        adDTO.setSessionToken(ad.getSessionToken());

        AdDTO response = adService.updateAd(adDTO);

        assertEquals("New Title", response.getTitle());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_updateAd_invalidSessionToken(){
        assertEquals(0, adRepository.count());
        Ad ad = adRepository.save(createAd());
        assertEquals(1, adRepository.count());
        ad.setId(432L);

        adService.updateAd(adMapper.mapToAdDTO(ad));
    }

    @Test
    public void test_findByTag_validRequest(){
        Ad ad1 = createAd();
        ad1.setTags(
                Arrays.asList("a","b")
        );
        ad1 = adRepository.save(ad1);

        Ad ad2 = createAd();
        ad2.setTags(
                Arrays.asList("b","c")
        );
        ad2 = adRepository.save(ad2);

        List<AdDTO> response = adService.findByTag("c");

        assertEquals(1, response.size());
        assertTrue(response.get(0).getTags().contains("c"));

    }

}
