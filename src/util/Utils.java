package util;

/**
 * Created by juliano on 15/09/17.
 */
public class Utils {

    private static final double FEET_INTO_METERS = 0.3048;

    /*
       Converts feet into meters.
     */
    public static double feetToMeters(double feet){
        return feet * FEET_INTO_METERS;
    }
}
