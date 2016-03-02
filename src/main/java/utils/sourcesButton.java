package utils;

import org.sikuli.script.Location;
import org.sikuli.script.Match;

/**
 * Created by DespicableMe on 02.03.2016.
 * Описание:
 */
public class SourcesButton {

    private Location location ;

    public SourcesButton(Match match) {
        this.location=match.getTarget();
    }

    public Location getLocation() {
        return location;
    }
}
