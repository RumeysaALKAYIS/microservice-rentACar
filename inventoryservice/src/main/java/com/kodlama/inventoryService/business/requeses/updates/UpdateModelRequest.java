package com.kodlama.inventoryService.business.requeses.updates;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateModelRequest {
	@NotBlank
	@NotNull
	private String id;
	
	@NotBlank
	@NotNull
	@Size(min = 2, max = 20)
	private String name;
	
	@NotBlank
	@NotNull
	private String brandId;
}
