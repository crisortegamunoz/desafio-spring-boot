package com.cristianortega.excercise.application.controller;

import com.cristianortega.excercise.application.domain.Holiday;
import com.cristianortega.excercise.application.service.HolidayService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/holidays")
public class HolidayController {

    private HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/")
    public List<Holiday> getHolidays() {
        return holidayService.getHolidays();
    }

    @ApiOperation(value = "Get Holidays by type, dateFrom and dateTo", response = Holiday.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Holidays found"),
            @ApiResponse(code = 404, message = "Holidays not found")
    })
    @GetMapping("/findByFilters")
    public ResponseEntity<List<Holiday>> findByFilters(
            @ApiParam(value = "Type of holiday")
            @RequestParam("type") String type,
            @ApiParam(value = "Date of the holiday")
            @RequestParam("dateFrom") Date dateFrom,
            @ApiParam(value = "Date of the holiday")
            @RequestParam("dateTo") Date dateTo) {
        return holidayService.findByFilters(type, dateFrom, dateTo)
                .map(holidays -> new ResponseEntity<>(holidays, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/load-holidays")
    public List<Holiday> loadHolidays() {
        holidayService.loadHolidays();
        return holidayService.getHolidays();
    }

}
