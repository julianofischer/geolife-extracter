package core;

import java.util.Date;

/**
 * Created by juliano on 19/09/17.
 */
public class TrajectoryLine {
    private double latitute;
    private double longitude;
    private Date datetime;

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

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
