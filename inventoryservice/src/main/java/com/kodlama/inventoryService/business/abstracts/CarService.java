package com.kodlama.inventoryService.business.abstracts;

import java.util.List;

import com.kodlama.inventoryService.business.requeses.creates.CreateCarRequest;
import com.kodlama.inventoryService.business.requeses.updates.UpdateCarRequest;
import com.kodlama.inventoryService.business.requeses.updates.UpdateStateCarRequest;
import com.kodlama.inventoryService.business.responses.create.CreateCarResponse;
import com.kodlama.inventoryService.business.responses.get.GetAllCarsResponse;
import com.kodlama.inventoryService.business.responses.update.UpdateCarResponse;
import com.kodlama.inventoryService.business.responses.update.UpdateStateCarResponse;

public interface CarService {

	List<GetAllCarsResponse> getAll();

	CreateCarResponse add(CreateCarRequest createCarRequest);

	UpdateCarResponse update(UpdateCarRequest updateCarRequest);

	void delete(String id);

	GetAllCarsResponse getbyId(String id);

	UpdateStateCarResponse updateStateCar(UpdateStateCarRequest updateStateCarRequest);

	void checkIfCar(String id);
}