package br.com.challenge.swapi.clients;

import br.com.challenge.swapi.clients.dtos.SwapiPlanetsPageResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "planets", url = "${app.swapi.url}")
public interface SwapiPlanetClient {

    @RequestMapping(method = RequestMethod.GET, value = "/planets/?search={name}&page={page}&format=json", consumes = "application/json")
    ResponseEntity<SwapiPlanetsPageResponseDTO> getPlanets(
            @RequestHeader Map<String, String> headerMap,
            @PathVariable("name") String name,
            @PathVariable("page") Integer page);
}
