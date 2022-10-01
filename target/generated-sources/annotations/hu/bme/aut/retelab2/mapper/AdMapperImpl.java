package hu.bme.aut.retelab2.mapper;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.dto.AdDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-01T16:35:15+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
public class AdMapperImpl implements AdMapper {

    @Override
    public AdDTO mapToAdDTO(Ad entity) {
        if ( entity == null ) {
            return null;
        }

        AdDTO adDTO = new AdDTO();

        adDTO.setId( entity.getId() );
        adDTO.setTitle( entity.getTitle() );
        adDTO.setPrice( entity.getPrice() );
        adDTO.setDateCreated( entity.getDateCreated() );
        adDTO.setExpiration( entity.getExpiration() );
        List<String> list = entity.getTags();
        if ( list != null ) {
            adDTO.setTags( new ArrayList<String>( list ) );
        }

        return adDTO;
    }

    @Override
    public Ad mapToAd(AdDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Ad ad = new Ad();

        ad.setId( dto.getId() );
        ad.setTitle( dto.getTitle() );
        ad.setPrice( dto.getPrice() );
        ad.setSessionToken( dto.getSessionToken() );
        ad.setDateCreated( dto.getDateCreated() );
        ad.setExpiration( dto.getExpiration() );
        List<String> list = dto.getTags();
        if ( list != null ) {
            ad.setTags( new ArrayList<String>( list ) );
        }

        return ad;
    }

    @Override
    public List<AdDTO> mapToAdDTOList(List<Ad> entity) {
        if ( entity == null ) {
            return null;
        }

        List<AdDTO> list = new ArrayList<AdDTO>( entity.size() );
        for ( Ad ad : entity ) {
            list.add( mapToAdDTO( ad ) );
        }

        return list;
    }

    @Override
    public List<Ad> mapToAdList(List<AdDTO> entity) {
        if ( entity == null ) {
            return null;
        }

        List<Ad> list = new ArrayList<Ad>( entity.size() );
        for ( AdDTO adDTO : entity ) {
            list.add( mapToAd( adDTO ) );
        }

        return list;
    }
}
