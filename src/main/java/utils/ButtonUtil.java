package utils;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

/**
 * Created by DespicableMe on 26.02.2016.
 * Описание:
 */
public class ButtonUtil {
    private Location location;


    public ButtonUtil(Region region , String patternPath) throws FindFailed {
        this.location = region.find(new Pattern(patternPath).exact()).getTarget();
    }

    public Location getLocation() {
        return location;
    }

}
