package hu.bme.aut.retelab2.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class SubscriptionDTO {

    private Long id;

    private String fistName;

    private String lastName;

    private String email;

    private Long adId;
}
