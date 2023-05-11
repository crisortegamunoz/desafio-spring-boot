package com.cristianortega.excercise;

import com.cristianortega.excercise.application.domain.Holiday;
import com.cristianortega.excercise.application.service.HolidayService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ExcerciseApplicationTests {

	private HolidayService holidayService;

	public ExcerciseApplicationTests(HolidayService holidayService) {
		this.holidayService = holidayService;
	}

	@Test
	void testLoadHolidayList() {
		List<Holiday> holidays = holidayService.getHolidays();
		assertNotNull(holidays);
		assertEquals(17, holidays.size());
	}

	@Test
	void testFindByType() {

	}

}
