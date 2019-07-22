package br.com.challenge.swapi.services;

import br.com.challenge.swapi.controllers.dtos.PlanetDTO;
import br.com.challenge.swapi.controllers.dtos.PageDTO;
import br.com.challenge.swapi.documents.Planet;
import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class MappingService {

    public PlanetDTO toDTO(Planet planet) {

        return PlanetDTO.builder()
                .id(planet.getId() != null ? planet.getId().toString() : null)
                .name(planet.getName())
                .terrain(planet.getTerrain())
                .climate(planet.getClimate())
                .appearances(planet.getAppearances())
                .build();
    }

    public Planet toDocument(PlanetDTO dto) {

        return Planet.builder()
                .id(StringUtils.isNotEmpty(dto.getId()) ? new ObjectId(dto.getId()) : null)
                .name(dto.getName())
                .terrain(dto.getTerrain())
                .climate(dto.getClimate())
                .appearances(dto.getAppearances())
                .build();
    }

    public PageDTO<PlanetDTO> toPageDTO(Page<Planet> planetPage) {

        Page<PlanetDTO> page = planetPage.map(this::toDTO);

        return PageDTO.<PlanetDTO>builder()
                .content(page.getContent())
                .first(page.isFirst())
                .last(page.isLast())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .number(page.getNumber())
                .size(page.getSize())
                .build();
    }
}
