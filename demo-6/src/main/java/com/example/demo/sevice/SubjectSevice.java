package com.example.demo.sevice;

import java.util.List;

import com.example.demo.model.Subject;


public interface SubjectSevice {
	Subject findById(long id);
	
	List<Subject> getAll();
	
	boolean createStudent(Subject subject);
	
	boolean updateStudent(Subject subject);
	
	boolean deleteStudent(Subject subject);
}
