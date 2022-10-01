package hu.bme.aut.retelab2.config;

import hu.bme.aut.retelab2.mapper.AdMapper;
import hu.bme.aut.retelab2.mapper.AdMapperImpl;
import hu.bme.aut.retelab2.mapper.SubscriptionMapper;
import hu.bme.aut.retelab2.mapper.SubscriptionMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdvertisementConfig {

    @Bean
    public AdMapper getAdMapper(){
        return new AdMapperImpl();
    }

    @Bean
    public SubscriptionMapper getSubscriptionMapper(){
        return new SubscriptionMapperImpl();
    }

}
