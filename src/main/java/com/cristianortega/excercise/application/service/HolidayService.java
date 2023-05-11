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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HolidayService {

    private static final Logger log = LoggerFactory.getLogger(HolidayService.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Value("${holiday.url}")
    private String URL;

    @Value("${date.message}")
    private String dateMessageValidation;

    private List<Holiday> holidays = null;

    /**
     * <h1>getHolidays</h1>
     * <p>
     * Metodo para obtener un listado de feriados
     *
     * @author CristianOrtega
     * @version 1.0
     * @since 2023
     */
    public List<Holiday> getHolidays() {
        return holidays;
    }

    /**
     * <h1>loadHolidays</h1>
     * <p>
     * Metodo para cargar una lista de feriados consumiendo una url externa.
     *
     * @author CristianOrtega
     * @version 1.0
     * @since 2023
     */
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

    /**
     * <h1>findByFilters</h1>
     * <p>
     * Metodo para filtrar la lista de feriados en base a parametros
     *
     * @param type Tipo de feriado
     * @param dateFrom fecha desde
     * @param dateTo fecha hasta
     *
     * @author CristianOrtega
     * @version 1.0
     * @since 2023
     */
    public Optional<List<Holiday>> findByFilters(String type, String dateFrom, String dateTo) {
        Stream<Holiday> stream = getHolidays().stream();
        if ((type == null || type.isEmpty()) && (dateFrom == null || dateFrom.isEmpty())  && (dateTo == null || dateTo.isEmpty()) ) {
            return Optional.ofNullable(getHolidays());
        }

        if (type != null && !type.isEmpty()) {
            stream = stream.filter(h -> h.getType().equals(type));
        }

        try {
            if (dateFrom != null && !dateFrom.isEmpty()) {
                LocalDate dateFromConvert = LocalDate.parse(dateFrom, formatter);
                stream = stream.filter(h -> {
                    LocalDate date = LocalDate.parse(h.getDate(), formatter);
                    return date.isAfter(dateFromConvert.minusDays(1));
                });
            }
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(dateMessageValidation, dateFrom, 0);
        }

        try {
            if (dateTo != null && !dateTo.isEmpty()) {
                LocalDate dateToConvert = LocalDate.parse(dateTo, formatter);
                stream = stream.filter(h -> {
                    LocalDate date = LocalDate.parse(h.getDate(), formatter);
                    return date.isBefore(dateToConvert.plusDays(1));
                });
            }
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(dateMessageValidation, dateFrom, 0);
        }

        return Optional.ofNullable(stream.collect(Collectors.toList()));
    }

}
