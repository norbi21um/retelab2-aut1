package hu.bme.aut.retelab2.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Ad {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private double price;

    private String sessionToken;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    private LocalDateTime expiration;

    @ElementCollection
    private List<String> tags;

    @OneToMany(mappedBy = "ad")
    private List<Subscription> subscriptions;
}
