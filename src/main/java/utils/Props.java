package utils;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Props {

    private static Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();
        URL props = ClassLoader.getSystemResource("data.properties");
        try {
            PROPERTIES.load(props.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getProperty(String key) {
        //just to use short name
        return PROPERTIES.getProperty(key);
    }

    public static int getInt(String key){


        return new Integer(PROPERTIES.getProperty(key));
    }

    public static String getPathForRun( String file) {
        return ClassLoader.getSystemResource("run/"+file).getPath().toString().substring(1);
    }

    public static String getPathFor(String file) {
        // Using substring for full path to remove first slash.
        // Sikuli bug on Windows
        return ClassLoader.getSystemResource(file).getPath().toString()
                .substring(1);
    }


	public static void set (String key ,String value){
        PROPERTIES.setProperty(key, value);
    }

}
