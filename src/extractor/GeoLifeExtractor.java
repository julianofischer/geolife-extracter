package extractor;

import geo.Coordinate;
import reader.GeoLifeReader;
import reader.TrajectoryEntry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    private Map<String, Connection> openConnections;
    private String outputFileName = DEFAULT_OUT_FILE;
    private File outputFile;
    private List<String> outputLines;

    public GeoLifeExtractor(File root){
        this.root = root;
        this.reader = new GeoLifeReader(root);
        this.nodes = new LinkedHashMap<Integer, Node>();
        this.openConnections = new HashMap<>();
        this.outputLines = new ArrayList<>();
    }

    public void run(){
        System.out.println("Extracting trajectories");
        List<TrajectoryEntry> trajectories = reader.extractTrajectoryEntries();
        System.out.println("Extracting connections");
        extract(trajectories);
    }

    private void extract(List<TrajectoryEntry> trajectories){

        long init_time = trajectories.get(0).getDatetime().getTime();

        for (TrajectoryEntry entry : trajectories){

            //convert time from ms to seconds
            long time  = (entry.getDatetime().getTime() - init_time)/1000;
            int nodeId = entry.getNodeId();

            Node n;

            if (nodes.containsKey(entry.getNodeId())){
                n = nodes.get(nodeId);
                n.updateLocation(entry.getCoordinate());
            }else{
                n = new Node();
                n.setId(nodeId);
                n.updateLocation(entry.getCoordinate());
                nodes.put(nodeId, n);
            }

            //verifying for opening or closing connections
            checkConnections(n, time);
        }

        //write the lines to the output file
        outputFile = new File(outputFileName);

        try{
            Files.write(outputFile.toPath(), outputLines);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    //check the connections between the node and all other nodes
    private void checkConnections(Node n, long time){
        for (Node aNode : nodes.values()) {
            if (!aNode.equals(n)) {
                Coordinate c1 = n.getLocation();
                Coordinate c2 = aNode.getLocation();

                if (c1.distance(c2) <= radioRange && !connectionIsOpened(n, aNode)) {
                    //if is in the range and the connection is not already opened
                    openConnection(n, aNode, time);
                }else if (c1.distance(c2) >= radioRange && connectionIsOpened(n, aNode)){
                    //node get out of reach, must close the connection
                    Connection con = getOpenConnection(n, aNode);
                    con.setClosingTime(time);
                    closeConnection(con);
                }
            }
        }
    }

    private void openConnection(Node n1, Node n2, long time){
        String id = generateConnectionId(n1,n2);
        openConnections.put(id, new Connection(n1.getId(),n2.getId(),time));
        String line = String.format("%d CONN %d %d %s",time, n1.getId(), n2.getId(), Connection.CONNECTION_OPENING);
        logLine(line);
    }

    private void closeConnection(Connection c){
        String id = generateConnectionId(c);
        openConnections.remove(id);
        String line = String.format("%d CONN %d %d %s", c.getClosingTime(), c.getFrom(), c.getTo(),
                Connection.CONNECTION_CLOSING);
        logLine(line);
    }

    private String generateConnectionId(Node n1, Node n2){
        return generateConnectionId(n1.getId(), n2.getId());
    }

    private String generateConnectionId(Connection c){
        return generateConnectionId(c.getFrom(), c.getTo());
    }

    private String generateConnectionId(int from, int to){
        int max = Math.max(from, to);
        int min = Math.min(from, to);
        String generatedId = ""+ min + "<->" + max;
        return generatedId;
    }

    private void logLine(String str){
        this.outputLines.add(str);
    }

    private boolean connectionIsOpened(Node n1, Node n2){
        String id = generateConnectionId(n1,n2);
        return openConnections.containsKey(id);
    }

    private Connection getOpenConnection(Node n1, Node n2){
        String id = generateConnectionId(n1,n2);
        return openConnections.get(id);
    }

    public void configureRadioRange(int range){
        this.radioRange = range;
    }

    public void configureOutputFilename(String filename){
        this.outputFileName = filename;
    }
}
