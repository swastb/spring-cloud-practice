package org.poc.graphdbpoc;

import java.util.Arrays;
import java.util.List;

import org.poc.graphdbpoc.model.Person;
import org.poc.graphdbpoc.service.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class GraphdbPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphdbPocApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(PersonRepository personRepository) {
		return args -> {

			personRepository.deleteAll();

			Person greg = new Person("Greg");
			Person roy = new Person("Roy");
			Person craig = new Person("Craig");

			List<Person> team = Arrays.asList(greg, roy, craig);

			System.out.println("Before linking up with Neo4j...");

			team.stream().forEach(person -> System.out.println("\t" + person.toString()));

			personRepository.save(greg);
			personRepository.save(roy);
			personRepository.save(craig);

			greg = personRepository.findByName(greg.getName());
			greg.worksWith(roy);
			greg.worksWith(craig);
			personRepository.save(greg);

			roy = personRepository.findByName(roy.getName());
			roy.worksWith(craig);
			// We already know that roy works with greg
			personRepository.save(roy);

			// We already know craig works with roy and greg

			System.out.println("Lookup each person by name...");
			team.stream().forEach(
					person -> System.out.println("\t" + personRepository.findByName(person.getName()).toString()));
		};
	}

}
