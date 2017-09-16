package reader;

import java.util.Date;

/**
 * Created by juliano on 09/05/17.
 */
public class GeoLifeTrajectoryLine {

    private double latitute;
    private double longitude;
    private double altitude;
    private Date date;

    public GeoLifeTrajectoryLine(double latitute, double longitude, double altitude, Date date){
        this.latitute = latitute;
        this.longitude = longitude;
        this.altitude = altitude;
        this.date = date;
    }

    public long getTime(){
        return date.getTime();
    }

    public double getLatitute() {
        return latitute;
    }

    public void setLatitute(double latitute) {
        this.latitute = latitute;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
