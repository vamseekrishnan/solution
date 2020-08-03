package com.assessment.solution.cities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Cities Rest Service Controller
 *
 * @author Vamsee Naranam
 * @since 07/30/2020
 */

@RestController
@RequestMapping("/")
public class CitiesController {

    private static final Logger LOG = LoggerFactory.getLogger(CitiesController.class);

    /**
     * Service to check if the given cities are connected.
     * @param origin
     * @param destination
     * @return String yes|no
     */
    @RequestMapping(value = "connected", method = RequestMethod.GET)
    @ResponseBody
    public String get(@RequestParam(name = "origin") String origin, @RequestParam(name = "destination") String destination) {
        if(isBlankString(origin) || isBlankString(origin)) {
            return "Invalid Request";
        }
        LOG.info(String.format("requested cities: %s and %s", origin, destination));
        String response = cityService.isConnected(origin, destination) ? "Yes": "No";
        LOG.info(response);
        return response;
    }

    /**
     * Checks if string is empty.
     * @param string
     * @return
     */
    private boolean isBlankString(String string) {
        return string == null || string.trim().isEmpty();
    }


    @Autowired
    private CitiesService cityService;

}




