package com.example.earthquakes.repository;

import com.example.earthquakes.model.Earthquake;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EarthquakeRepository extends JpaRepository<Earthquake, Long> {

    List<Earthquake> findByMagnitudeGreaterThan(Double mag);
    List<Earthquake> findByTimeAfter(Long time);

}