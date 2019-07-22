package br.com.challenge.swapi.controllers;

import br.com.challenge.swapi.controllers.dtos.PageDTO;
import br.com.challenge.swapi.controllers.dtos.PlanetDTO;
import br.com.challenge.swapi.documents.Planet;
import br.com.challenge.swapi.services.MappingService;
import br.com.challenge.swapi.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private PlanetService planetService;
    private MappingService mappingService;

    @Autowired
    public PlanetController(
            PlanetService planetService,
            MappingService mappingService) {

        this.planetService = planetService;
        this.mappingService = mappingService;
    }

    @GetMapping
    public ResponseEntity<PageDTO<PlanetDTO>> list(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size) {

        Page<Planet> planetPage = planetService.findAll(PageRequest.of(page, size));

        PageDTO<PlanetDTO> dtoPage = mappingService.toPageDTO(planetPage);

        return ResponseEntity.ok(dtoPage);
    }

//    /**
//     * Retrieves information about a planet by name
//     * @param id id of a previously added planet. This param has priority over others
//     * @param name name of a planet. Examples: Alderaan, Yavin IV, Hoth, Dagobah, Bespin
//     * @return SwapiPlanet instance within ResponseEntity bean
//     */
//    @GetMapping(value = {"/", "/{id}"})
//    public ResponseEntity<PlanetDTO> get(
//            @PathVariable(value = "id", required = false) Long id,
//            @RequestParam(value = "name", required = false) String name) {
//
//        Optional<PlanetDTO> planet = Optional.empty();
//
//        if (id != null) {
//
//            planet = Optional.empty();
//        } else if (StringUtils.isNotEmpty(name)) {
//
//            planet = Optional.empty();
//        }
//
//        return ResponseEntity.of(planet);
//    }

    @PostMapping
    public ResponseEntity<PlanetDTO> add(
            @RequestBody PlanetDTO dto) {

        Planet planet = mappingService.toDocument(dto);

        try {

            planetService.save(planet);
        } catch (Exception e) {

            return ResponseEntity.badRequest().build();
        }

        dto = mappingService.toDTO(planet);

        return ResponseEntity.ok(dto);
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
