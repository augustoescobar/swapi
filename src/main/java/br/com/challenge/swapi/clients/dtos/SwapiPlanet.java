package br.com.challenge.swapi.clients.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SwapiPlanet {

    private String terrain;
    private String edited;
    private String diameter;
    private String gravity;
    private String created;
    private List<String> residents;

    @JsonProperty("orbital_period")
    private String orbitalPeriod;
    private List<String> films;
    private String population;
    private String climate;

    @JsonProperty("rotation_period")
    private String rotationPeriod;

    @JsonProperty("surface_water")
    private String surfaceWater;
    private String name;
    private String url;
}
