package reader;

import geo.Coordinate;

import java.util.Date;

/**
 * Created by juliano on 15/09/17.
 */
public class TrajectoryEntry implements Comparable<TrajectoryEntry>{
    private int nodeId;
    private Coordinate coordinate;
    private Date datetime;

    public TrajectoryEntry(GeoLifeTrajectoryLine line, int nodeId){
        this.coordinate = new Coordinate(line.getLatitute(), line.getLongitude(), line.getAltitude());
        this.datetime = line.getDate();
        this.nodeId = nodeId;
    }

    @Override
    public int compareTo(TrajectoryEntry trajectoryEntry) {
        return datetime.compareTo(trajectoryEntry.getDatetime());
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
