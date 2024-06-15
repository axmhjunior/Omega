package com.mahumane.omegaStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mahumane.omegaStore.dto.ProductsDto;
import com.mahumane.omegaStore.model.ProductsModel;
import com.mahumane.omegaStore.model.UpdateInfoStorageModel;
import com.mahumane.omegaStore.model.UsersModel;
import com.mahumane.omegaStore.service.ProductsService;
import com.mahumane.omegaStore.service.UpdateInfoStorageService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductsService service;
	
	@Autowired
	private UpdateInfoStorageService infoStorageService;
	
	
	@GetMapping
	public ResponseEntity<List<ProductsModel>> selectProducts(){
		return service.selectProducts();
	}
	@GetMapping("/user")
	public ResponseEntity<List<ProductsModel>> selectUserProducts(){
		UsersModel usersModel = (UsersModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return service.selectUserProducts(usersModel.getId());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> selectById(@PathVariable int id){
		return service.selectById(id);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> searchProductByName(@RequestParam String q){
		return service.searchProductByName(q);
	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<ProductsModel>> filters(
			@RequestParam(required = false) Double minPrice,
			@RequestParam(required = false) Double maxPrice,
			@RequestParam(required = false) Integer discounts,
			@RequestParam(required = false) String category,
			@RequestParam(required = false) Integer rating 
			){
		return service.filter(minPrice, maxPrice, discounts, category, rating);
	}
	
	
	@Transactional
	@PostMapping
	public ResponseEntity<?> insertProduct(@RequestBody @Valid ProductsDto dto){
		UsersModel usersModel = (UsersModel)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ProductsModel pm = new ProductsModel(dto, usersModel.getId());
		return service.insertProduct(pm);
	}
	
	
	@Transactional
	@PutMapping("/{id}")
	public  ResponseEntity<?> updateProduct(@PathVariable int id,  @RequestBody  @Valid ProductsDto dto){
		
		UsersModel users = (UsersModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		
		UpdateInfoStorageModel updateInfoStorageModel = new UpdateInfoStorageModel(dto.id(), users.getId());
		infoStorageService.insertUpdateInfo(updateInfoStorageModel);
		return service.updateProduct(id, dto, users.getId());
	}

	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeProduct(@PathVariable int id){
		UsersModel usersModel = (UsersModel)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return service.removeProduct(id, usersModel.getId());
	}
}
