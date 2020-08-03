package com.assessment.solution.cities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

/**
 * Cities Service class responsible to check if given cities are connected
 * Also loads initial data during post construct.
 *
 * @author Vamsee Naranam
 * @since 07/30/2020
 */

@Component
public class CitiesService {

    private static final Logger LOG = LoggerFactory.getLogger(CitiesService.class);
    static HashMap<String, LinkedList<String>> cityGraph = new HashMap<>();
    /**
     * Load city data from file 'city.txt'
     *
     * @throws Exception
     */
    @PostConstruct
    public void loadCitiesData() throws Exception {
        try {
            List<String> lines = Files.readAllLines(Paths.get(res.getURI()),
                    StandardCharsets.UTF_8);

            for (String line : lines) {
                String[] connectedCities = line.split(",");
                //TODO check if data is in expected format
                String origin = connectedCities[0];
                String dest = connectedCities[1];
                LinkedList<String> sourceList = cityGraph.getOrDefault(origin,  new LinkedList<>());
                sourceList.add(dest);
                cityGraph.put(origin, sourceList);
                LinkedList<String> destList = cityGraph.getOrDefault(origin,  new LinkedList<>());
                cityGraph.put(dest,destList);
                cityGraph.get(dest).add(origin);
            }
        } catch (IOException ioe) {
            LOG.warn("error while loading connected cities data");
            throw new Exception("Exception occurred while read cities data.");
        }
    }

    /**
     *  Method to check if cities are connected or not using BFS
     *  TimeComplexity O(V+E)
     * @param s
     * @param d
     * @return
     */
    public boolean isConnected(String s, String d)
    {
        Map<String, Boolean> visited = new HashMap<>();
        LinkedList<String> queue = new LinkedList<>();
        cityGraph.keySet().forEach(city -> visited.put(city, false));
        visited.put(s, true);
        queue.add(s);
        Iterator<String> i;
        String tempCity;
        while (queue.size()!=0)
        {
            tempCity = queue.poll();
            i = cityGraph.getOrDefault(tempCity, new LinkedList<>()).listIterator();
            String tempFoundCity;
            while (i.hasNext())
            {
                tempFoundCity = i.next();
                if (d.equals(tempFoundCity))
                    return true;
                if (!visited.get(tempFoundCity))
                {
                    visited.put(tempFoundCity, true);
                    queue.add(tempFoundCity);
                }
            }
        }
        return false;
    }

    @Value("classpath:city.txt")
    private Resource res;

}
