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
}
