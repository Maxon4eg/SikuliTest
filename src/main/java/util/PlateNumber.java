package util;

import org.sikuli.script.Pattern;

import java.awt.image.BufferedImage;

public class PlateNumber {

    private final Pattern PATTERN;
    private final String NAME;


    /**
     *
     * @param img BufferedImage class, Sometimes may send some exceptions ...
     *            <br>please be aware
     */
    public PlateNumber(BufferedImage img,String number) {
        this.NAME=number;
        this.PATTERN = new Pattern(img);
    }

    public PlateNumber(Pattern PATTERN, String NAME) {
        this.PATTERN = PATTERN;
        this.NAME = NAME;
    }

    public String getName() {
        return NAME;
    }

    public Pattern getPattern() {
        return PATTERN;
    }
}
