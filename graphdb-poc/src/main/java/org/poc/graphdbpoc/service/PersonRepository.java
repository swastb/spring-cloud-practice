package org.poc.graphdbpoc.service;

import org.poc.graphdbpoc.model.Person;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PersonRepository extends GraphRepository<Person> {

	Person findByName(String name);
}
