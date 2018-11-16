package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subject")
public class Subject {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="unit")
	private int unit;

	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subject(long id, String name, int unit) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
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

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", unit=" + unit + "]";
	}
	
	
	
}
