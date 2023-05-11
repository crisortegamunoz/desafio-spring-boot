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
		List<Holiday> holidays = holidayService.findByFilters("Religioso", null, null).get();
		assertNotNull(holidays);
		assertEquals(9, holidays.size());
	}

	@Test
	void testFindByDateFrom() {
		List<Holiday> holidays = holidayService.findByFilters(null, "2023-12-08", null).get();
		assertNotNull(holidays);
		assertEquals(2, holidays.size());
	}

	@Test
	void testFindByDateTo() {
		List<Holiday> holidays = holidayService.findByFilters(null, "2023-12-08", null).get();
		assertNotNull(holidays);
		assertEquals(2, holidays.size());
	}

	@Test
	void testFindByDateFromAndDateTo() {
		List<Holiday> holidays = holidayService.findByFilters(null, null, "2023-04-07").get();
		assertNotNull(holidays);
		assertEquals(3, holidays.size());
	}

}
