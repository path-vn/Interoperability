package com.globits.hivimportcsadapter.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class HandleDateUtils {
	public Date getDateFromString(String value) {
		if(value.isEmpty())
			return null;

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// DateTimeFormat.forPattern("dd/MM/yyyy");

		// Date date = null;
		Date date = null;

		try{
			date = dateFormat.parse(value);
		} catch(ParseException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;
	}

	public String getDate(Date date) {
		if(date == null)
			return null;

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		return dateFormat.format(date);
	}
}
