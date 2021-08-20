package com.globits.cbs.deidentification.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

@Service
public class HandleTimeUtils {
	public int getHours(double time) {
		double miliseconds = time * DateUtils.MILLIS_PER_DAY;

		return (int) (miliseconds / DateUtils.MILLIS_PER_HOUR);
	}

	public int getMinutes(double time) {
		double hours = getHours(time) * DateUtils.MILLIS_PER_HOUR;
		double miliseconds = time * DateUtils.MILLIS_PER_DAY - hours;

		return (int) (miliseconds / DateUtils.MILLIS_PER_MINUTE);
	}

	public int getSeconds(double time) {
		double hours = getHours(time) * DateUtils.MILLIS_PER_HOUR;
		double minutes = getMinutes(time) * DateUtils.MILLIS_PER_MINUTE;
		double miliseconds = time * DateUtils.MILLIS_PER_DAY - hours - minutes;

		return (int) (miliseconds / DateUtils.MILLIS_PER_SECOND);
	}

	@SuppressWarnings("deprecation")
	public Date setTimeToDateFromString(String value, Date date) {
		int hours = 0;
		int minutes = 0;
		int seconds = 0;

		Pattern pattern = Pattern.compile("\\d{1,2}");

		Matcher matcher = pattern.matcher(value);

		int count = 0;
		String[] arrayTime = new String[3];
		while(matcher.find()){
			count++;
			arrayTime[count - 1] = matcher.group();
		}

		switch(count){
		case 0:
			return null;
		case 1:
			double miliseconds = 0;
			try{
				miliseconds = Double.parseDouble(value);

				hours = getHours(miliseconds);
				minutes = getMinutes(miliseconds);
				seconds = getSeconds(miliseconds);
			} catch(NumberFormatException e){
				hours = Integer.parseInt(arrayTime[0]);
			}

			break;
		case 2:
			hours = Integer.parseInt(arrayTime[0]);
			minutes = Integer.parseInt(arrayTime[1]);
			break;
		case 3:
			hours = Integer.parseInt(arrayTime[0]);
			minutes = Integer.parseInt(arrayTime[1]);
			seconds = Integer.parseInt(arrayTime[2]);
			break;
		}


		// samplingDate.withHourOfDay(hours);
		// samplingDate.withMinuteOfHour(minutes);
		// samplingDate.withSecondOfMinute(seconds);
		date.setHours(hours);
		date.setMinutes(minutes);
		date.setSeconds(seconds);

		return date;
	}

	public String getTimeFromDate(Date date) {
		// Timestamp ts=new Timestamp(samplingDate.getTime());
		if(date == null)
			return null;

		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

		return formatter.format(date);
	}
}
