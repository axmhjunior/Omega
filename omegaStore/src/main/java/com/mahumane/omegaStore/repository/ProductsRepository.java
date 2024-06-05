package com.mahumane.omegaStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahumane.omegaStore.model.ProductsModel;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsModel, Integer>{

	ProductsModel findById(int id);

	List<ProductsModel> findByName(String name);


	

}
