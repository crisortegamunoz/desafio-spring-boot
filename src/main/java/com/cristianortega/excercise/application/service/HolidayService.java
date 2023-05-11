package com.cristianortega.excercise.application.service;

import com.cristianortega.excercise.application.domain.Holiday;
import com.cristianortega.excercise.application.domain.HolidayResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {

    private static final Logger log = LoggerFactory.getLogger(HolidayService.class);

    @Value("${holiday.url}")
    private String URL;

    private List<Holiday> holidays = null;

    public List<Holiday> getHolidays() {
        return holidays;
    }

    public void loadHolidays() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        log.info("endpoint " + URL);
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class).getBody();
        log.info("response: " + json);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            HolidayResponse holidayResponse = objectMapper.readValue(json, HolidayResponse.class);
            if (holidayResponse != null) {
                holidays = holidayResponse.getData();
            }
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public Optional<List<Holiday>> findByFilters(String type, String dateFrom, String dateTo) {
        return null;
    }

    private RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
