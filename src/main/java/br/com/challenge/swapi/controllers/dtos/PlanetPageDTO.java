package br.com.challenge.swapi.controllers.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlanetPageDTO extends PageDTO<PlanetDTO> {

    @Builder(builderMethodName = "planetPageBuilder")
    public PlanetPageDTO(
            List<PlanetDTO> content,
            Boolean last,
            Boolean first,
            Integer totalPages,
            Long totalElements,
            Integer size,
            Integer number) {

        super(content, last, first, totalPages, totalElements, size, number);
    }
}
