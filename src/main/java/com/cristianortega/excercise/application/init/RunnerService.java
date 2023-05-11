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

    /**
     * <h1>run</h1>
     * <p>
     * Metodo para hacer el cargado de feriados cuando se inicie la aplicacion
     *
     * @param args Tipo de feriado
     *
     * @author CristianOrtega
     * @version 1.0
     * @since 2023
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        holidayService.loadHolidays();
    }
}
