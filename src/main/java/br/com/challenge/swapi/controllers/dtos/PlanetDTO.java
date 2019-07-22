package br.com.challenge.swapi.controllers.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class PlanetDTO {

    private String id;
    private String name;
    private String climate;
    private String terrain;
    private Long appearances;
}
