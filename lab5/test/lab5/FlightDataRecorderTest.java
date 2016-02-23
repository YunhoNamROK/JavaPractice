package lab5;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FlightDataRecorderTest {


	@Test
	public void testFlightDataRecorder() {

	}

	@Test
	// Supposed to add null to List when exceeding CAPACITY but raises an IndexOutOfBoundsException
	public void testRecord() {
		FlightDataRecorder fd = new FlightDataRecorder();
		fd.record(10d);
		fd.record(20d);
		fd.record(30d);
		fd.record(40d);
		fd.record(50d);
		fd.record(60d);
		fd.record(70d);
		fd.record(20d);
		fd.record(20d);
		fd.record(20d);
		fd.record(20d);
	}

	@Test
	// Problem is with record method; record limited to 10 items, so cannot be tested for List exceeding CAPACITY size
	public void testGetRecordedData() {
		FlightDataRecorder fd = new FlightDataRecorder();
		fd.record(1d);
		fd.record(1d);
		fd.record(1d);
		fd.record(1d);
		fd.record(1d);
		fd.record(1d);
		fd.record(1d);
		fd.record(1d);
		fd.record(1d);
		fd.record(1d);
		fd.record(1d);
		List<Double> lastDataPoints = new ArrayList<Double>();
		lastDataPoints.add(1d);
		lastDataPoints.add(1d);
		lastDataPoints.add(1d);
		lastDataPoints.add(1d);
		lastDataPoints.add(1d);
		lastDataPoints.add(1d);
		lastDataPoints.add(1d);
		lastDataPoints.add(1d);
		lastDataPoints.add(1d);
		lastDataPoints.add(1d);
		lastDataPoints.add(1d);
		assertEquals(lastDataPoints,fd.getRecordedData());
	}

	@Test
	// Index is wrong; should return index 0 item but returns index 1 item
	// Index is wrong; should return all 6 data points from index 0 to 5 but returns 5 data points from index 0 to 4
	public void testGetLastDataPoints() {
		FlightDataRecorder fd = new FlightDataRecorder();
		fd.record(20d);
		fd.record(30d);
		fd.record(40d);
		fd.record(50d);
		fd.record(60d);
		fd.record(70d);
		List<Double> lastDataPoints = new ArrayList<Double>();
		lastDataPoints.add(70d);
		assertEquals(lastDataPoints,fd.getLastDataPoints(0));
		assertEquals(lastDataPoints,fd.getLastDataPoints(5));
	}

	@Test
	// Capacity is static at 10 so the sum of data points is divided by 10 when there are less than 10 data points
	public void testAverage() {
		FlightDataRecorder fd = new FlightDataRecorder();
		fd.record(10d);
		fd.record(10d);
		float s = 10;
		assertEquals(s,fd.average(),0);
	}

}
