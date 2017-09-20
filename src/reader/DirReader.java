package reader;

import core.TrajectoryLine;
import geolife.GeoLifeTrajectoryFileReader;
import geolife.GeoLifeTrajectoryLine;

import java.io.File;
import java.util.*;

/**
 * Created by juliano on 10/05/17.
 * TODO: remover menções a "/Trajectory" tornando o código mais genérico.
 *       Somente desta forma esta classe poderá ser usada por todos os "readers"
 *       A menção ao diretório "/Trajectory" deve ser tratada pelo reader próprio
 *       do dataset, neste caso, GeoLifeReader.
 */
public class DirReader {
    private File root;
    private File trajectoryRoot;
    private List<File> trajectoryFiles;
    //private File reading;

    //strategy pattern
    private TrajectoryFileReader trajectoryFileReader = null;

    private Queue<File> filesToRead;

    public DirReader(File root, TrajectoryFileReader fr) {
        this.root = root;
        this.trajectoryRoot = new File(root.getAbsolutePath() + "/Trajectory");
        trajectoryFiles = new ArrayList<File>(Arrays.asList(trajectoryRoot.listFiles()));
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

    public List<TrajectoryLine> read(){

        ArrayList<TrajectoryLine> ret = new ArrayList();

        while(hasNextLine()){
            ret.add(readNextLine());
        }

        return ret;
    }
}
