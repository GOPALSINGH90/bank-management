package com.cybrilla.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Uitility {
	public static LocalDateTime getCurrentDateAndTimeInDate() {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String strDate = formatter.format(date);

		Date formatedDate = null;
		try {
			formatedDate = stringToDate(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Instant instant = formatedDate.toInstant();
		ZonedDateTime zone = instant.atZone(ZoneId.of("Asia/Kolkata"));
		LocalDateTime givenDate = zone.toLocalDateTime();
		return givenDate;
	}

	public static Date stringToDate(String dob) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = formatter.parse(dob);
		return date;
	}
}
