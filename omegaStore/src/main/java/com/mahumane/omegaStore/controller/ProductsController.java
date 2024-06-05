package com.mahumane.omegaStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.mahumane.omegaStore.service.ProductsService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("products")
public class ProductsController {

	@Autowired
	private ProductsService service;
	
	@GetMapping
	public ResponseEntity<List<ProductsModel>> selectProducts(){
		return service.selectProducts();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> selectById(@PathVariable int id){
		return service.selectById(id);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> searchProductByName(@RequestParam String q){
		return service.searchProductByName(q);
	}
	
	@PostMapping
	public ResponseEntity<?> insertProduct(@RequestBody @Valid ProductsDto dto){
		ProductsModel pm = new ProductsModel(dto);
		return service.insertProduct(pm);
	}
	
	@PutMapping
	public  ResponseEntity<?> updateProduct(@RequestBody  @Valid ProductsDto dto){
		ProductsModel pm = new ProductsModel(dto);
		return service.updateProduct(pm);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeProduct(@PathVariable int id){
		return service.removeProduct(id);
	}
	
	
}
