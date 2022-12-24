package net.codejava.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "products")
public class Product {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 512, unique = true)
//	@NotBlank(message = "Product name must not be blank")
	@Length(min = 5, max = 512, message = "Product name must have 5-512 characters")
	private String name;
	
	@Min(value = 10, message = "Product price must be greater than 9")
	@Max(value = 9999, message = "Product price must be less than 10000")
	private float price;
	
	public Product() { }
	
	public Product(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
