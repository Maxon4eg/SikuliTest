package utils;

import java.io.*;
import java.util.Properties;

/**
 * Reader of Numberok Config File
 */

public class NConfig   {
    private static File configFile = new File("C:\\ProgramData\\FF\\NumberOk3\\NumberOk.ini");

    private static Properties load() {
        Properties config = new Properties();
        InputStream in;
        try {
            in = new FileInputStream(configFile);
            config.load(in);
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    /**
     * @param key какие ключи будем получать
     *            <br> Channel1File - video path
     *            <br> Channel1RTSPurl - rtsp string
     *            <br> LprZone(CZ)enabled c- channel -z zone  1-enabled
     *            <br>Checkpoint(N) n - number of cp
     *            <br>Checkpoint(N)EntranceChannel from "0'
     *            <br>Checkpoint(N)EntranceZone from "0"
     *            <br>Checkpoint(N)EntranceWhom 0 -none;1-all;2-only
     *            <br>Checkpoint(N)ExitChannel
     *            <br>Checkpoint(N)ExitWhom
     *            <br>Checkpoint(N)ExitZone
     *            <br>Checkpoint(N)DeterminatePassageByPhotodetectors 0\1
     *            <br>Checkpoint(N)DeterminatePassageByRecognitionZones 0\1
     * @return
     */

    public static String get(String key) {
        return load().getProperty(key);
    }

}
