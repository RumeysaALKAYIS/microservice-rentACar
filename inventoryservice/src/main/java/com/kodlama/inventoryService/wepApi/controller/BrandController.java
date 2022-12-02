package com.kodlama.inventoryService.wepApi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlama.inventoryService.business.abstracts.BrandService;
import com.kodlama.inventoryService.business.requeses.creates.CreateBrandRequest;
import com.kodlama.inventoryService.business.requeses.updates.UpdateBrandRequest;
import com.kodlama.inventoryService.business.responses.create.CreateBrandResponse;
import com.kodlama.inventoryService.business.responses.get.GetAllBrandResponse;
import com.kodlama.inventoryService.business.responses.update.UpdateBrandResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor

public class BrandController {
	private BrandService brandService;
	@GetMapping()
	public List<GetAllBrandResponse> getAll(){
		return this.brandService.getAll();
	}

	@PostMapping()
	public CreateBrandResponse add(@RequestBody CreateBrandRequest createBrandRequest) {
		return this.brandService.add(createBrandRequest);
	}

	@PutMapping()
	public UpdateBrandResponse update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest);
	}
	
	@GetMapping("/{id}")
	public GetAllBrandResponse getbyId(@PathVariable String id) {
		return this.brandService.getbyId(id); 
				
	}
	@DeleteMapping("/{id}")
	void delete(@PathVariable String id) {
		 this.brandService.delete(id);
	}

}
