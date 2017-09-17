package extractor;

import geo.Coordinate;
import reader.GeoLifeFileReader;
import reader.GeoLifeReader;
import reader.TrajectoryEntry;

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by juliano on 16/09/17.
 */
public class GeoLifeExtractor {

    private static final String DEFAULT_OUT_FILE = "connections.txt";

    //Radio range in meters
    private static final int DEFAULT_RADIO_RANGE = 50;

    private int radioRange = DEFAULT_RADIO_RANGE;
    private File root;
    private GeoLifeReader reader;
    private Map<Integer, Node> nodes;
    private Set<Connection> openConnections;

    public GeoLifeExtractor(File root){
        this.root = root;
        this.reader = new GeoLifeReader(root);
        this.nodes = new LinkedHashMap<Integer, Node>();
        this.openConnections = new HashSet<>();
    }

    public void run(){
        System.out.println("Extracting trajectories");
        List<TrajectoryEntry> trajectories = reader.extractTrajectoryEntries();

    }

    private void extract(List<TrajectoryEntry> trajectories){

        double init_time = trajectories.get(0).getDatetime().getTime();

        for (TrajectoryEntry entry : trajectories){
            double time  = entry.getDatetime().getTime() - init_time;
            int nodeId = entry.getNodeId();

            Node n;

            if (nodes.containsKey(entry.getNodeId())){
                n = nodes.get(nodeId);
                n.updateLocation(entry.getCoordinate());
            }else{
                n = new Node();
                n.setId(nodeId);
                n.updateLocation(entry.getCoordinate());
            }
        }
    }

    private void checkConnections(Node n){
        for (Node aNode : nodes.values()) {
            if (!aNode.equals(n)) {
                Coordinate c1 = n.getLocation();
                Coordinate c2 = aNode.getLocation();
                if (c1.distance(c2) <= radioRange) {
                    System.out.println("is connected");
                }else{
                    System.out.println("is not connected");
                }
            }
        }
    }

    private void openConnection(Node n1, Node n2){

    }

    private void closeConnection(Node n1, Node n2){
        
    }

}
