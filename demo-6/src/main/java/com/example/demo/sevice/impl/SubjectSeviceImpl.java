package com.example.demo.sevice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Subject;
import com.example.demo.repo.SubjectRepo;
import com.example.demo.sevice.SubjectSevice;

@Service
public class SubjectSeviceImpl implements SubjectSevice {
	@Autowired
	SubjectRepo subjectRepo;

	@Override
	public Subject findById(long id) {
		// TODO Auto-generated method stub
		return subjectRepo.findById(id);
	}

	@Override
	public List<Subject> getAll() {
		// TODO Auto-generated method stub
		return subjectRepo.findAll();
	}

	@Override
	public boolean createStudent(Subject subject) {
		// TODO Auto-generated method stub
		if (this.findById(subject.getId()) == null) {
			subjectRepo.save(subject);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateStudent(Subject subject) {
		// TODO Auto-generated method stub
		if (this.findById(subject.getId()) != null) {
			subjectRepo.save(subject);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteStudent(Subject subject) {
		// TODO Auto-generated method stub
		if (this.findById(subject.getId()) != null) {
				subjectRepo.delete(subject);
				return true;
		}
		return false;
	}

}
