package geolife;

import core.TrajectoryLine;

import java.util.Date;

/**
 * Created by juliano on 09/05/17.
 */
public class GeoLifeTrajectoryLine extends TrajectoryLine{

    private double altitude;

    public GeoLifeTrajectoryLine(double latitude, double longitude, double altitude, Date date){
        setLatitute(latitude);
        setLongitude(longitude);
        setDatetime(date);
        this.altitude = altitude;
    }


    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

}
