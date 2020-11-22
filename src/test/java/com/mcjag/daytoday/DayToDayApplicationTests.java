package com.mcjag.daytoday;

import com.mcjag.daytoday.providers.EventProvider;
import com.mcjag.daytoday.providers.WelcomeProvider;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import java.io.IOException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)

class DayToDayApplicationTests {

	@Test
	void contextLoads() {
	}

}
