package hu.bme.aut.retelab2.service;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.dto.AdDTO;
import hu.bme.aut.retelab2.dto.NotifySubscribersRequest;
import hu.bme.aut.retelab2.mapper.AdMapper;
import hu.bme.aut.retelab2.repository.AdRepository;
import hu.bme.aut.retelab2.service.helper.SecretGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class AdService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private SecretGenerator secretGenerator;

    @Autowired
    private AdMapper adMapper;

    @Autowired
    private SubscriptionService subscriptionService;

    public AdDTO createAd(AdDTO ad){
        String sessionToken = secretGenerator.generate();
        ad.setSessionToken(sessionToken);
        AdDTO dto = adMapper.mapToAdDTO(adRepository.save(adMapper.mapToAd(ad)));
        dto.setSessionToken(sessionToken);
        return dto;
    }

    public List<AdDTO> findAllAds(double min, double max){
        List<Ad> ads =  adRepository.findAdByRange(min, max);
        ads.forEach(ad -> ad.setSessionToken(null));
        return adMapper.mapToAdDTOList(ads);
    }


    public AdDTO updateAd(AdDTO adDTO){
        Ad ad = adMapper.mapToAd(adDTO);
        if(!findAdById(ad.getId()).getSessionToken().equals(ad.getSessionToken())){
            throw new IllegalArgumentException("Bad session token!");
        }
        AdDTO changeDTO = adMapper.mapToAdDTO(adRepository.save(ad));
        subscriptionService.notifySubscibers(
                NotifySubscribersRequest.builder()
                        .item(adDTO)
                        .build()
        );
        return changeDTO;
    }

    public Ad findAdById(Long id) throws IllegalArgumentException {
        return adRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Incorrect ad id!"));
    }

    public List<AdDTO> findByTag(String tag){
        return adMapper.mapToAdDTOList(adRepository.findAdByTags(tag));
    }


    @Transactional
    @Scheduled(fixedDelay = 6000)
    public void deleteExpiredAds(){
        adRepository.findAll().stream()
                .filter(i -> LocalDateTime.now().isAfter(i.getExpiration()))
                .forEach(ad -> adRepository.delete(ad));
    }
}
