package com.example.demo.sevice;

import java.util.List;

import com.example.demo.model.Student;

public interface StudentSevice {
	
	Student findById(long id);
	
	List<Student> getAll();
	
	boolean createStudent(Student student);
	
	boolean updateStudent(Student student);
	
	boolean deleteStudent(Student student);
	
}
