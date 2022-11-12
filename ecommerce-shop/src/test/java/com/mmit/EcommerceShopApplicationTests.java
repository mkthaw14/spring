package com.mmit;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class EcommerceShopApplicationTests {


	
	@Test
	void contextLoads() {
		String pattern = "hh:mm";
		///LocalTime time = LocalTime.now();
		LocalDateTime date = LocalDateTime.now();
		LocalDateTime date2 = LocalDateTime.parse("2022-11-05T21:52:29.301119800");
		
		System.out.println("date hour: " + date.getHour());
		System.out.println("date2 hour: " + date2.getHour());
		int diff = date.getHour() - date2.getHour();
		System.out.println("date diff : " + diff);
		
		//System.out.println("currentDate : " + date);
		//System.out.println("currentTime : " + time.format(DateTimeFormatter.ofPattern(pattern)));
	}

}
