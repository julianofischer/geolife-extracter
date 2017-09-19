package core;

/**
 * Created by juliano on 10/05/17.
 * This class represents a Node in the trace
 */
public class Node {
    private Coordinate location;
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return id == node.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

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
