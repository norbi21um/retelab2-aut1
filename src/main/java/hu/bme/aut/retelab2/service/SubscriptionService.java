package hu.bme.aut.retelab2.service;

import hu.bme.aut.retelab2.domain.Subscription;
import hu.bme.aut.retelab2.dto.AdDTO;
import hu.bme.aut.retelab2.dto.EmailRequest;
import hu.bme.aut.retelab2.dto.NotifySubscribersRequest;
import hu.bme.aut.retelab2.dto.SubscriptionDTO;
import hu.bme.aut.retelab2.mapper.AdMapper;
import hu.bme.aut.retelab2.mapper.SubscriptionMapper;
import hu.bme.aut.retelab2.repository.AdRepository;
import hu.bme.aut.retelab2.repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    @Autowired
    private MailService mailService;

    @Autowired
    private AdMapper adMapper;


    public SubscriptionDTO subscribe(SubscriptionDTO subscription) {
        return subscriptionMapper.mapToSubscriptionDTO(
                subscriptionRepository.save(subscriptionMapper.mapToSubscription(subscription))
        );
    }

    public void notifySubscibers(NotifySubscribersRequest request){
        mailService.sendEmail(
                EmailRequest.builder()
                        .subscriptions(findSubscribers(request.getItem()))
                        .build()
        );
    }

    private List<SubscriptionDTO> findSubscribers(AdDTO adDTO){
        return subscriptionMapper.mapToSubscriptionDTOList(
                subscriptionRepository.findAllByAdId(
                        adMapper.mapToAd(adDTO)
                )
        );
    }
}
