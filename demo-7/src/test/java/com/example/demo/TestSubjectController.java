package com.example.demo;

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

import com.example.demo.model.Subject;
import com.example.demo.sevice.SubjectSevice;
@RunWith(MockitoJUnitRunner.class)
public class TestSubjectController {

	List<Subject> subjects ;

	Subject subject = new Subject(1, "test", 2);

	@Mock
	SubjectSevice subjectSevice;

	@InjectMocks
	SubjectController subjectController;

	public List<Subject> init() {
		subjects = new ArrayList<Subject>();
		for (int i = 0; i < 10; i++) {
			subject.setId(i);
			subject.setName("Subject_" + i);
			subject.setUnit(i);
			subjects.add(subject);
		}
		return subjects;
	}

	@Test
	public void testGetAll() {
		when(subjectSevice.getAll()).then(new Answer<List<Subject>>() {

			@Override
			public List<Subject> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println(init());
				return init();
				
			}

		});
		List<Subject> subjects2= subjectController.getAll();
		System.out.println(subjects2.size());

		assertTrue(subjects2.size()==10);
	}
	@Test
	public void testCreateSubject() {
		when(subjectSevice.findById(subject.getId())).thenReturn(null);
		
		assertTrue(subjectController.createSubject(subject));
	}

}
