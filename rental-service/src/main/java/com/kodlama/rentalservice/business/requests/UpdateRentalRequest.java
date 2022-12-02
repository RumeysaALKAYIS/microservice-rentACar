package com.kodlama.rentalservice.business.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

	@NotBlank
	private String id;
	@NotBlank
	private String carId;
	@NotNull
	private int rentedForDays;
	@NotNull
	private double dailyPrice;
	@NotNull
	private double totalPrice;
}
