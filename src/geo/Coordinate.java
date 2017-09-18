package geo;

/**
 * Created by juliano on 09/05/17.
 * This class represents a geographical coordinate
 */
public class Coordinate {

    public static final int INVALID_ALTITUDE = -777;

    //The Earth radius in kms (value from geopy)
    private static final double EARTH_RADIUS = 6372.795 ;

    private double latitude;
    private double longitude;
    private double altitude;

    //altitude in meters (initially, the value of altitude is being ignored)
    //keeping altitude for future use
    public Coordinate(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    /*
      Compute the distance between to geographical coordinates through Harvesine formula.
      return: the distance between to coordinates in kms
      note: Vincenty's formulae is more accurate, however, it is also more computationally intensive.
     */
    public double distance(Coordinate coordinate) {
        double dLat = Math.toRadians(coordinate.getLatitude() - this.latitude);
        double dLon = Math.toRadians(coordinate.getLongitude() - this.longitude);
        double lat1R = Math.toRadians(coordinate.getLatitude());
        double lat2R = Math.toRadians(this.latitude);

        double a = (Math.sin(dLat / 2.0) * Math.sin(dLat / 2.0)) +
                Math.cos(lat1R) * Math.cos(lat2R)
                        * Math.sin(dLon / 2.0) * Math.sin(dLon / 2.0);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = EARTH_RADIUS * c;
        return d;
    }

    @Override
    public String toString() {
        return ""+latitude+","+longitude;
    }
}
