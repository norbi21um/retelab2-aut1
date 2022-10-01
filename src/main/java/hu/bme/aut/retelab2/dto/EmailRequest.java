package hu.bme.aut.retelab2.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmailRequest {

    private List<SubscriptionDTO> subscriptions;

}
