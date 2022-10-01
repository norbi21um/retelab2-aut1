package hu.bme.aut.retelab2.mapper;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.dto.AdDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface AdMapper {

    @Mapping(target = "sessionToken", ignore = true)
    AdDTO mapToAdDTO(Ad entity);

    Ad mapToAd(AdDTO dto);

    List<AdDTO> mapToAdDTOList(List<Ad> entity);

    List<Ad> mapToAdList(List<AdDTO> entity);

}
