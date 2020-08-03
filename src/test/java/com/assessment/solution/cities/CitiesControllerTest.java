package com.assessment.solution.cities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CitiesController.class)
public class CitiesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CitiesService citiesService;

    @Test
    public void test_Controller_withValidConnectedCities() throws Exception {
        given(this.citiesService.isConnected("Boston", "Newark" ))
                .willReturn(true);
        this.mvc.perform(get("/connected?origin=Boston&destination=Newark"))
                .andExpect(status().isOk()).andExpect(content().string("Yes"));
    }

    @Test
    public void test_Controller_withEmptyCities() throws Exception {
        this.mvc.perform(get("/connected?origin=&destination="))
                .andExpect(status().isOk()).andExpect(content().string("Invalid Request"));
    }

    @Test
    public void test_Controller_withNoParameters() throws Exception {
        this.mvc.perform(get("/connected"))
                .andExpect(status().is(400));
    }

}
