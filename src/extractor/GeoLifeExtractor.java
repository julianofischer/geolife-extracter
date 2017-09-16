package extractor;

import reader.GeoLifeFileReader;
import reader.GeoLifeReader;
import reader.TrajectoryEntry;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by juliano on 16/09/17.
 */
public class GeoLifeExtractor {

    private static final String DEFAULT_OUT_FILE = "connections.txt";

    private File root;
    private GeoLifeReader reader;

    public GeoLifeExtractor(File root){
        this.root = root;
        this.reader = new GeoLifeReader(root);
    }

    public void run(){
        System.out.println("Extracting trajectories");
        List<TrajectoryEntry> trajectories = reader.extractTrajectoryEntries();

    }

    private void extract(List<TrajectoryEntry> trajectories){

        double init_time = trajectories.get(0).getDatetime().getTime();

        for (TrajectoryEntry entry : trajectories){
            double time  = entry.getDatetime().getTime() - init_time;

        }
    }

}
