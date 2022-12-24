package net.codejava;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.codejava.product.Product;
import net.codejava.product.ProductRepository;

@Configuration
public class DatabaseLoader {
	private ProductRepository productRepo;
	
	public DatabaseLoader(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@Bean
	CommandLineRunner initDatabase() {
		return args -> {
			Product product1 = new Product("iPhone 15", 1099);
			Product product2 = new Product("Amazon Kindle Fire", 159);
			
			productRepo.saveAll(List.of(product1, product2));
			
			System.out.println("Database initialized");
		};
	}
}
