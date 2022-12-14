package com.kodlama.inventoryService.business.requeses.updates;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {
	
	@NotBlank
	@NotNull
	private String id;
	
	@NotBlank
	@NotNull
	@Size(min = 2, max = 20)
	private String name;

}
