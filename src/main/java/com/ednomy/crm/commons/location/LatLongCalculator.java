package com.ednomy.crm.commons.location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ednomy.crm.entity.EdPointEntity;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class LatLongCalculator {
	
	private boolean printLogger = false;
	
	/*public static double PI = 3.14159265;
	public static double TWOPI = 2 * PI;*/

	Logger LOGGER = LoggerFactory.getLogger(getClass());

	private List<Point> boundaryCordinates = new ArrayList<Point>();

	
	/**
	 * @param boundaryCordinates initial JsonNode containing list of Coordinates
	 * @param exploreMaxDistance find till distance
	 * @param initLatitude initial latitude
	 * @param initLongitude initial longitude
	 * @return Map with keys result(final result), collected(all searched Coordinates), centerPoint initial cordinates for given distance, latitude and longitude 
	 * @throws Exception
	 */
	public Map<String, List<Point>> getResult(JsonNode boundaryCordinates, double exploreMaxDistance, double initLatitude, double initLongitude) throws Exception {
		return getResult(boundaryCordinates, 1.0, exploreMaxDistance, initLatitude, initLongitude);
	}
	

	/**
	 * @param coordinatesArray initial JsonNode containing list of Coordinates
	 * @param exploreDistanceInterval initial distance 
	 * @param exploreMaxDistance find till distance
	 * @param initLatitude initial latitude
	 * @param initLongitude initial longitude
	 * @return Map with keys result(final result), collected(all searched Coordinates), boundaryCordinates initial cordinates for given distance, latitude and longitude 
	 * @throws Exception
	 */
	public Map<String, List<Point>> getResult(JsonNode coordinatesArray, double exploreDistanceInterval, double exploreMaxDistance, double initLatitude, double initLongitude) throws Exception {

		if (exploreDistanceInterval <= 0.0) {
			exploreDistanceInterval = 1.0;
		}
		
		if (initLatitude <= 0.0) {
			LOGGER.debug("Invalid Latitude Value : " + initLatitude);
			return null;
		}
		
		if (initLongitude <= 0.0) {
			LOGGER.debug("Invalid Longitude Value : " + initLongitude);
			return null;
		}

		recursiveData(coordinatesArray);
		
		

		List<Point> points = exploreCordinates(exploreDistanceInterval, exploreMaxDistance, initLatitude, initLongitude);
		Set<Point> allPoints = new HashSet<Point>();
		Iterator<Point> iterator = points.iterator();
		while (iterator.hasNext()) {
			Point point = (Point) iterator.next();
			allPoints.addAll(exploreCordinates(exploreDistanceInterval, exploreMaxDistance, point.getLatitude(), point.getLongitude()));
		}

		printLogs("Total Log Cordinates Search : " + allPoints.size());

		Set<Point> filteredPoints = new HashSet<Point>();
		Iterator<Point> itera = allPoints.iterator();
		while (itera.hasNext()) {
			Point point = (Point) itera.next();
			//filter data from all point to inside boundary points
			/*if (isPointInPolygon(new ArrayList<Point>(boundaryCordinates), point)) {
				filteredPoints.add(point);
				printLogs("Final Point Items latitude,longitude : " + point.getLatitude() + ", " + point.getLongitude());
			}*/
			/*if (coordinateInsidePolygon(new ArrayList<Point>(boundaryCordinates), point.getLatitude(), point.getLongitude())) {
				filteredPoints.add(point);
				printLogs("Final Point Items latitude,longitude : " + point.getLatitude() + ", " + point.getLongitude());
			}*/
			
			if (PointinPolygon( new ArrayList<Point>(boundaryCordinates), point)) {
				filteredPoints.add(point);
				printLogs("Final Point Items latitude,longitude : " + point.getLatitude() + ", " + point.getLongitude());
			}
			
		}
		
		LOGGER.debug("Total Filtered Points : " + filteredPoints.size());
		
		Map<String, List<Point>> map = new HashMap<String, List<Point>>();
		map.put("result", new ArrayList<Point>(filteredPoints));
		map.put("collected", new ArrayList<Point>(allPoints));
		map.put("boundaryCordinates", boundaryCordinates);
 		
		return map; 

	}

	/**
	 * filter out all coordinates from given nested array
	 * @param jsonNode nested array of json coordinates
	 */
	private void recursiveData(JsonNode jsonNode) {

		Iterator<JsonNode> iterator = jsonNode.iterator();
		while (iterator.hasNext()) {
			JsonNode imArray = (JsonNode) iterator.next();

			if (imArray.isArray()) {
				recursiveData(imArray);
			} else {
				double latitude = jsonNode.get(1).asDouble();
				double longitude = jsonNode.get(0).asDouble();
				Point point = new Point();
				point.setLatitude(latitude);
				point.setLongitude(longitude);
				boundaryCordinates.add(point);
			}
		}
	}

	/**
	 * Result Point for set of latitide and longitude for given latitude, longitude and distance a-part
	 * @param distance distance of latitude, longitude from given points
	 * @param distance max distance of latitude, longitude from given points
	 * @param latitude initial latitude 
	 * @param longitude initial longitude
	 * @return Set Of Points 
	 */
	private List<Point> exploreCordinates(double distance, double maxDistance, double latitude, double longitude) {

		Set<Point> points = new HashSet<Point>();

		do {

			int[] radians = new int[] { 30, 60, 90, 120, 150, 180, 210, 240,
					270, 300, 330, 360 };
			for (int i = 0; i < radians.length; i++) {
				printLogs("Initial Distance  : " + distance + " : Radians : " + radians[i]);
				printLogs("Initial Latitude  : " + latitude + " : Longitude : " + longitude);
				double dist = distance / 6371.0;
				double brng = Math.toRadians(radians[i]);
				double lat1 = Math.toRadians(latitude);
				double lon1 = Math.toRadians(longitude);

				double lat2 = Math.asin(Math.sin(lat1) * Math.cos(dist)
						+ Math.cos(lat1) * Math.sin(dist) * Math.cos(brng));
				double a = Math.atan2(
						Math.sin(brng) * Math.sin(dist) * Math.cos(lat1),
						Math.cos(dist) - Math.sin(lat1) * Math.sin(lat2));
				
				printLogs("Result Distance From Center Of Earth = " + a);
				double lon2 = lon1 + a;
				lon2 = (lon2 + 3 * Math.PI) % (2 * Math.PI) - Math.PI;
				printLogs("Result Latitude = "+Math.toDegrees(lat2)+"\nLongitude = "+Math.toDegrees(lon2));
				Point point = new Point();
				point.setLatitude(Math.toDegrees(lat2));
				point.setLongitude(Math.toDegrees(lon2));
				points.add(point);
			}
			distance++;
		} while (distance < maxDistance);

		return new ArrayList<Point>(points);

	}
	
	/**
	 * Check Weather Logger is On
	 * @return boolean
	 */
	public boolean isPrintLogger() {
		return printLogger;
	}

	/**
	 * Set logger on/ off
	 * @param printLogger boolean true for switch on
	 */
	public void setPrintLogger(boolean printLogger) {
		this.printLogger = printLogger;
	}
	
	private void printLogs(String message){
		if (printLogger) {
			LOGGER.info(message);
		}
	}
	
	

	/**
	 * @param  points list of all boundary points
	 * @param  point set of coordinates (latitude, longitude) say Point
	 * @return boolean true if inside boundary
	 * @throws Exception
	 */
	public static boolean isPointInPolygon(List<EdPointEntity> points, EdPointEntity point) throws Exception{
		int i, j;
		boolean status = false;
		for (i = 0, j = points.size() - 1; i < points.size(); j = i++) {
			if ((((points.get(i).getLatitude() <= point.getLatitude()) && (point.getLatitude() < points.get(j).getLatitude())) || ((points.get(
					j).getLatitude() <= point.getLatitude()) && (point.getLatitude() < points.get(i).getLatitude())))
					&& (point.getLongitude() < (points.get(j).getLongitude() - points.get(i).getLongitude()) * (point.getLatitude() - points.get(i).getLatitude())
							/ (points.get(j).getLatitude() - points.get(i).getLatitude())+ points.get(i).getLongitude())) {

				status = !status;
			}
		}

		return status;
	}
	
	public static boolean PointinPolygon( List<Point> points, Point p )
    {
        boolean result = false;

        for( int i = 0; i < points.size() - 1; i++ )
        {
            if( ( ( ( points.get( i + 1).getLatitude() <= p.getLatitude()) && ( p.getLatitude() < points.get(i).getLatitude()) ) || ( ( points.get(i).getLatitude() <= p.getLatitude() ) && ( p.getLatitude() < points.get(i + 1).getLatitude() ) ) ) 
            				&& ( p.getLongitude() < ( points.get(i).getLongitude() - points.get( i + 1 ).getLongitude() ) * ( p.getLatitude() - points.get( i + 1).getLatitude() ) / ( points.get(i).getLatitude() - points.get( i + 1 ).getLatitude() ) + points.get(i + 1 ).getLongitude() ) )
            {
                result = !result;
            }
        }
        return result;
    }
		

}
/*
public static boolean coordinateInsidePolygon(List<Point> points, double latitude,
		double longitude) {
	int i;
	double angle = 0;
	double point1_lat;
	double point1_long;
	double point2_lat;
	double point2_long;
	int n = points.size();

	for (i = 0; i < n; i++) {
		point1_lat = points.get(i).getLatitude() - latitude;
		point1_long = points.get(i).getLongitude() - longitude;
		point2_lat = points.get((i + 1) % n).getLatitude() - latitude;
		// you should have paid more attention in high school geometry.
		point2_long = points.get((i + 1) % n).getLongitude() - longitude;
		angle += Angle2D(point1_lat, point1_long, point2_lat, point2_long);
	}

	if (Math.abs(angle) < PI)
		return false;
	else
		return true;
}

private static double Angle2D(double y1, double x1, double y2, double x2) {
	double dtheta, theta1, theta2;

	theta1 = Math.atan2(y1, x1);
	theta2 = Math.atan2(y2, x2);
	dtheta = theta2 - theta1;
	while (dtheta > PI)
		dtheta -= TWOPI;
	while (dtheta < -PI)
		dtheta += TWOPI;

	return (dtheta);
}


 boolean coordinateInRegion(List<Point> region, Point coord) {
        int i, j;
        boolean isInside = false;
        //create an array of coordinates from the region boundary list
        Point[] verts = region.toArray(new Point[region.size()]);
        int sides = verts.length;
        for (i = 0, j = sides - 1; i < sides; j = i++) {
            //verifying if your coordinate is inside your region
            if (
                (
                 (
                  (verts[i].getLongitude() <= coord.getLongitude()) && (coord.getLongitude() < verts[j].getLongitude())
                 ) || (
                  (verts[j].getLongitude() <= coord.getLongitude()) && (coord.getLongitude() < verts[i].getLongitude())
                 )
                ) &&
                (coord.getLatitude() < (verts[j].getLatitude() - verts[i].getLatitude()) * (coord.getLatitude() - verts[i].getLongitude()) / (verts[j].getLongitude() - verts[i].getLongitude()) + verts[i].getLatitude())
               ) {
                isInside = !isInside;
            }
        }
        return isInside;
 }*/
