package com.example.demo.repo;

import javax.persistence.JoinColumn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Subject;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Long> {
	Subject findById(long id);
}
