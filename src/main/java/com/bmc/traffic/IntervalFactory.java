package com.bmc.traffic;

public class IntervalFactory {

	public static final int MidNightStart = 0;
	public static final int midNight = 86400000;
	private static final int Hour16 = 57600000;
	private static final int midNightToNoon = 43200000;


	public static Interval getMorning() {
		return new Interval(MidNightStart,midNightToNoon);
	}

	public static Interval getEvening() {
		return new Interval(  Hour16,midNight);
	}

	public static int getOneDayInterval() {
		return 86400000;
	}

	
}
