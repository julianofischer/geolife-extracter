import geo.Coordinate;

import java.io.File;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Coordinate c1 = new Coordinate(41.49008, -71.312796);
        Coordinate c2 = new Coordinate(41.499498, -81.695391);
        System.out.println(c1.distance(c2));
        System.out.println(c2.distance(c1));

        File f = new File("/home/juliano/workspace/geolife-extracter/Geolife Trajectories 1.3/Data/181/");
        System.out.println(f.getName());
    }
}
