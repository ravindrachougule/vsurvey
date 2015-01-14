package com.bmc.traffic.interval;

public class TrafficInterval extends Interval implements Comparable<TrafficInterval> {

	private int vehicleCount;
	public TrafficInterval(int startTime, int endTime) {
		super(startTime, endTime);
	}

	public TrafficInterval(Interval interval, int vehicleCount) {
		super(interval.getStartTime(), interval.getEndTime());
		this.vehicleCount = vehicleCount;
	}

	public int compareTo(TrafficInterval o) {

		if (this.vehicleCount > o.vehicleCount)
			return -1;
		else if (this.vehicleCount < o.vehicleCount)
			return 1;
		else 
			return 0;
	}

	@Override
	public String toString() {
		return "TrafficInterval [vehicleCount=" + vehicleCount
				+ ", getEndTime (ms)=" + getEndTime() + ", getStartTime(ms)="
				+ getStartTime() + "]";
	}


}
