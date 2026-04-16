package com.example.earthquakes;

import com.example.earthquakes.service.EarthquakeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EarthquakeServiceTest {

    @Autowired
    private EarthquakeService service;

    @Test
    void testFetchAndSaveDoesNotCrash() {
        assertDoesNotThrow(() -> service.fetchAndSave());
    }

    @Test
    void testGetAllReturnsList() {
        var result = service.getAll();
        assertNotNull(result);
    }

    @Test
    void testGetGreaterThan2Works() {
        var result = service.getGreaterThan2();
        assertNotNull(result);
    }
}