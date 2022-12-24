package net.codejava.student;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
public class StudentApi {
	private StudentRepository repo;

	public StudentApi(StudentRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping
	public List<Student> listAll() {
		return repo.findAll();
	}
	
	@PostMapping
	public Student addOne(@RequestBody @Valid Student student) {
		return repo.save(student);
	}
}
