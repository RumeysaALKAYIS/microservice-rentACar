package com.kodlama.rentalservice.business.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateRentalRequest {

	@NotBlank
	private String carId;
	@NotNull
	private int rentedForDays;
	@NotNull
	private double dailyPrice;
	@NotNull
	private double totalPrice;
}
