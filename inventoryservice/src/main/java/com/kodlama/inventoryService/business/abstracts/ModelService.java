package com.kodlama.inventoryService.business.abstracts;

import java.util.List;

import com.kodlama.inventoryService.business.requeses.creates.CreateModelRequest;
import com.kodlama.inventoryService.business.requeses.updates.UpdateModelRequest;
import com.kodlama.inventoryService.business.responses.create.CreateModelResponse;
import com.kodlama.inventoryService.business.responses.get.GetAllModelResponse;
import com.kodlama.inventoryService.business.responses.update.UpdateModelResponse;

public interface ModelService {

	List<GetAllModelResponse> getAll();

	GetAllModelResponse getById(String id);

	CreateModelResponse add(CreateModelRequest createModelRequest);

	UpdateModelResponse update(UpdateModelRequest updateModelRequest);

	void delete(String id);

}