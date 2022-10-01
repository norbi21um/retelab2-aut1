package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.Subscription;
import hu.bme.aut.retelab2.dto.AdDTO;
import hu.bme.aut.retelab2.dto.SubscriptionDTO;
import hu.bme.aut.retelab2.service.AdService;
import hu.bme.aut.retelab2.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    static final String MIN_VALUE = "0";

    static final String MAX_VALUE = "10000000";

    @Autowired
    private AdService adService;

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public List<AdDTO> getAll(@RequestParam(required = false, defaultValue = MIN_VALUE) double min,
                              @RequestParam(required = false, defaultValue = MAX_VALUE) double max) {
        return adService.findAllAds(min, max);
    }

    @GetMapping("{tag}")
    public List<AdDTO> getByTag(@PathVariable String tag){
        return adService.findByTag(tag);
    }

    @PostMapping
    public AdDTO create(@RequestBody AdDTO ad) {
        ad.setId(null);
        return adService.createAd(ad);
    }

    @PostMapping("/{id}/subscribe")
    public SubscriptionDTO subscribe(@RequestBody SubscriptionDTO subscription) {
        return subscriptionService.subscribe(subscription);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AdDTO ad) {
        try {
            return ResponseEntity.ok(adService.updateAd(ad));
        } catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Error: Invalid session token or ad id");
        }
    }

}
