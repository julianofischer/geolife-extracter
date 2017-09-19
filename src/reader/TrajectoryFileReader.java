package reader;

import core.TrajectoryLine;

import java.io.File;

/**
 * Created by juliano on 19/09/17.
 */
public interface TrajectoryFileReader {

    TrajectoryLine nextLine();
    boolean hasNextLine();
    void prepareFile(File f);

}
