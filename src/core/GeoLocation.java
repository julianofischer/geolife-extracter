package core;

import core.Coordinate;

/**
 * Created by juliano on 09/05/17.
 * Based on http://www.geodatasource.com/developers/java
 */
public class GeoLocation {

    public static final String UNIT_KILOMETERS = "K";
    public static final String UNIT_MILES = "M";
    public static final String UNIT_NAUTICAL_MILES = "N";

    public static final double distance(Coordinate c1, Coordinate c2, String unit){
        double lon1 = c1.getLongitude();
        double lon2 = c2.getLongitude();

        double lat1 = c1.getLatitude();
        double lat2 = c2.getLatitude();

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit.equals('K')) {
            dist = dist * 1.609344;
        }
        else if (unit.equals('N')) {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    //DEFAULT DISTANCE RETURNS KILOMETERS
    public static final double distance(Coordinate c1, Coordinate c2) {
        return distance(c1,c2,UNIT_KILOMETERS);
    }


    /**
     * <p>This function converts decimal degrees to radians.</p>
     *
     * @param deg - the decimal to convert to radians
     * @return the decimal converted to radians
     */
    private static final double deg2rad(double deg)
    {
        return (deg * Math.PI / 180.0);
    }

    /**
     * <p>This function converts radians to decimal degrees.</p>
     *
     * @param rad - the radian to convert
     * @return the radian converted to decimal degrees
     */
    private static final double rad2deg(double rad)
    {
        return (rad * 180 / Math.PI);
    }
}
