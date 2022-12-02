package com.kodlama.inventoryService.business.requeses.updates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStateCarRequest {
	
	private String id;
	private int state;

}
