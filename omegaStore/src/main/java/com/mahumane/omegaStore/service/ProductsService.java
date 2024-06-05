package com.mahumane.omegaStore.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mahumane.omegaStore.model.ProductsModel;
import com.mahumane.omegaStore.repository.ProductsRepository;


@Service
public class ProductsService {

	@Autowired
	private ProductsRepository action;
	
	public ResponseEntity<List<ProductsModel>> selectProducts(){
		return ResponseEntity.ok(action.findAll());
	}
	
	
	public ResponseEntity<?> insertProduct(ProductsModel pm) {
		return new ResponseEntity<>(action.save(pm), HttpStatus.CREATED);
	}


	public ResponseEntity<?> updateProduct(ProductsModel pm) {
		return ResponseEntity.ok(action.save(pm));
	}


	public ResponseEntity<?> removeProduct(int id) {
		if(action.findById(id) != null) {
			ProductsModel pm = action.findById(id);
			action.delete(pm);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}


	public ResponseEntity<?> searchProductByName(String name) {
		if(action.findByName(name) != null) {
			return ResponseEntity.ok(action.findByName(name));
		}
		return ResponseEntity.notFound().build();
	}


	public ResponseEntity<?> selectById(int id) {
		if(action.findById(id) != null) {
			return ResponseEntity.ok(action.findById(id));
		}
		return ResponseEntity.notFound().build();
	}




}
