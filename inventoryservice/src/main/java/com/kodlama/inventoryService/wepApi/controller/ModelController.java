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

import com.kodlama.inventoryService.business.abstracts.ModelService;
import com.kodlama.inventoryService.business.requeses.creates.CreateModelRequest;
import com.kodlama.inventoryService.business.requeses.updates.UpdateModelRequest;
import com.kodlama.inventoryService.business.responses.create.CreateModelResponse;
import com.kodlama.inventoryService.business.responses.get.GetAllModelResponse;
import com.kodlama.inventoryService.business.responses.update.UpdateModelResponse;

import lombok.AllArgsConstructor;

@RequestMapping("/api/models")
@RestController
@AllArgsConstructor
public class ModelController {
	
	private ModelService modelService;
	
	@GetMapping()
	public List<GetAllModelResponse> getAll(){
		return this.modelService.getAll();
	}
//	@GetMapping("{/id}")
//	public GetAllModelResponse getById(@PathVariable String id) {
//		return this.modelService.getById(id);
//	}
	@PostMapping()
	public CreateModelResponse add(@RequestBody CreateModelRequest createModelRequest) {
	 return	this.modelService.add(createModelRequest);
	}
	
	@PutMapping()
	public UpdateModelResponse update (@RequestBody UpdateModelRequest updateModelRequest) {
		return this.modelService.update(updateModelRequest);
	}
	@DeleteMapping("/{id}")
 	public void delete(@PathVariable String id) {
	
		 this.modelService.delete(id);
	}
	
	
	
	
	
	

}
