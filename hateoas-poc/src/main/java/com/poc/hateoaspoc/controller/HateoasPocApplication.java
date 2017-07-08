package com.poc.hateoaspoc.controller;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.poc.hateoaspoc.controller.model.Reservation;

@SpringBootApplication

@ComponentScan("com.poc.hateoaspoc")
public class HateoasPocApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(HateoasPocApplication.class, args);
	}
}

@Component
class SampleRecordsCLR implements CommandLineRunner {

	@Autowired
	private ReservationRepository reservationRepository;
	@Override
	public void run(String... args) throws Exception {
		Stream.of("Josh", "Jungryeol", "Nosung", "Hyobeom", "Soeun", "Seunghue", "Peter", "Jooyong")
				.forEach(name -> reservationRepository.save(new Reservation(name)));

		// reservationRepository.findAll().forEach(System.out::println);
	}
}
