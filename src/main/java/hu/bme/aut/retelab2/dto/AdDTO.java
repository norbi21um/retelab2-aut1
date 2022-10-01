package hu.bme.aut.retelab2.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class AdDTO {

    private Long id;

    private String title;

    private double price;

    private String sessionToken;

    private Date dateCreated;

    private LocalDateTime expiration;

    private List<String> tags;

}
