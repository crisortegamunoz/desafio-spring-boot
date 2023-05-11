package com.cristianortega.excercise.application.init;

import com.cristianortega.excercise.application.service.HolidayService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RunnerService implements ApplicationRunner {

    private HolidayService holidayService;

    public RunnerService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        holidayService.loadHolidays();
    }
}
