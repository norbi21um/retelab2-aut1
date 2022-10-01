package hu.bme.aut.retelab2.mapper;

import hu.bme.aut.retelab2.domain.Subscription;
import hu.bme.aut.retelab2.domain.Subscription.SubscriptionBuilder;
import hu.bme.aut.retelab2.dto.SubscriptionDTO;
import hu.bme.aut.retelab2.dto.SubscriptionDTO.SubscriptionDTOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-01T21:41:56+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
public class SubscriptionMapperImpl extends SubscriptionMapper {

    @Override
    public SubscriptionDTO mapToSubscriptionDTO(Subscription entity) {
        if ( entity == null ) {
            return null;
        }

        SubscriptionDTOBuilder subscriptionDTO = SubscriptionDTO.builder();

        subscriptionDTO.id( entity.getId() );
        subscriptionDTO.fistName( entity.getFistName() );
        subscriptionDTO.lastName( entity.getLastName() );
        subscriptionDTO.email( entity.getEmail() );

        subscriptionDTO.adId( entity.getAd().getId() );

        return subscriptionDTO.build();
    }

    @Override
    public Subscription mapToSubscriptionWithoutAd(SubscriptionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SubscriptionBuilder subscription = Subscription.builder();

        subscription.id( dto.getId() );
        subscription.fistName( dto.getFistName() );
        subscription.lastName( dto.getLastName() );
        subscription.email( dto.getEmail() );

        return subscription.build();
    }

    @Override
    public List<SubscriptionDTO> mapToSubscriptionDTOList(List<Subscription> entity) {
        if ( entity == null ) {
            return null;
        }

        List<SubscriptionDTO> list = new ArrayList<SubscriptionDTO>( entity.size() );
        for ( Subscription subscription : entity ) {
            list.add( mapToSubscriptionDTO( subscription ) );
        }

        return list;
    }

    @Override
    public List<Subscription> mapToSubscriptionList(List<SubscriptionDTO> entity) {
        if ( entity == null ) {
            return null;
        }

        List<Subscription> list = new ArrayList<Subscription>( entity.size() );
        for ( SubscriptionDTO subscriptionDTO : entity ) {
            list.add( mapToSubscription( subscriptionDTO ) );
        }

        return list;
    }
}
