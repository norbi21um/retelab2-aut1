package hu.bme.aut.retelab2.service;

import hu.bme.aut.retelab2.domain.Subscription;
import hu.bme.aut.retelab2.dto.SubscriptionDTO;
import hu.bme.aut.retelab2.mapper.SubscriptionMapper;
import hu.bme.aut.retelab2.repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SubscriptionService {

    @Autowired
    private AdService adService;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    @Autowired
    private MailService mailService;


    public SubscriptionDTO subscribe(SubscriptionDTO subscription) {
        return subscriptionMapper.mapToSubscriptionDTO(
                subscriptionRepository.save(subscriptionMapper.mapToSubscription(subscription))
        );
    }

    public void sendEmails(long adId){
        subscriptionRepository.findAllByAdId(adId).forEach(sub -> {
            mailService.sendEmail(sub.getEmail(), sub.getFistName(), sub.getLastName());
        });
    }

    private List<SubscriptionDTO> findSubscribers(long adId){
        return subscriptionMapper.mapToSubscriptionDTOList(subscriptionRepository.findAllByAdId(adId));
    }
}
