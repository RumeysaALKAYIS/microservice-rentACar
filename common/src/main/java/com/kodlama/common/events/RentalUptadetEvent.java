package com.kodlama.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalUptadetEvent {

	private String message;
	private String newCarId;
	private String exCarId;
}
