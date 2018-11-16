package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="classid")
	private String classid;

	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "student_subject", 
        joinColumns = { @JoinColumn(name="idStudent")}, 
        inverseJoinColumns = { @JoinColumn(name = "idsubject") }
    )
	Set<Subject> subjects = new HashSet<Subject>();

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(long id, String name, String classid, Set<Subject> subjects) {
		super();
		this.id = id;
		this.name = name;
		this.classid = classid;
		this.subjects = subjects;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", classid=" + classid + ", subjects=" + subjects + "]";
	}

	
	
	
	
		
}
