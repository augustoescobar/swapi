package br.com.challenge.swapi.controllers;

import br.com.challenge.swapi.clients.dtos.SwapiPlanet;
import br.com.challenge.swapi.controllers.dtos.PlanetDTO;
import br.com.challenge.swapi.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private PlanetService planetService;

    @Autowired
    public PlanetController(PlanetService planetService) {

        this.planetService = planetService;
    }

    @GetMapping
    public ResponseEntity<List<PlanetDTO>> list() {

        return ResponseEntity.of(Optional.empty());
    }

    /**
     * Retrieves information about a planet by name
     * @param id id of a previously added planet. This param has priority over others
     * @param name name of a planet. Examples: Alderaan, Yavin IV, Hoth, Dagobah, Bespin
     * @return SwapiPlanet instance within ResponseEntity bean
     */
    @GetMapping("/{id}")
    public ResponseEntity<SwapiPlanet> get(
            @PathVariable(value = "id", required = false) String id,
            @RequestParam(value = "name", required = false) String name) {

        return ResponseEntity.of(planetService.findPlanetByName(name));
    }

    @PostMapping
    public ResponseEntity<PlanetDTO> add(
            @RequestBody PlanetDTO planet) {

        return ResponseEntity.of(Optional.empty());
    }

    @PostMapping("/{id}")
    public ResponseEntity<PlanetDTO> edit(
            @PathVariable(value = "id", required = false) String id,
            @RequestBody PlanetDTO planet) {

        return ResponseEntity.of(Optional.empty());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlanetDTO> delete(
            @PathVariable(value = "id", required = false) String id) {

        return ResponseEntity.of(Optional.empty());
    }
}
