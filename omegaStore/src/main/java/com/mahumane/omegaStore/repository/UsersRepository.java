package com.mahumane.omegaStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahumane.omegaStore.model.UsersModel;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Integer>{

	UsersModel findByName(String name);

}
