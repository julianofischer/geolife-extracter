package geolife;

import core.TrajectoryEntry;
import reader.DirReader;

import java.io.File;
import java.util.*;

/**
 * Created by juliano on 15/09/17.
 */
public class GeoLifeReader {
    private File root;
    private List<File> dirs;


    public GeoLifeReader(File f){
        this.root = f;
        dirs = filter(Arrays.asList(root.listFiles()));
    }

    private ArrayList<File> filter(List<File> f){
        ArrayList<File> ret = new ArrayList();
        for (File file : f){
            if (file.isDirectory()){
                ret.add(file);
            }
        }
        return ret;
    }

    public List<TrajectoryEntry> extractTrajectoryEntries(){
        List<TrajectoryEntry> ret = new ArrayList();
        for (File dir : dirs){
            System.out.println("Extracting trajectories from: "+dir);
            DirReader dr = new DirReader(dir);
            List<GeoLifeTrajectoryLine> aux = dr.read();
            // the directory name is the nodeId
            int nodeId = Integer.parseInt(dir.getName());

            for (GeoLifeTrajectoryLine line : aux) {
                TrajectoryEntry entry = new TrajectoryEntry(line, nodeId);
                ret.add(entry);
            }
        }
        //ordered by date
        Collections.sort(ret);
        return ret;
    }
}