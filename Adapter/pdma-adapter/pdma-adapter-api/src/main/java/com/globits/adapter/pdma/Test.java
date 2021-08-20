package com.globits.adapter.pdma;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.globits.adapter.pdma.utils.CommonUtils;

public class Test {

	public static void main(String[] args) {
//		Hashids hashids = new Hashids("100", 20, "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
//		System.out.println(hashids.encode(100));
//
//		for (long i = 0; i < 10000; i++) {
//			String s1 = RandomStringUtils.random(20, false, true);
//			if (s1.length() == 20) {
//				System.out.println(s1);
//			}
//		}

//		String a = "prov_1";
//		System.out.println(a.substring(a.indexOf("_") + 1));
//
//		String input = "1";
//		while (input.length() < 5) {
//			input = "0" + input;
//		}
//		
//		System.out.println(input);
//
//		String name1 = "Nguyễn Tuấn Anh";
//		String name2 = "Nguyễn Tuân Ánh";
//
//		double ds = StringUtils.getJaroWinklerDistance(name1, name2);
//		System.out.println(ds);

//		System.out.println(UUID.fromString("7CE33A92-08D3-422D-B9FE-35418AAAD52C").toString());

//		System.out.println(WordUtils.capitalizeFully("nguyễn vĂn nam"));

//		String s = "CK,CB,KC,HN,DT,DK,XD,BT,TS";
//		System.out.println(s.indexOf("CB"));

//		LocalDateTime todayStart = LocalDateTime.now(ZoneId.of("GMT+7")).withHour(0).withMinute(0).withSecond(0)
//				.withNano(0);
//
//		System.out.println(todayStart.minusDays(2));
//		System.out.println(todayStart.minusDays(1));

//		String[] vals = { "1", "1" };
//
//		List<String> l = Lists.newArrayList(vals);
//		l.add("2");
//		
//		vals = l.toArray(new String[0]);
//		
//
//		for (String val : vals) {
//			System.out.println(val);
//		}
//		
//		System.out.println(Boolean.valueOf("false"));

//		List<Long> list = new ArrayList<>();
//		list.add(10l);
//		list.add(8l);
//		list.add(6l);
//		
//		Long a = 10l;
//		
//		System.out.println(list.contains(a.longValue()));

//		long monthsBetween = ChronoUnit.MONTHS.between(LocalDate.parse("2016-08-01").withDayOfMonth(1),
//				LocalDate.parse("2016-08-31").withDayOfMonth(1));
//		System.out.println(monthsBetween); // 3
//
		for (long i = 0; i < 100; i ++) {
			System.out.println(UUID.randomUUID().toString());
		}
		
//		System.out.println(PatientStatus.ACTIVE.name());

		LocalDateTime startDate = LocalDateTime.of(2015, 2, 20, 0, 0, 0);
		LocalDateTime endDate = LocalDateTime.of(2017, 1, 15, 0, 0, 0);

		System.out.println(CommonUtils.dateDiff(ChronoUnit.YEARS, startDate, endDate));
		
//		String s = "2009-10-10T10:10:10.000Z";
//		s = s.substring(0, s.length() - 1);
//		System.out.println(s);
//
//		LocalDateTime endOfLastWeek = LocalDateTime.now(ZoneId.of("GMT+7")).minusWeeks(1).with(DayOfWeek.SUNDAY).withHour(23)
//				.withMinute(59).withSecond(59).withNano(0);
//
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
//		System.out.println(sdf.format(CommonUtils.fromLocalDateTime(endOfLastWeek)));
//
//		WeekFields weekFields = WeekFields.of(Locale.forLanguageTag("vi-VN"));
//		System.out.println(endOfLastWeek.get(weekFields.weekOfWeekBasedYear()));
//
//		LocalDateTime[][] _8weeks = get8Weeks(endOfLastWeek);
//		for (int i = 0; i < 8; i++) {
//			System.out.println("Week " + (i + 1));
//			System.out.println(sdf.format(CommonUtils.fromLocalDateTime(_8weeks[i][0])) + " - "
//					+ sdf.format(CommonUtils.fromLocalDateTime(_8weeks[i][1])));
//			System.out.println("---------");
//		}
//
//		System.out.println();
//		System.out.println();
//
//		LocalDateTime[][] _12months = get12Months(10, LocalDateTime.now(ZoneId.of("GMT+7")).getYear() - 1);
//		for (int i = 0; i < 12; i++) {
//			System.out.println("Month " + (i + 1));
//			System.out.println(sdf.format(CommonUtils.fromLocalDateTime(_12months[i][0])) + " - "
//					+ sdf.format(CommonUtils.fromLocalDateTime(_12months[i][1])));
//			System.out.println("---------");
//		}
	}

	@SuppressWarnings("unused")
	private static LocalDateTime[][] get12Months(final int month, final int year) {
		LocalDateTime[][] months = new LocalDateTime[12][2];

		LocalDateTime beginOfYear = null;
		LocalDateTime fromDate = null;
		LocalDateTime toDate = null;

		if (month >= 1 && month <= 9) {
			beginOfYear = LocalDateTime.of(year - 1, 10, 1, 0, 0, 0, 0);
		} else {
			beginOfYear = LocalDateTime.of(year, 10, 1, 0, 0, 0, 0);
		}

		for (int i = 0; i < 12; i++) {
			fromDate = beginOfYear.plusMonths(i);
			toDate = fromDate.plusMonths(1).minusMinutes(1).withHour(23).withMinute(59).withSecond(59).withNano(0);
			months[i][0] = fromDate;
			months[i][1] = toDate;
		}

		return months;
	}

	@SuppressWarnings("unused")
	private static LocalDateTime[][] get8Weeks(final LocalDateTime toDateInput) {
		if (toDateInput == null) {
			return null;
		}

		LocalDateTime toDate = null;
		LocalDateTime fromDate = null;
		LocalDateTime[][] weeks = new LocalDateTime[8][2];
		for (int i = 0; i < 8; i++) {
			toDate = toDateInput.minusWeeks(i);
			fromDate = toDate.plusMinutes(1).minusDays(7).withHour(0).withMinute(0).withSecond(0).withNano(0);
			weeks[7 - i][0] = fromDate;
			weeks[7 - i][1] = toDate;
		}

		return weeks;
	}
}
