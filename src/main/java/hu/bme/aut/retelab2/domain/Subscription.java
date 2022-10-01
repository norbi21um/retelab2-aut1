package hu.bme.aut.retelab2.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subscription {

    @Id
    @GeneratedValue
    private Long id;

    private String fistName;

    private String lastName;

    private String email;

    private Long adId;

}
