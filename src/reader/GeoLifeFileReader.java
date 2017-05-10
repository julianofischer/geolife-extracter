package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by juliano on 09/05/17.
 */
public class GeoLifeFileReader {

    //number of lines to skip (header)
    private static final int HEADER_LINES = 6;
    private static final String SEPARATOR = ",";

    private final File file;
    private Scanner scanner;
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");;

    public GeoLifeFileReader(File f){
        this.file = f;
        skipLines(HEADER_LINES);

        try {
            scanner = new Scanner  (file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public boolean hasNextLine(){
        return scanner.hasNextLine();
    }


    private void skipLines(int numberOfLines){
        for (int i=0; i<numberOfLines; i++){
            scanner.nextLine();
        }
    }

    public GeoLifeTrajectoryLine nextLine(){
        GeoLifeTrajectoryLine r = null;

        String line = scanner.nextLine();
        String row[] = line.split(SEPARATOR);

        double latitude = Double.parseDouble(row[0]);
        double longitude = Double.parseDouble(row[1]);
        double altitude = Double.parseDouble(row[3]);
        String dateStr = row[5];
        String timeStr = row[6];
        String dateTimeStr = dateStr + " " + timeStr;
        Date d = null;

        try {
            d = sdf.parse(dateTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        r = new GeoLifeTrajectoryLine(latitude, longitude, altitude, d);

        return r;

    }

}
