package com.kodlama.inventoryService.business.abstracts;

import java.util.List;

import com.kodlama.inventoryService.business.requeses.creates.CreateBrandRequest;
import com.kodlama.inventoryService.business.requeses.updates.UpdateBrandRequest;
import com.kodlama.inventoryService.business.responses.create.CreateBrandResponse;
import com.kodlama.inventoryService.business.responses.get.GetAllBrandResponse;
import com.kodlama.inventoryService.business.responses.update.UpdateBrandResponse;

public interface BrandService {
	List<GetAllBrandResponse> getAll();

	CreateBrandResponse add(CreateBrandRequest createBrandRequest);

	UpdateBrandResponse update(UpdateBrandRequest updateBrandRequest);

	void delete(String id);

	GetAllBrandResponse getbyId(String id);

}