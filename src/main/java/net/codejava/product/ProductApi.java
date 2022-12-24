package net.codejava.product;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductApi {

	private ProductRepository repo;

	public ProductApi(ProductRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping
	public List<Product> listAll() {
		return repo.findAll();
	}
	
	@PostMapping
	public Product addOne(@RequestBody @Valid Product product) {
		return repo.save(product);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") Integer id) {
		Optional<Product> result = repo.findById(id);
		if (result.isPresent()) {
			return new ResponseEntity<>(result.get(), HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> replaceOne(@PathVariable("id") Integer id, @RequestBody @Valid Product product) {
		if (!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}	
		
		product.setId(id);
		Product savedProduct = repo.save(product);
		
		return new ResponseEntity<>(savedProduct, HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updatePrice(@PathVariable("id") Integer id, @RequestBody Product product) {
		Optional<Product> result = repo.findById(id);
		
		if (!result.isPresent()) {
			return ResponseEntity.notFound().build();
		}	

		Product productInDB = result.get();
		productInDB.setPrice(product.getPrice());
		Product savedProduct = repo.save(productInDB);
		
		return new ResponseEntity<>(savedProduct, HttpStatus.OK);
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOne(@PathVariable("id") Integer id) {
		if (!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		repo.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
