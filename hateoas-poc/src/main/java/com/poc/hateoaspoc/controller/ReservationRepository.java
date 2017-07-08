package com.poc.hateoaspoc.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.poc.hateoaspoc.controller.model.Reservation;


@RepositoryRestResource
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	/*@RestResource(path = "by-name")
	Collection<Reservation> findByReservationName(@Param("rn") String rn);*/
}

