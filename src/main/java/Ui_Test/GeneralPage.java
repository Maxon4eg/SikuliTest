package Ui_Test;


import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import utils.Props;

/**
 * Created by DespicableMe on 26.02.2016.
 * Описание:
 */
public class GeneralPage extends AbstractPage {
    private Screen screen;

    GeneralPage(Screen screen) {
        this.screen = screen;
    }

    private Region generalRegion() {
        Pattern page = new Pattern(Props.getPathForRun("Settings_Page.png"));
        try {
            return screen.find(page);

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            System.out.println("could not find settings region " + findFailed.getMessage());
            return null;
        }
    }

    /**
     * @param mode - change mode param :
     *             1 - recognition
     *             2 - Checkpoint
     *             3 - Parking
     */

    GeneralPage setOperationMode(int mode) throws FindFailed {
        Pattern modeButton;
        Region region = generalRegion();
        Assert.assertTrue(region != null);

        switch (mode) {
            case 1:
                modeButton = new Pattern(Props.getPathForRun("Recog_mode.png")).targetOffset(-55, 0);
                region.click(modeButton);
                return this;
            case 2:
                modeButton = new Pattern(Props.getPathForRun("Checkpoint_mode.png")).targetOffset(-100, 0);
                region.click(modeButton);
                return this;
            case 3:
                modeButton = new Pattern(Props.getPathForRun("Parking_mode.png")).targetOffset(-25, 0);
                region.click(modeButton);
                return this;
            default:
                System.out.println("Please choose from 1-3");
                break;
        }
        return this;
    }

    void warningClick(int chose) {
        Pattern button;
        try {
            switch (chose) {
                case 1:
                    button = new Pattern(Props.getPathForRun("OK_warningMsg.png"));
                    screen.click(button);
                    break;
                case 2:
                    button = new Pattern(Props.getPathForRun("Cancel_warningMsg.png"));
                    screen.click(button);
                    break;

            }
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    void clickApply() {
        Pattern button = new Pattern(Props.getPathForRun("Apply_GeneralPage.png"));
        try {
            screen.click(button);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }
}
