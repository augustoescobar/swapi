package br.com.challenge.swapi.controllers;

import br.com.challenge.swapi.controllers.dtos.PlanetDTO;
import br.com.challenge.swapi.controllers.dtos.PlanetPageDTO;
import br.com.challenge.swapi.documents.Planet;
import br.com.challenge.swapi.services.MappingService;
import br.com.challenge.swapi.services.PlanetService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private static final Logger logger = LogManager.getLogger(PlanetController.class);

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
    public ResponseEntity<PlanetPageDTO> list(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size) {

        Page<Planet> planetPage = planetService.findAll(PageRequest.of(page, size));

        PlanetPageDTO dtoPage = mappingService.toPageDTO(planetPage);

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanetDTO> get(
            @PathVariable String id) {

        Optional<Planet> optional = planetService.findById(id);

        if (!optional.isPresent()) {

            logger.warn("Planet not found {}", id);

            return ResponseEntity.notFound().build();
        }

        PlanetDTO planetDTO = mappingService.toDTO(optional.get());

        return ResponseEntity.ok(planetDTO);
    }

    @PostMapping(path = {"/", "/{id}"})
    public ResponseEntity<PlanetDTO> save(
            @PathVariable(required = false) String id,
            @RequestBody PlanetDTO dto) {

        if (StringUtils.isNotEmpty(id)) dto.setId(id);

        Planet planet = mappingService.toDocument(dto);

        try {

            planetService.save(planet);
        } catch (Exception e) {

            logger.error("Failed to save planet {}", planet::toString);

            logger.error(e);

            return ResponseEntity.badRequest().build();
        }

        dto = mappingService.toDTO(planet);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlanetDTO> delete(
            @PathVariable(value = "id", required = false) String id) {

        if (StringUtils.isEmpty(id) || !ObjectId.isValid(id)) {

            return ResponseEntity.notFound().build();
        }

        try {

            planetService.delete(new ObjectId(id));
        } catch (Exception e) {

            logger.error("Failed to delete planet", e);

            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.noContent().build();
    }
}
