package com.fullack.flight_api.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Util {
	public static String dateToString(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		String formattedDate = date.format(formatter);
		return formattedDate;
	}

	public static String timeToString(LocalTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String formattedTime = time.format(formatter);
		return formattedTime;
	}

	public static String dateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
	}

	public static LocalDate stringToDate(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		LocalDate localDate = LocalDate.parse(dateString, formatter);
		return localDate;
	}
	
	public static BigDecimal stringToBigDecimal(String decimalString) {
		BigDecimal decimal = new BigDecimal(decimalString);
		return decimal;
	}
}
