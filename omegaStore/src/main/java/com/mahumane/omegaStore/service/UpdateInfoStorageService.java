package com.mahumane.omegaStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.mahumane.omegaStore.model.UpdateInfoStorageModel;
import com.mahumane.omegaStore.repository.UpdateInfoStorageRepository;

@Service
public class UpdateInfoStorageService {

	
	@Autowired
	private UpdateInfoStorageRepository action;
	
	public ResponseEntity<List<UpdateInfoStorageModel>> getAllUpdatesInfo( int user_id) {
		return ResponseEntity.ok(action.findByUserId(user_id));
	}
	
	public ResponseEntity<?> insertUpdateInfo(UpdateInfoStorageModel model){
		return new ResponseEntity<>(action.save(model), HttpStatus.CREATED);
	}

}
