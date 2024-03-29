package br.com.challenge.swapi.services;

import br.com.challenge.swapi.clients.dtos.SwapiPlanet;
import br.com.challenge.swapi.documents.Planet;
import br.com.challenge.swapi.repositories.PlanetRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Service
public class PlanetService {

    private static final Logger logger = LogManager.getLogger(PlanetService.class);

    private SwapiPlanetService swapiPlanetService;
    private PlanetRepository planetRepository;

    @Autowired
    public PlanetService(
            SwapiPlanetService swapiPlanetService,
            PlanetRepository planetRepository) {

        this.swapiPlanetService = swapiPlanetService;
        this.planetRepository = planetRepository;
    }

    private PageRequest normalize(PageRequest pageRequest) {

        return PageRequest.of(
                max(0, pageRequest.getPageNumber()),
                max(0, min(50, pageRequest.getPageSize()))
        );
    }

    private Boolean isValid(Planet planet) {

        return StringUtils.isNotEmpty(planet.getName())
                && StringUtils.isNotEmpty(planet.getClimate())
                && StringUtils.isNotEmpty(planet.getTerrain());
    }

    public Page<Planet> findAll(PageRequest pageRequest) {

        pageRequest = normalize(pageRequest);

        return planetRepository.findAll(pageRequest);
    }

    public Optional<Planet> findById(String id) {

        if (StringUtils.isEmpty(id) || !ObjectId.isValid(id)) {

            return Optional.empty();
        }

        return planetRepository.findById(new ObjectId(id));
    }

    public Optional<Planet> findByName(String name) {

        if (StringUtils.isEmpty(name)) {

            logger.warn("invalid parameter {}", name);

            return Optional.empty();
        }

        return planetRepository.findByName(name);
    }

    public void save(Planet planet) {

        if (!isValid(planet)) {

            throw new IllegalArgumentException("Invalid planet attributes");
        }

        if (planet.getId() != null && !planetRepository.existsById(planet.getId())) {

            throw new IllegalArgumentException("Planet not found " + planet.getId().toString());
        }

        Optional<SwapiPlanet> swapiPlanet = swapiPlanetService.findPlanetByName(planet.getName());

        if (!swapiPlanet.isPresent()) {

            throw new IllegalArgumentException("Planet not found in SWAPI " + planet.getName());
        }

        planet.setAppearances(swapiPlanet.get().getFilms().size());

        planetRepository.save(planet);

        logger.info("Planet saved successfully {}", planet);
    }

    public void delete(ObjectId objectId) {

        if (!planetRepository.existsById(objectId)) {

            throw new IllegalArgumentException("Planet not found " + objectId);
        }

        planetRepository.deleteById(objectId);
    }
}
