package com.cristianortega.excercise.application.service;

import com.cristianortega.excercise.application.domain.Holiday;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {

    @Value("${holiday.url}")
    private String URL;

    private List<Holiday> holidays = null;

    public List<Holiday> getHolidays() {
        return holidays;
    }

    public void loadHolidays() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Holiday[]> response = restTemplate.getForEntity(URL, Holiday[].class);
        Holiday[] feriados = response.getBody();
        holidays = Arrays.asList(feriados);
    }

    public Optional<List<Holiday>> findByFilters(String type, Date dateFrom, Date dateTo) {
        return null;
    }
}
