package com.mahumane.omegaStore.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "update_info_storage")
public class UpdateInfoStorageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int product_id;
	
	private int user_id;
	
	@CreationTimestamp
	private LocalDateTime update_at;

	public UpdateInfoStorageModel(int id, int product_id, int user_id, LocalDateTime update_at) {
		super();
		this.id = id;
		this.product_id = product_id;
		this.user_id = user_id;
		this.update_at = update_at;
	}
	
	public UpdateInfoStorageModel() {}

	public UpdateInfoStorageModel(int product_id, int user_id) {
		this.product_id = product_id;
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public LocalDateTime getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(LocalDateTime update_at) {
		this.update_at = update_at;
	}
	
	
	
}
