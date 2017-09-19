package reader;

import core.TrajectoryLine;
import geolife.GeoLifeTrajectoryFileReader;
import geolife.GeoLifeTrajectoryLine;

import java.io.File;
import java.util.*;

/**
 * Created by juliano on 10/05/17.
 */
public class DirReader {
    private File root;
    private File trajectoryRoot;
    private List<File> trajectoryFiles;
    private File reading;

    //strategy pattern
    private TrajectoryFileReader trajectoryFileReader = null;

    private Queue<File> filesToRead;

    public DirReader(File root, TrajectoryFileReader fr) {
        this.root = root;
        this.trajectoryRoot = new File(root.getAbsolutePath() + "/Trajectory");
        trajectoryFiles = new ArrayList<File>(Arrays.asList(trajectoryRoot.listFiles()));
        reading = null;
        filesToRead = new LinkedList<File>(trajectoryFiles);
        trajectoryFileReader = fr;
    }

    //should be called after verification by "hasNextLine"
    private TrajectoryLine readNextLine() {
        if (!trajectoryFileReader.hasNextLine()) {
            trajectoryFileReader.prepareFile(filesToRead.remove());
        }

        return trajectoryFileReader.nextLine();
    }

    private boolean hasNextFile() {
        //not is empty
        return !filesToRead.isEmpty();

    }

    private boolean hasNextLine() {
        if (trajectoryFileReader != null) {
            if (trajectoryFileReader.hasNextLine()) {
                return true;
            }
        }
        return hasNextFile();
    }

    public ArrayList<TrajectoryLine> read(){

        ArrayList<TrajectoryLine> ret = new ArrayList();

        while(hasNextLine()){
            ret.add(readNextLine());
        }

        return ret;
    }
}
