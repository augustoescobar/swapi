package br.com.challenge.swapi.services;

import br.com.challenge.swapi.clients.SwapiPlanetClient;
import br.com.challenge.swapi.clients.dtos.SwapiPlanet;
import br.com.challenge.swapi.clients.dtos.SwapiPlanetPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.singletonMap;

@Service
public class PlanetService {

    private static final Map<String, String> headersMap = singletonMap("User-Agent", "PostmanRuntime/7.15.2");

    private static final Logger logger = LogManager.getLogger(PlanetService.class);

    private SwapiPlanetClient swapiPlanetClient;

    @Autowired
    public PlanetService(SwapiPlanetClient swapiPlanetClient) {

        this.swapiPlanetClient = swapiPlanetClient;
    }

    public Optional<SwapiPlanet> findPlanetByName(@NotNull String name) {

        ResponseEntity<SwapiPlanetPage> response = swapiPlanetClient.getPlanets(headersMap, name.toLowerCase(), 1);

        if (response.getStatusCode().isError()
            || response.getBody() == null
            || response.getBody().getResults() == null) {

            logger.error("An error has occurred while retrieving planet {} - status: {}, message: {}",
                name, response.getStatusCode(), response.getBody());

            return Optional.empty();
        }

        return response.getBody().getResults().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}
