package hu.bme.aut.retelab2.mapper;

import hu.bme.aut.retelab2.domain.Subscription;
import hu.bme.aut.retelab2.dto.SubscriptionDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SubscriptionMapper {

    SubscriptionDTO mapToSubscriptionDTO(Subscription entity);

    Subscription mapToSubscription(SubscriptionDTO dto);

    List<SubscriptionDTO> mapToSubscriptionDTOList(List<Subscription> entity);

    List<Subscription> mapToSubscriptionList(List<SubscriptionDTO> entity);
}

