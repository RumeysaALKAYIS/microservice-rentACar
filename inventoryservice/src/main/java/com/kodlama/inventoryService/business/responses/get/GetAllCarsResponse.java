package com.kodlama.inventoryService.business.responses.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCarsResponse {

	private String id;
	private double dailyPrice;
	private int modelYear;
//	private String description;
	private String plate;
	private int state;
	private int model_id;
	//private int brandName;
	//private String colorName;

}
