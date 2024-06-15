create table products (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(255) NOT NULL,
	category VARCHAR(100) NOT NULL,
	coin_code VARCHAR(100) NOT NULL,
	price DECIMAL(10, 2) NOT NULL,
	discounts INT,
	user_id INT,
	creation_date_time DATETIME DEFAULT CURRENT_TIMESTAMP
);