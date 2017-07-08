package com.poc.hateoaspoc.controller.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // id

	private String reservationName; // reservation_name

	public Long getId() {
		return id;
	}

	public String getReservationName() {
		return reservationName;
	}

	@Override
	public String toString() {
		return "Reservation{" + "id=" + id + ", reservationName='" + reservationName + '\'' + '}';
	}

	Reservation() {// why JPA why???
	}

	public Reservation(String reservationName) {

		this.reservationName = reservationName;
	}
}
