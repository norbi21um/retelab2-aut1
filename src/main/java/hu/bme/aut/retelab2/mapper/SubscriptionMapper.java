package hu.bme.aut.retelab2.mapper;


import hu.bme.aut.retelab2.domain.Subscription;
import hu.bme.aut.retelab2.dto.SubscriptionDTO;
import hu.bme.aut.retelab2.service.AdService;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public abstract class SubscriptionMapper {

    @Autowired
    private AdService adService;

    @Mapping(target = "adId", expression = "java(entity.getAd().getId())")
    public abstract SubscriptionDTO mapToSubscriptionDTO(Subscription entity);

    @Mapping(target = "ad", ignore = true)
    public abstract Subscription mapToSubscriptionWithoutAd(SubscriptionDTO dto);

    @Named(value = "useMe")
    public Subscription mapToSubscription(SubscriptionDTO dto){
        Subscription entity = mapToSubscriptionWithoutAd(dto);
        entity.setAd(adService.findAdById(dto.getAdId()));
        return entity;
    }

    public abstract List<SubscriptionDTO> mapToSubscriptionDTOList(List<Subscription> entity);

    @IterableMapping(qualifiedByName = "useMe")
    public abstract List<Subscription> mapToSubscriptionList(List<SubscriptionDTO> entity);
}

