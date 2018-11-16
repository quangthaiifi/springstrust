package com.example.demo;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import com.example.demo.repo.StudentRepo;
import com.example.demo.sevice.StudentSevice;

@RunWith(MockitoJUnitRunner.class)
public class TestStudenController {
	List<Student> students = new ArrayList<>();
	Subject subject = new Subject(1,"toan",2);
	
	Set<Subject> subjects = new HashSet<Subject>();
	
	Student student = new Student(1,"Thai","testter",subjects);
	@Mock
	StudentSevice studentSevice;
	
	@InjectMocks
	StudentController testController;
	
	
	public List<Student> init() {
		for (int i = 0; i < 10; i++) {
			Student student = new Student();
			student.setId((long) i + 1);
			student.setName("name" + i);
			student.setClassid("classid" + i);
			subjects.add(subject);
			student.setSubjects(subjects);
			students.add(student);
		}
		return students;
	}
	
	@Test
	public void testGetAll() {
		when(studentSevice.getAll()).then(new Answer<List<Student>>() {
			
			@Override
			public List<Student> answer(InvocationOnMock invocation) throws Throwable {
				return init();
			}
		});
		List<Student> students2 = testController.getAll();
		System.out.println(students2);
		assertTrue(students2.size() == 10);
	}

	@Test
	public void testCreateStudentSussess() {
		when(testController.findById(student.getId())).thenReturn(null);
		boolean createStudent = testController.createStudent(student);
		assertTrue(createStudent);
	}
	
	@Test
	public void testCreateStudentFalse() {
		when(testController.findById(student.getId())).thenReturn(student);
		boolean createStudent2 = testController.createStudent(student);
		assertFalse("Can not create student with this case",createStudent2);
	}

}
