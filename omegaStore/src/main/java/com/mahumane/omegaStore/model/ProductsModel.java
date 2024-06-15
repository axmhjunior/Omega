package com.mahumane.omegaStore.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.mahumane.omegaStore.dto.ProductsDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="products")
public class ProductsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String name;
	
	private String description;
	
	private String category;
	
	private String coin_code;
	
	private Double price;
	
	private int user_id;
	
	@CreationTimestamp
	private LocalDateTime creation_date_time;

	private int discounts;
	
	public ProductsModel(int id, String name, String description, String category, String coin_code, Double price,
			LocalDateTime creation_date_time, int discounts, int user_id) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.coin_code = coin_code;
		this.price = price;
		this.creation_date_time = creation_date_time;
		this.discounts = discounts;
		this.user_id = user_id;
	}
	
	public ProductsModel(ProductsDto dto, int user_id) {
		this.id = dto.id();
		this.name = dto.name();
		this.description = dto.description();
		this.category = dto.category();
		this.coin_code = dto.coin_code();
		this.price = dto.price();
		this.discounts = dto.discounts();
		this.user_id = user_id;
	}
	public ProductsModel(ProductsDto dto) {
		this.id = dto.id();
		this.name = dto.name();
		this.description = dto.description();
		this.category = dto.category();
		this.coin_code = dto.coin_code();
		this.price = dto.price();
		this.discounts = dto.discounts();
	}

	public ProductsModel() {
		super();
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getDiscounts() {
		return discounts;
	}

	public void setDiscounts(int discounts) {
		this.discounts = discounts;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCoin_code() {
		return coin_code;
	}

	public void setCoin_code(String coin_code) {
		this.coin_code = coin_code;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDateTime getCreation_date_time() {
		return creation_date_time;
	}

	public void setCreation_date_time(LocalDateTime creation_date_time) {
		this.creation_date_time = creation_date_time;
	}
	
}
