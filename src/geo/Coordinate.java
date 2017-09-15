package geo;

/**
 * Created by juliano on 09/05/17.
 * This class represents a geographical coordinate
 */
public class Coordinate {

    public static final int INVALID_ALTITUDE = -777;

    private double latitude;
    private double longitude;
    private double altitude;

    //altitude in meters
    public Coordinate(double latitude, double longitude, double altitude) {
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
}
