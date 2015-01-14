package com.bmc.traffic.interval;

public class IntervalFactory {

	private static final int  MS = 1000;
	public static final int MidNightStart = 0;
	public static final int fifteenMinInterval = 60 * 15 * MS;
	public static final int twentyMinInterval = 60 * 20 * MS;
	public static final int halfHourInterval = 60 *30 * MS;
	public static final int hourInterval = 60 *60 * MS;;

	private static final int Hour16 = hourInterval *16 ;
	private static final int midNightToNoon = hourInterval *12;
	public static final int midNight = hourInterval *24;


	/** for simplicity **/
	public static Interval getMorning() {
		return new Interval(MidNightStart,midNightToNoon);
	}

	public static Interval getEvening() {
		return new Interval(  Hour16,midNight);
	}

	public static Interval getOneDayInterval() {
		return new Interval(MidNightStart,midNight);
	}

	
}
