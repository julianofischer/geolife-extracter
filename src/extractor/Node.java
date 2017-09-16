package extractor;

import geo.Coordinate;

/**
 * Created by juliano on 10/05/17.
 * This class represents a Node in the trace
 */
public class Node {
    private Coordinate location;
    private int id;

    public Coordinate getLocation() {
        return location;
    }

    public void updateLocation(Coordinate location) {
        this.location = location;
    }

    public void updateLocation(double latitude, double longitude, double altitude) {
        this.location = new Coordinate(latitude, longitude, altitude);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
