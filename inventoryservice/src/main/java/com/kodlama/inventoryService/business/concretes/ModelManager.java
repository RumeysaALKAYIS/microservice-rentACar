package com.kodlama.inventoryService.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlama.common.utilities.exceptions.BusinessExeption;
import com.kodlama.common.utilities.mapping.ModelMapperService;
import com.kodlama.inventoryService.business.abstracts.BrandService;
import com.kodlama.inventoryService.business.abstracts.ModelService;
import com.kodlama.inventoryService.business.requeses.creates.CreateModelRequest;
import com.kodlama.inventoryService.business.requeses.updates.UpdateModelRequest;
import com.kodlama.inventoryService.business.responses.create.CreateModelResponse;
import com.kodlama.inventoryService.business.responses.get.GetAllBrandResponse;
import com.kodlama.inventoryService.business.responses.get.GetAllModelResponse;
import com.kodlama.inventoryService.business.responses.update.UpdateModelResponse;
import com.kodlama.inventoryService.dataAccess.ModelRepository;
import com.kodlama.inventoryService.entities.Brand;
import com.kodlama.inventoryService.entities.Model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {

	private ModelRepository modelRepository;
	private ModelMapperService mapperService;
	private BrandService brandService;

	@Override
	public List<GetAllModelResponse> getAll() {
		List<Model> modells = this.modelRepository.findAll();
		List<GetAllModelResponse> allModelResponses = modells.stream()
				.map(m -> this.mapperService.forResponse().map(m, GetAllModelResponse.class))
				.collect(Collectors.toList());

		return allModelResponses;
	}

	@Override
	public CreateModelResponse add(CreateModelRequest createModelRequest) {

		
		GetAllBrandResponse getAllBrandResponse= this.brandService.getbyId(createModelRequest.getBrandId());
		Brand brand=this.mapperService.forResponse().map(getAllBrandResponse, Brand.class);
		Model model = this.mapperService.forRequest().map(createModelRequest, Model.class);
		model.setId(UUID.randomUUID().toString());
		model.setBrand(brand);
		
		this.modelRepository.save(model);
		CreateModelResponse createModelResponse = this.mapperService.forResponse().map(model,
				CreateModelResponse.class);
		return createModelResponse;
	}

	private void checkIfBrandExistsByName(String name) {
		Model currentModel = this.modelRepository.findByName(name);
		if (currentModel != null) {
			throw new BusinessExeption("BRAND.EXISTS");
		}
	}

	@Override
	public GetAllModelResponse getById(String id) {
		
		Model model=this.modelRepository.findById(id).get();

		GetAllModelResponse allModelResponse = this.mapperService.forResponse().map(model, GetAllModelResponse.class);
		return allModelResponse;
	}

	@Override
	public UpdateModelResponse update(UpdateModelRequest updateModelRequest) {
		Model model = this.mapperService.forRequest().map(updateModelRequest, Model.class);
		this.modelRepository.save(model);
		UpdateModelResponse modelResponse = this.mapperService.forResponse().map(model, UpdateModelResponse.class);
		return modelResponse;
	}

	@Override
	public void delete(String id) {
		this.modelRepository.deleteById(id);

	}

}