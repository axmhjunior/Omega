package com.mahumane.omegaStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mahumane.omegaStore.dto.TokenDto;
import com.mahumane.omegaStore.dto.UsersDto;
import com.mahumane.omegaStore.model.UsersModel;
import com.mahumane.omegaStore.service.TokenService;
import com.mahumane.omegaStore.service.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UsersController {

	@Autowired
	private UsersService service;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid UsersDto dto){
		var userPass = new UsernamePasswordAuthenticationToken(dto.name(), dto.pass());
		
		var authenticate = this.manager.authenticate(userPass);
		
		var token = tokenService.generateTokenJWT((UsersModel) authenticate.getPrincipal());
		
		return ResponseEntity.ok(new TokenDto(token));
	}
	
	
	@GetMapping
	public ResponseEntity<?> selectUsers(){
		return service.selectUsers();
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> searchByUsername(@RequestParam String q){
		return service.searchByUsername(q);
	}
	

	@PostMapping("/register")
	public ResponseEntity<?> insertUser(@RequestBody @Valid UsersDto dto){
		UsersModel user = new UsersModel(dto);
		return service.insertUser(user);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody @Valid UsersDto dto){
		UsersModel user = new UsersModel(dto);
		return service.updateUser(user);
	}
	
	
}
