package com.mcjag.daytoday;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import jdk.jfr.Timestamp;

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

	@Test
	void testTomorrow(){
		assertThat(this.controller.tomorrow()).contains("Tomorrow");
	}
	
	@Test
	void testByeFunc()
	{
		Assert.assertEquals("Bye World!", controller.bye("World"));
	}

	@Test
	void testEmail(){
		assertThat(this.controller.email()).contains("done");
	}
}
