package com.assessment.solution;

import com.assessment.solution.cities.CitiesController;
import com.assessment.solution.cities.CitiesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SolutionApplicationTests {

	@Autowired
	ApplicationContext ac;

	@Test
	void contextLoads() {
		CitiesService citiesService = ac.getBean(CitiesService.class);
		assertNotNull(citiesService);

		CitiesController citiesController = ac.getBean(CitiesController.class);
		assertNotNull(citiesController);
	}

}
