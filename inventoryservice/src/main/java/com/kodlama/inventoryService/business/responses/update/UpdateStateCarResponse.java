package com.kodlama.inventoryService.business.responses.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateStateCarResponse {

	private String id;
	private double dailyPrice;
	private int modelYear;
	private int state;
	private String modelId;
}
