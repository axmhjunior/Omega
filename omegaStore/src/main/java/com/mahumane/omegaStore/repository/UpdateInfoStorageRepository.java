package com.mahumane.omegaStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mahumane.omegaStore.model.UpdateInfoStorageModel;

@Repository
public interface UpdateInfoStorageRepository extends JpaRepository<UpdateInfoStorageModel, Integer>{

	@Query(value = "select * from update_info_storage where user_id= :user_id", nativeQuery = true)
	List<UpdateInfoStorageModel> findByUserId(@Param("user_id") int user_id);

}
