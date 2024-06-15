package com.mahumane.omegaStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahumane.omegaStore.model.ProductsModel;
import com.mahumane.omegaStore.model.UpdateInfoStorageModel;
import com.mahumane.omegaStore.model.UsersModel;
import com.mahumane.omegaStore.service.UpdateInfoStorageService;

@RestController
@RequestMapping("/updates/info")
public class UpdateInfoStorageController {

	@Autowired
	private UpdateInfoStorageService service;
	
	@GetMapping
	public ResponseEntity<List<UpdateInfoStorageModel>> updatesInfo(){
		UsersModel user = (UsersModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return service.getAllUpdatesInfo( user.getId());
	}
	
}
