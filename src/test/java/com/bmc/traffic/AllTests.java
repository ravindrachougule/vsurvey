package com.bmc.traffic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.bmc.traffic.file.TrafficDataReaderTest;
import com.bmc.traffic.interval.IntervalTest;
import com.bmc.traffic.monitorState.MonitorStateTest;

@RunWith(Suite.class)
@SuiteClasses({ AnalyserTest.class, CarEntryTest.class, IntervalTest.class,
		MonitorStateTest.class, MonitorTest.class,TrafficDataReaderTest.class })
public class AllTests {

}
