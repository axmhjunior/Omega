package com.mahumane.omegaStore.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mahumane.omegaStore.dto.ProductsDto;
import com.mahumane.omegaStore.model.ProductsModel;
import com.mahumane.omegaStore.repository.ProductsRepository;
import com.mahumane.omegaStore.specification.ProductsSpecification;


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


	public ResponseEntity<?> updateProduct(int id, ProductsDto dto, int user_id) {
		
		if(action.countById(id) == 0) {
			return ResponseEntity.notFound().build();
		}
		
		ProductsModel product = action.findById(id);
		
		if(product.getUser_id() != user_id) {
			return ResponseEntity.badRequest().build();
		}
		
		
		product.setCategory(dto.category());
		product.setCoin_code(dto.coin_code());
		product.setDescription(dto.description());
		product.setDiscounts(dto.discounts());			
		product.setName(dto.name());
		product.setPrice(dto.price());
			
		return ResponseEntity.ok(action.save(product));

	}


	public ResponseEntity<?> removeProduct(int id, int user_id) {
		if(action.findById(id) != null) {
			action.deleteByUserIdAndproductId(user_id, id);
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


	public ResponseEntity<List<ProductsModel>> selectUserProducts(int id) {
		
		return ResponseEntity.ok(action.findProductsByUserId(id));
	}


	public ResponseEntity<List<ProductsModel>> filter(Double minPrice, Double maxPrice, Integer discounts, String category,
			Integer rating) {
		Specification<ProductsModel> spec = Specification
				.where(ProductsSpecification.hasMinPrice(minPrice))
				.and(ProductsSpecification.hasMaxPrice(maxPrice))
				.and(ProductsSpecification.hasDiscounts(discounts))
				.and(ProductsSpecification.hasCategory(category));
		return ResponseEntity.ok(action.findAll(spec));
	}
}
