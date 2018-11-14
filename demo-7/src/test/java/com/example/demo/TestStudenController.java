package com.example.demo;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;
import com.example.demo.sevice.StudentSevice;

@RunWith(MockitoJUnitRunner.class)
public class TestStudenController {
	List<Student> students = new ArrayList<>();
	Student student = new Student((long) 1, "test", "test");
	@Mock
	StudentSevice studentSevice;
	@Mock
	StudentRepo studentRepo;
	@InjectMocks
	TestController testController;

	@Test
	public void testGetAll() {
		when(studentSevice.getAll()).then(new Answer<List<Student>>() {

			@Override
			public List<Student> answer(InvocationOnMock invocation) throws Throwable {
				for (int i = 0; i < 10; i++) {
					Student student = new Student();
					student.setId((long) i + 1);
					student.setName("name" + i);
					student.setClassid("classid" + i);
					students.add(student);
				}
				return students;
			}
		});
		List<Student> students2 = testController.getAll();
		assertTrue(students2.size() == 10);
	}

	@Test
	public void testCreateStudent() {
		when(testController.findById(student.getId())).thenReturn(null);
	
		boolean createStudent = testController.createStudent(student);
		assertTrue(createStudent);
		when(testController.findById(student.getId())).thenReturn(student);
		boolean createStudent2 = testController.createStudent(student);
		assertFalse(createStudent2);
		
		
	}
}
