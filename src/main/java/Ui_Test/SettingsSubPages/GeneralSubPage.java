package Ui_Test.SettingsSubPages;


import Ui_Test.SettingsPage;
import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import utils.Props;

/**
 * Created by DespicableMe on 26.02.2016.
 * Описание:
 */
public class GeneralSubPage extends SettingsPage {


    private Region generalRegion() {
//        clickGeneral();
        Pattern page = new Pattern(Props.pathForRun("Settings_Page.png"));
        try {
            return screen.find(page);

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            System.out.println("could not find settings region trying to go to General sub page");
            return null;
        }
    }

    /**
     * @param mode - change mode param :
     *             <br>1 - recognition
     *             <br>2 - Checkpoint
     *             <br>3 - Parking
     */

    public GeneralSubPage setOperationMode(int mode) {
        Pattern modeButton;
        Region region = generalRegion();
        Assert.assertTrue(region != null);
        try {
            switch (mode) {
                case 1:
                    modeButton = new Pattern(Props.pathForRun("Recog_mode.png")).targetOffset(-55, 0);
                    region.click(modeButton);
                    switchedOperationMode =true;
                    return this;
                case 2:
                    modeButton = new Pattern(Props.pathForRun("Checkpoint_mode.png")).targetOffset(-100, 0);
                    region.click(modeButton);
                    switchedOperationMode =true;
                    return this;
                case 3:
                    modeButton = new Pattern(Props.pathForRun("Parking_mode.png")).targetOffset(-25, 0);
                    region.click(modeButton);
                    switchedOperationMode =true;
                    return this;
                default:
                    System.out.println("Please choose from 1-3");
                    break;
            }
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    /**
     * @param chose 1 - ok 2 - cancel
     */

    public GeneralSubPage warningClick(int chose) {
        Pattern button;
        try {
            switch (chose) {
                case 1:
                    button = new Pattern(Props.pathForRun("OK_warningMsg.png"));
                    screen.click(button);
                    return this;
                case 2:
                    button = new Pattern(Props.pathForRun("Cancel_warningMsg.png"));
                    screen.click(button);
                    return this;

            }
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }


}
