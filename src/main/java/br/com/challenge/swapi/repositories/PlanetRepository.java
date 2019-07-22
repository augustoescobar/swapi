package br.com.challenge.swapi.repositories;

import br.com.challenge.swapi.documents.Planet;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, ObjectId> {

    Optional<Planet> findByName(String name);
}
