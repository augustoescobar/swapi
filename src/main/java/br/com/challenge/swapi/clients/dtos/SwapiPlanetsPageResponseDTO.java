package br.com.challenge.swapi.clients.dtos;

import java.util.List;

@SuppressWarnings("unused")
public class SwapiPlanetsPageResponseDTO {

    private Long count;
    private String next;
    private String previous;
    private List<SwapiPlanetResponseDTO> results;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<SwapiPlanetResponseDTO> getResults() {
        return results;
    }

    public void setResults(List<SwapiPlanetResponseDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "SwapiPlanetsPageResponseDTO{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", results=" + results +
                '}';
    }
}
