package com.bmc.traffic.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bmc.traffic.Record;
import com.bmc.traffic.reference.Sensor;

public class TrafficDataReader
{
	
	public static String path ="sampleData.txt";

	public static List<Record> read(String path) throws IOException
	{
		List<Record> result = new ArrayList<Record>();
		InputStream in = TrafficDataReader.class.getClassLoader()
                .getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		while ((line = br.readLine()) != null) {
		   result.add(parse(line));
		}
		br.close();
		return result;
	}

	public static Record parse(String input)
	{
		Record result = null;
		String row = input.trim();
		if (row.startsWith(Sensor.A.toString()))
		{
			result = new Record(Sensor.A, Integer.parseInt(row.substring(1)));
			
		}else if(row.startsWith(Sensor.B.toString()))
		{
			result = new Record(Sensor.B, Integer.parseInt(row.substring(1)));
		}
		return result;
	}

}
