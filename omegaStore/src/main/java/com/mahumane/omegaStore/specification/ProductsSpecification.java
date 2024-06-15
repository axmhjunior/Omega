package com.mahumane.omegaStore.specification;

import org.springframework.data.jpa.domain.Specification;

import com.mahumane.omegaStore.model.ProductsModel;

public class ProductsSpecification {
	public static Specification<ProductsModel> hasMinPrice(Double minPrice){
		return (root, query, criteriaBuilder) ->
			minPrice == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
	}
	
	public static Specification<ProductsModel> hasMaxPrice(Double maxPrice){
		return (root, query, criteriaBuilder) ->
		maxPrice == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
	}
	
	public static Specification<ProductsModel> hasCategory(String category){
		return (root, query, criteriaBuilder) -> 
		(category == null || category.isEmpty()) ? null : criteriaBuilder.equal(root.get("category"), category);
	}
	
	public static Specification<ProductsModel> hasDiscounts(Integer discounts){
		return (root, query, criteriaBuilder) -> 
		discounts == null ? null : criteriaBuilder.equal(root.get("discounts"), discounts);
	}
}
