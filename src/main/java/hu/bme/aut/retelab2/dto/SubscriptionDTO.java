package hu.bme.aut.retelab2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubscriptionDTO {

    private Long id;

    private String fistName;

    private String lastName;

    private String email;

    private Long adId;
}
