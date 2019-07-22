package br.com.challenge.swapi.controllers.dtos;

public class PlanetDTO {

    private String name;
    private String climate;
    private String terrain;
    private Long appearances;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public Long getAppearances() {
        return appearances;
    }

    public void setAppearances(Long appearances) {
        this.appearances = appearances;
    }

    @Override
    public String toString() {
        return "PlanetDTO{" +
                "name='" + name + '\'' +
                ", climate='" + climate + '\'' +
                ", terrain='" + terrain + '\'' +
                ", appearances=" + appearances +
                '}';
    }
}
