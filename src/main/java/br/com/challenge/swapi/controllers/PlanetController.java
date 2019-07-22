package br.com.challenge.swapi.controllers;

import br.com.challenge.swapi.clients.dtos.SwapiPlanetResponseDTO;
import br.com.challenge.swapi.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private PlanetService planetService;

    @Autowired
    public PlanetController(PlanetService planetService) {

        this.planetService = planetService;
    }

    @GetMapping                                                              // "Alderaan"
    public ResponseEntity<SwapiPlanetResponseDTO> get(@RequestParam("name") String name) {

        Optional<SwapiPlanetResponseDTO> dto = planetService.findPlanetByName(name);

        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
