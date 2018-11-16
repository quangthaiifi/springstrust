package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Subject;
import com.example.demo.sevice.SubjectSevice;

@Controller
public class SubjectController {
	
	@Autowired
	SubjectSevice subjectSevice;

	@GetMapping(value = "subjects")
	public List<Subject> getAll() {

		return subjectSevice.getAll();
	}

	@GetMapping(value = "subject/{id}")
	public Subject findById(@PathVariable long id) {
		return subjectSevice.findById(id);
	}

	@PostMapping(value = "subject")
	public boolean createSubject ( @RequestBody Subject subject) {
		return subjectSevice.createStudent(subject);
	}

	@PutMapping(value = "subject")
	public boolean updateSubject(Subject subject) {
		return subjectSevice.updateStudent(subject);
	}

	@DeleteMapping(value = "subject")
	public boolean deleteSubject(Subject subject) {
		return subjectSevice.deleteStudent(subject);

	}

}
