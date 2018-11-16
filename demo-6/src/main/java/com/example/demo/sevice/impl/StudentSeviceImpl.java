package com.example.demo.sevice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;
import com.example.demo.sevice.StudentSevice;

@Service
public class StudentSeviceImpl implements StudentSevice {

	@Autowired
	StudentRepo studentRepo;
	
	@Override
	public Student findById(long id) {
		// TODO Auto-generated method stub
		return studentRepo.findById(id);
	}

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return studentRepo.findAll();
	}

	@Override
	public boolean createStudent(Student student) {
		System.out.println(student);
		// TODO Auto-generated method stub
		if(studentRepo.findById(student.getId())!=null) {
			return false;
		}else {
			studentRepo.save(student);
			return true;
		}
	}

	@Override
	public boolean updateStudent(Student student) {
		// TODO Auto-generated method stub
		if(this.findById((long)student.getId())!=null) {
			studentRepo.save(student);
			return true;
		}
		else
		{
			return false;
			
		}
	}

	@Override
	public boolean deleteStudent(Student student) {
		// TODO Auto-generated method stub
		if(this.findById((long)student.getId())!=null) {
			studentRepo.delete(student);
			return true;
		}
		else
		{
			return false;
			
		}
	}

}
