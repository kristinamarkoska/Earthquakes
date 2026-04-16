package com.example.earthquakes.service;

import com.example.earthquakes.model.Earthquake;
import com.example.earthquakes.repository.EarthquakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EarthquakeService {

    private final EarthquakeRepository repository;
    private final RestTemplate restTemplate;
    private final String URL =
            "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";

    public void fetchAndSave() {

        try {
            Map response = restTemplate.getForObject(URL, Map.class);

            if (response == null || response.get("features") == null) {
                System.out.println("No data from API");
                return;
            }

            List<Map> features = (List<Map>) response.get("features");

            repository.deleteAll();

            for (Map feature : features) {

                Map properties = (Map) feature.get("properties");

                if (properties == null) continue;

                Earthquake eq = new Earthquake();

                eq.setMagnitude(properties.get("mag") != null
                        ? ((Number) properties.get("mag")).doubleValue()
                        : null);

                eq.setPlace((String) properties.get("place"));
                eq.setTitle((String) properties.get("title"));
                eq.setMagType((String) properties.get("magType"));

                eq.setTime(properties.get("time") != null
                        ? ((Number) properties.get("time")).longValue()
                        : null);

                repository.save(eq);
            }

        } catch (Exception e) {
            System.out.println("ERROR while fetching data: " + e.getMessage());
        }
    }
    public List<Map<String, Object>> getAllFormatted() {

        List<Earthquake> list = repository.findAll();

        List<Map<String, Object>> result = new java.util.ArrayList<>();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (Earthquake eq : list) {

            Map<String, Object> map = new HashMap<>();

            map.put("id", eq.getId());
            map.put("magnitude", eq.getMagnitude());
            map.put("place", eq.getPlace());
            map.put("title", eq.getTitle());
            map.put("magType", eq.getMagType());

            LocalDateTime date = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(eq.getTime()),
                    ZoneId.systemDefault()
            );

            map.put("time", date.format(formatter));

            result.add(map);
        }

        return result;
    }
    public List<Earthquake> getAll() {
        return repository.findAll();
    }
    public List<Earthquake> getGreaterThan2() {
        List<Earthquake> result = repository.findByMagnitudeGreaterThan(2.0);

        if (result.isEmpty()) {
            return repository.findAll(); // fallback
        }

        return result;
    }
    public List<Earthquake> getAfterTime(Long time) {
        return repository.findByTimeAfter(time);
    }
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}