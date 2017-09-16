package reader;

import java.io.File;
import java.util.*;

/**
 * Created by juliano on 15/09/17.
 */
public class GeoLifeReader {
    private File root;
    private List<File> dirs;


    public GeoLifeReader(File f){
        this.root = root;
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
            DirReader dr = new DirReader(dir);
            List<GeoLifeTrajectoryLine> aux = dr.read();

            for (GeoLifeTrajectoryLine line : aux) {
                TrajectoryEntry entry = new TrajectoryEntry();
            }
        }
    }
}
