package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("select s from Subscription s where s.adId = ?1")
    List<Subscription> findAllByAdId(long adId);

}
