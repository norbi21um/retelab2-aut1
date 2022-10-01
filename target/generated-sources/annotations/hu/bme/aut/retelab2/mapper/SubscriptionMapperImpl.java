package hu.bme.aut.retelab2.mapper;

import hu.bme.aut.retelab2.domain.Subscription;
import hu.bme.aut.retelab2.domain.Subscription.SubscriptionBuilder;
import hu.bme.aut.retelab2.dto.SubscriptionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-01T16:35:14+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
public class SubscriptionMapperImpl implements SubscriptionMapper {

    @Override
    public SubscriptionDTO mapToSubscriptionDTO(Subscription entity) {
        if ( entity == null ) {
            return null;
        }

        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();

        subscriptionDTO.setId( entity.getId() );
        subscriptionDTO.setFistName( entity.getFistName() );
        subscriptionDTO.setLastName( entity.getLastName() );
        subscriptionDTO.setEmail( entity.getEmail() );
        subscriptionDTO.setAdId( entity.getAdId() );

        return subscriptionDTO;
    }

    @Override
    public Subscription mapToSubscription(SubscriptionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SubscriptionBuilder subscription = Subscription.builder();

        subscription.id( dto.getId() );
        subscription.fistName( dto.getFistName() );
        subscription.lastName( dto.getLastName() );
        subscription.email( dto.getEmail() );
        subscription.adId( dto.getAdId() );

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
