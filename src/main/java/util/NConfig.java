package util;

import java.io.*;


/**
 * Reader of Numberok Config File
 * <br>
 * <br> Channel1File - video path
 * <br> Channel1RTSPurl - rtsp string
 * <br> LprZone(CZ)enabled c- channel -z zone  1-enabled
 * <br>Checkpoint(N) n - number of cp
 * <br>Checkpoint(N)EntranceChannel from "0'
 * <br>Checkpoint(N)EntranceZone from "0"
 * <br>Checkpoint(N)EntranceWhom 0 -none;1-all;2-only
 * <br>Checkpoint(N)ExitChannel
 * <br>Checkpoint(N)ExitWhom
 * <br>Checkpoint(N)ExitZone
 * <br>Checkpoint(N)DeterminatePassageByPhotodetectors 0\1
 * <br>Checkpoint(N)DeterminatePassageByRecognitionZones 0\1
 */

public class NConfig {
    private static File configFile = new File("C:\\ProgramData\\FF\\NumberOk3\\NumberOk.ini");
    private static File tempConfig = new File("C:\\ProgramData\\FF\\NumberOk3\\TempConfig.ini");


    public static String get(String key) {
        String line;
        String value = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    value = line.split(" ")[1];
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

    public static int getInt (String key){
        return Integer.parseInt(get(key));
    }


    public static void set(String key, String value) { // TODO: 01.06.2016  check it
        BufferedReader reader;
        BufferedWriter writer;
        String line;

        boolean found = false;

        try {
            reader = new BufferedReader(new FileReader(configFile));
            writer = new BufferedWriter(new FileWriter(tempConfig));

            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    found = true;
                    line = key + " " + value;
                }
                writer.write(line);
                writer.newLine();
            }
            if (!found) {
                writer.write(key + " " + value);
            }
            reader.close();
            writer.close();
            configFile.delete();
            tempConfig.renameTo(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
