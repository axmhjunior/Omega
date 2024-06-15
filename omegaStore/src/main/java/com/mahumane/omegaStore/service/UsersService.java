package com.mahumane.omegaStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mahumane.omegaStore.model.UsersModel;
import com.mahumane.omegaStore.repository.UsersRepository;

@Service
public class UsersService implements UserDetailsService{

	@Autowired
	private UsersRepository action;
	
	public ResponseEntity<?> insertUser(UsersModel user) {
		// TODO Auto-generated method stub
		if(action.findByName(user.getName()) != null) {
		return ResponseEntity.badRequest().build();
		}
		
		return new ResponseEntity<>(action.save(user), HttpStatus.CREATED);
	}

	public ResponseEntity<?> updateUser(UsersModel user) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(action.save(user));
	}

	public ResponseEntity<?> selectUsers() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(action.findAll());
	}

	public ResponseEntity<?> searchByUsername(String name) {
		// TODO Auto-generated method stub
		if(action.findByName(name) != null) {
			return ResponseEntity.ok(action.findByName(name));
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return action.findByName(username);
	}

	public ResponseEntity<?> deleteUser(int id) {
		if(action.findById(id) != null) {
			UsersModel usersModel = action.findById(id);
			action.delete(usersModel);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	
}
