package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    @Query("select a from Ad a where a.price > ?1 and a.price < ?2")
    List<Ad> findAdByRange(double min, double max);

    @Query("select a from Ad a where ?1 member of a.tags")
    List<Ad> findAdByTags(String tag);
}
