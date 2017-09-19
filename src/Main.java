import geolife.GeoLifeExtractor;

import java.io.File;
import java.time.Duration;
import java.time.Instant;

public class Main {

    //radio range in meters
    private static final int RADIO_RANGE = 50;

    //outputfilename
    private static final String OUTPUT_FILENAME = "connections.txt";

    public static void main(String[] args) {
	    // write your code here
        //Coordinate c1 = new Coordinate(41.49008, -71.312796);
        //Coordinate c2 = new Coordinate(41.499498, -81.695391);
        //System.out.println(c1.distance(c2));
        //System.out.println(c2.distance(c1));

        File f = new File("/home/juliano/workspace/geolife-extracter/Geolife Trajectories 1.3/Data/");
        //File f = new File("/home/juliano/workspace/geolife-extracter/test/");
        GeoLifeExtractor gle = new GeoLifeExtractor(f);
        gle.configureRadioRange(RADIO_RANGE);
        gle.configureOutputFilename(OUTPUT_FILENAME);

        Instant start = Instant.now();
        gle.run();
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end));

    }
}
