package com.bmc.traffic;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.bmc.traffic.interval.Interval;
import com.bmc.traffic.interval.IntervalFactory;
import com.bmc.traffic.interval.TrafficInterval;
import com.bmc.traffic.reference.Direction;

public class Analyser {

	private List<CarEntry>  carEntries;
	public Analyser(List<CarEntry> carEntries) {
		this.carEntries = carEntries;
	}


	public List<CarEntry> getCarEntries(Interval interval, Direction direction) {

		List<CarEntry> result = new ArrayList<CarEntry>(); 
		for (CarEntry entry : carEntries)
		{
			if (interval.contains(entry.recordEntries.get(0).getTimestamp()) && entry.direction.equals(direction))
				result.add(entry);
		}

		return result;
	}


	public TreeSet<TrafficInterval> getPeak(Interval interval, int breakInterval, Direction direction) {

		TreeSet<TrafficInterval> result = new TreeSet<TrafficInterval>();
		List<Interval>halfHourly = interval.breakDown(breakInterval);

		for(Interval aInterval : halfHourly)
		{
			result.add(new TrafficInterval(aInterval,getCarEntries( aInterval,direction).size()));

		}
		return result;
	}


	public float getAvgDistance(Interval interval, Direction northbound) {
		List<CarEntry> cars = getCarEntries(interval,northbound);
		int distance = 0;
		CarEntry previous = null;
		for (CarEntry car : cars)
		{
			if (previous != null){
				distance += car.distance(previous);
			}

			previous = car;
		}
		return distance/cars.size();
	}
	
	public Interval printDailyCount(Analyser analyser) {
		System.out.println(" Total Vehicle count in morning ");

		Interval getOneDayInterval = IntervalFactory.getOneDayInterval();
		Interval interval = null;
		for (int day=1;day<=5;day++)
		{
			interval = getOneDayInterval.advanceFor(day);
			System.out.print(" NorthBound Day[" + day + "]= " + analyser.getCarEntries(interval,Direction.NorthBound).size());
		}

		System.out.println();
		for (int day=1;day<=5;day++)
		{
			interval = getOneDayInterval.advanceFor(day);
			System.out.print(" SouthBound Day[" + day + "]= " + analyser.getCarEntries(interval,Direction.SouthBound).size());


		}
		System.out.println("\n \n");
		return getOneDayInterval;
	}
}
