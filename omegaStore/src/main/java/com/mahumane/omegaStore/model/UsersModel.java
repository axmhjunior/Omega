package com.mahumane.omegaStore.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mahumane.omegaStore.dto.UsersDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;





@Entity
@Table(name= "users")
public class UsersModel implements UserDetails{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private String name;
	private String pass;
	private String roles;
	
	
	public UsersModel(int id, String name, String pass, String roles) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.roles = roles;
	}
	
	public UsersModel() {}

	
	public UsersModel(UsersDto dto) {
		this.id = dto.id();
	}

	public UsersModel(String name, String pass, String roles) {
		this.name = name;
		this.pass = pass;
		this.roles = roles;
	}


	public UsersModel( UsersDto dto, String encryptedPassword, String roles) {
		// TODO Auto-generated constructor stub
		this.id = dto.id();
		this.name = dto.name();
		this.pass = encryptedPassword;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	


	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
	return List.of(new SimpleGrantedAuthority("ROLE_"+roles));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return pass;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
