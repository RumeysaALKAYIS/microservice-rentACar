package com.kodlama.rentalservice.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "carid")
	private String carId;
	@Column(name = "datestarted")
	private LocalDateTime dateStarted=LocalDateTime.now();
	@Column(name = "rentedfordays")
	private int rentedForDays;
	@Column(name = "dailyprice")
	private double dailyPrice;
	@Column(name = "totalprice")
	private double totalPrice;

}
