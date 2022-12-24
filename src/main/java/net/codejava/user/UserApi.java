package net.codejava.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserApi {

	private UserRepository repo;

	public UserApi(UserRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping
	public List<User> listAll() {
		return repo.findAll();
	}
	
	@PostMapping
	public User addOne(@RequestBody @Valid User user) {
		return repo.save(user);
	}
	
}
