package com.mcjag.daytoday;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.IOException;


@SpringBootTest
class DayToDayApplicationTests {

	@Autowired
	private DayToDayApplication controller;

	@Test
	void contextLoads() {
	}

	@Test
	void test2() {
		Assert.assertEquals("Hello World!", this.controller.hello("World"));
	}

	@Test
	void test() throws IOException {
		Boolean res = this.controller.onThisDay().contains("On this day: ");
		Assert.assertTrue(res);
	}

}
