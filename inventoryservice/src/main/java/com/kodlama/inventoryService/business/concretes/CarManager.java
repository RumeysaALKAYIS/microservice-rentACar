package com.kodlama.inventoryService.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlama.common.utilities.exceptions.BusinessExeption;
import com.kodlama.common.utilities.mapping.ModelMapperService;
import com.kodlama.inventoryService.business.abstracts.CarService;
import com.kodlama.inventoryService.business.constants.Messages;
import com.kodlama.inventoryService.business.requeses.creates.CreateCarRequest;
import com.kodlama.inventoryService.business.requeses.updates.UpdateCarRequest;
import com.kodlama.inventoryService.business.requeses.updates.UpdateStateCarRequest;
import com.kodlama.inventoryService.business.responses.create.CreateCarResponse;
import com.kodlama.inventoryService.business.responses.get.GetAllCarsResponse;
import com.kodlama.inventoryService.business.responses.update.UpdateCarResponse;
import com.kodlama.inventoryService.business.responses.update.UpdateStateCarResponse;
import com.kodlama.inventoryService.dataAccess.CarRepository;
import com.kodlama.inventoryService.entities.Car;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CarManager implements CarService {

	private CarRepository carRepository;
	private ModelMapperService modelMapperService;

	@Override
	public List<GetAllCarsResponse> getAll() {
		List<Car> cars = this.carRepository.findAll();

		List<GetAllCarsResponse> response = cars.stream()
				.map(c -> this.modelMapperService.forResponse().map(c, GetAllCarsResponse.class))
				.collect(Collectors.toList());

		return response;
	}

	@Override
	public CreateCarResponse add(CreateCarRequest createCarRequest) {

		checkIfBrandExistsByPlate(createCarRequest.getPlate());
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);

		car.setId(UUID.randomUUID().toString());
		this.carRepository.save(car);

		CreateCarResponse carResponse = this.modelMapperService.forResponse().map(car, CreateCarResponse.class);

		return carResponse;
	}

	@Override
	public UpdateCarResponse update(UpdateCarRequest updateCarRequest) {
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carRepository.save(car);
		UpdateCarResponse carResponse = this.modelMapperService.forResponse().map(car, UpdateCarResponse.class);
		return carResponse;
	}

	@Override
	public GetAllCarsResponse getbyId(String id) {

		Car car = this.carRepository.findById(id).get();
		GetAllCarsResponse allCarResponse = this.modelMapperService.forResponse().map(car, GetAllCarsResponse.class);
		return allCarResponse;

	}

	@Override
	public void delete(String id) {
		this.carRepository.deleteById(id);

	}

	@Override
	public void checkIfCar(String id) {
		if (this.carRepository.existsById(id)) {
			throw new BusinessExeption(Messages.CarIsExists);
		}

	}

	private void checkIfBrandExistsByPlate(String plate) {
		Car currentCar = this.carRepository.findByPlate(plate);
		if (currentCar != null) {
			throw new BusinessExeption(Messages.BrandIsExists);
		}
	}

	@Override
	public UpdateStateCarResponse updateStateCar(UpdateStateCarRequest updateStateCarRequest) {
		Car car = this.carRepository.findById(updateStateCarRequest.getId()).get();
		car.setState(updateStateCarRequest.getState());
		UpdateStateCarResponse carRequest = this.modelMapperService.forResponse().map(car,
				UpdateStateCarResponse.class);
		return carRequest;
	}

}