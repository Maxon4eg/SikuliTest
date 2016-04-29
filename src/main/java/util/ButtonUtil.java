package util;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

/**
 * Created by DespicableMe on 26.02.2016.
 * Описание:
 */
public class ButtonUtil {
    private Pattern button;
    private int RGB;
    private Location target_RGB;
    private Location location;


    public ButtonUtil(Region region, String patternPath) throws FindFailed {
        button = new Pattern(patternPath).exact();
//        region.highlight(1,"GREEN");
        this.target_RGB = region.find(button).getTopLeft();
        this.RGB = target_RGB.getColor().getRGB();
        this.location = region.find(button.exact()).getTarget();
    }

    public Location getLocation() {
        return location;
    }

    public int getRGB() {
        return RGB;
    }

    public Location getTarget_RGB() {
        return target_RGB;
    }

    public Pattern getPattern() {
        return button;
    }
}
