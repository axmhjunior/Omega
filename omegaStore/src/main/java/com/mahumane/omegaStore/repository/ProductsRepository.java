package com.mahumane.omegaStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mahumane.omegaStore.model.ProductsModel;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsModel, Integer>, JpaSpecificationExecutor<ProductsModel>{



	ProductsModel findById(int id);

	List<ProductsModel> findByName(String name);

	
	@Modifying
	@Transactional
	@Query(value = "Delete from products where user_id=:user_id AND id =:id ", nativeQuery = true)
	void deleteByUserIdAndproductId(@Param("user_id") int user_id,@Param("id") int id);

	@Query(value="select * from products where user_id= :id", nativeQuery=true)
	List<ProductsModel> findProductsByUserId(@Param("id") int id);

	int countById(int id);
	
	
	@Query(value ="select * from products  where "
			+ "(:minPrice IS NULL OR :minPrice IS NOT NULL AND price >= :minPrice) AND "
			+ "(:maxPrice IS NULL OR :maxPrice IS NOT NULL AND price <= :maxPrice) "
			+ "(:discounts IS NULL OR :discounts  IS NOT NULL AND discounts = :discounts) "
			+ "AND (:category IS NULL OR :category IS NOT NULL AND category = :category)", nativeQuery= true)
	List<ProductsModel> findByFilter(
			@Param("minPrice") Double minPrice
			,@Param("maxPrice") Double maxPrice,
			@Param("discounts") Integer discounts,
			@Param("category") String category);




	

}
