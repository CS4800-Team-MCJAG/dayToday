package com.mcjag.daytoday;

import com.mcjag.daytoday.providers.EventProvider;
import com.mcjag.daytoday.providers.WelcomeProvider;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import java.io.IOException;

@SpringBootTest
class DayToDayApplicationTests {

	@Autowired
	private WelcomeProvider welcomeProvider;
	@Test
	void contextLoads() {
	}

}
