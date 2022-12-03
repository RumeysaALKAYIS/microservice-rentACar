package com.kodlama.rentalservice.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlama.common.events.RentalCreatedEvent;
import com.kodlama.common.events.RentalUptadetEvent;
import com.kodlama.common.utilities.exceptions.BusinessExeption;
import com.kodlama.common.utilities.mapping.ModelMapperService;
import com.kodlama.rentalservice.business.abstracts.RentalServices;
import com.kodlama.rentalservice.business.client.CarServiceClient;
import com.kodlama.rentalservice.business.constants.Messages;
import com.kodlama.rentalservice.business.requests.CreateRentalRequest;
import com.kodlama.rentalservice.business.requests.UpdateRentalRequest;
import com.kodlama.rentalservice.business.responses.CreateRentalResponse;
import com.kodlama.rentalservice.business.responses.GetAllRentalResponse;
import com.kodlama.rentalservice.business.responses.UpdateRentalResponse;
import com.kodlama.rentalservice.dataAccess.RentalRepository;
import com.kodlama.rentalservice.entities.Rental;
import com.kodlama.rentalservice.kafka.RentalCreatedProducer;
import com.kodlama.rentalservice.kafka.RentalUpdateProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalManager implements RentalServices {

	private RentalRepository rentalRepository;
	private ModelMapperService modelService;
	private CarServiceClient carServiceClient;

	private RentalCreatedProducer rentalProducer;
	private RentalUpdateProducer rentalUpdateProducer;

	@Override
	public CreateRentalResponse add(CreateRentalRequest createRentalRequest) {

		checkIfRented(createRentalRequest.getCarId());
		
		Rental rental = this.modelService.forRequest().map(createRentalRequest, Rental.class);
		rental.setId(UUID.randomUUID().toString());
		rental.setTotalPrice(createRentalRequest.getDailyPrice() * createRentalRequest.getRentedForDays());

		this.rentalRepository.save(rental);

		RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
		rentalCreatedEvent.setCarId(rental.getCarId());
		rentalCreatedEvent.setMessage(Messages.CeratedRental);

		this.rentalProducer.sendMessage(rentalCreatedEvent);

		CreateRentalResponse createRentalResponse = this.modelService.forResponse().map(rental,
				CreateRentalResponse.class);
		return createRentalResponse;
	}

	@Override
	public List<GetAllRentalResponse> getAll() {
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalResponse> allRentalResponses = rentals.stream()
				.map(r -> this.modelService.forResponse().map(r, GetAllRentalResponse.class))
				.collect(Collectors.toList());
		return allRentalResponses;
	}

	@Override
	public UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest) {

		Rental rental = this.rentalRepository.findById(updateRentalRequest.getId()).get();

		RentalUptadetEvent rentalUptadetEvent = new RentalUptadetEvent();
		rentalUptadetEvent.setNewCarId(updateRentalRequest.getCarId());
		rentalUptadetEvent.setExCarId(rental.getCarId());
		rentalUptadetEvent.setMessage(Messages.UpdeatedRental);

		this.rentalUpdateProducer.sendMessageUpdate(rentalUptadetEvent);

		rental = this.modelService.forRequest().map(updateRentalRequest, Rental.class);
		rental.setDateStarted(LocalDateTime.now());
		this.rentalRepository.save(rental);

		UpdateRentalResponse rentalResponse = this.modelService.forResponse().map(rental, UpdateRentalResponse.class);


		return rentalResponse;
	}

	private void checkIfRented(String carId) {

		if (this.carServiceClient.getbyId(carId).getState()==1) {
			throw new BusinessExeption(Messages.CarIsRental);
		}
		
	}

}
