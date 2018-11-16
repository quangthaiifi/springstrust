package com.example.demo;

import java.util.List;

import javax.persistence.GeneratedValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.sevice.StudentSevice;

@RestController
public class StudentController {
	@Autowired
	StudentSevice studentSevice;

	@GetMapping(value = "/")
	public String hello(Model model) {
		return "good Job";
	}

	@GetMapping(value = "/students")
	public List<Student> getAll() {
		return studentSevice.getAll();
	}

	@GetMapping(value = "/student/{id}")
	public Student findById(@PathVariable long id) {
		return studentSevice.findById(id);

	}
	
	@PostMapping(value = "/student")
	public boolean createStudent(@RequestBody Student student) {
		
		if (this.findById(student.getId()) == null) {
			studentSevice.createStudent(student);
			return true;
		}
		return false;

	}

	@PutMapping(value = "/student")
	public boolean updateStudent(@RequestBody Student student) {
		System.out.println(this.findById((long) student.getId()));
		if (this.findById((long) student.getId()) != null) {
				studentSevice.updateStudent(student);
				return true;
		}return false;
	}
}
