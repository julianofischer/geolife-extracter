package reader;

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
    private GeoLifeFileReader fileReader = null;
    private Queue<File> filesToRead;

    public DirReader(File root) {
        this.root = root;
        this.trajectoryRoot = new File(root.getAbsolutePath() + "/Trajectory");
        trajectoryFiles = new ArrayList<File>(Arrays.asList(trajectoryRoot.listFiles()));
        reading = null;
        filesToRead = new LinkedList<File>(trajectoryFiles);
        fileReader = new GeoLifeFileReader(filesToRead.remove());
    }

    //should be called after verification by "hasNextLine"
    private GeoLifeTrajectoryLine readNextLine() {
        if (!fileReader.hasNextLine()) {
            fileReader = new GeoLifeFileReader(filesToRead.remove());
        }

        return fileReader.nextLine();
    }

    private boolean hasNextFile() {
        //not is empty
        return !filesToRead.isEmpty();

    }

    private boolean hasNextLine() {
        if (fileReader != null) {
            if (fileReader.hasNextLine()) {
                return true;
            }
        }
        return hasNextFile();
    }

    public ArrayList<GeoLifeTrajectoryLine> read(){

        ArrayList<GeoLifeTrajectoryLine> ret = new ArrayList();

        while(hasNextLine()){
            ret.add(readNextLine());
        }

        return ret;
    }
}
