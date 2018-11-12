package com.example.demofa;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;

public class ReadExcelTest {

	@Test
	public void getAllModelTestSoAm()
	{
		Readexcel readexcel = new Readexcel();
		readexcel.getAllModel(-1);
		
		
	}
	@Test
	public void getAllModelTestSoLon() {
		
		Readexcel readexcel = new Readexcel();
		readexcel.getAllModel(1);
	}
	@Test(timeout=2000)
	public void getAllModelTestSoDung() {
		
		Readexcel readexcel = new Readexcel();
		readexcel.getAllModel(2);
	}
	@Test
	public void checkNum() {
		Readexcel readexcel = new Readexcel();
		readexcel.getAllModel(1);
		Assert.assertThat(readexcel.getAllModel(0).get(10),is(notNullValue()));
		Assert.assertTrue(readexcel.getAllModel(1).size()==8);

	}
}
