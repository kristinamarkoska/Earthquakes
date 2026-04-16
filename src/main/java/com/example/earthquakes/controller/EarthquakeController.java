package com.example.earthquakes.controller;

import com.example.earthquakes.model.Earthquake;
import com.example.earthquakes.service.EarthquakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/earthquakes")
@RequiredArgsConstructor
public class EarthquakeController {

    private final EarthquakeService service;

    @GetMapping("/fetch")
    public String fetchData() {
        service.fetchAndSave();
        return "Data fetched!";
    }
    @GetMapping
    public List<Earthquake> getAll() {
        return service.getAll();
    }
    @GetMapping("/strong")
    public List<Earthquake> strong() {
        return service.getGreaterThan2();
    }
    @GetMapping("/after")
    public List<Earthquake> afterTime(@RequestParam Long time) {
        return service.getAfterTime(time);
    }
    @GetMapping("/formatted")
    public List<Map<String, Object>> getFormatted() {
        return service.getAllFormatted();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}