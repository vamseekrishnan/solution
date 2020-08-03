package com.assessment.solution.cities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CitiesService.class)
public class CitiesServiceTest {

    @Autowired
    CitiesService citiesService;

    @Test
    public void test_isConnected_withValidConnectedCities() {
        assertTrue(citiesService.isConnected("Boston", "Newark"));
    }

    @Test
    public void test_isConnected_withInValidConnectedCities() {
        assertFalse(citiesService.isConnected("Boston", "Dallas"));
    }
}
