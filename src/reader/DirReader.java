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

    public DirReader(String rootStr) {
        this.root = new File(rootStr);
        this.trajectoryRoot = new File(root.getAbsolutePath() + "/Trajectory");
        trajectoryFiles = new ArrayList<File>(Arrays.asList(trajectoryRoot.listFiles()));
        reading = null;
        filesToRead = new LinkedList<File>(trajectoryFiles);
    }

    //should be called after verification by "hasNextLine"
    public GeoLifeTrajectoryLine getNextLine() {
        if (!fileReader.hasNextLine()) {
            fileReader = new GeoLifeFileReader(filesToRead.remove());
        }

        return fileReader.nextLine();
    }

    public boolean hasNextFile() {
        //not is empty
        return !filesToRead.isEmpty();

    }

    public boolean hasNextLine() {
        if (fileReader != null) {
            if (fileReader.hasNextLine()) {
                return true;
            }
        }
        return hasNextFile();
    }


}
