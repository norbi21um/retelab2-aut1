package hu.bme.aut.retelab2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotifySubscribersRequest {

    private AdDTO item;

}
