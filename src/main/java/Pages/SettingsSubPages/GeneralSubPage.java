package Pages.SettingsSubPages;


import Pages.SettingsPage;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import util.Props;

/**
 * Created by DespicableMe on 26.02.2016.
 * Описание:
 */
public class GeneralSubPage extends SettingsPage {
    private final Pattern ID = new Pattern(Props.pathForRun("GeneralSubPage_ident.png"));

    /**
     * @param mode - change mode param :
     *             <br>1 - recognition
     *             <br>2 - Checkpoint
     *             <br>3 - Parking
     */

    public GeneralSubPage setOperationMode(int mode) {
        String setMode;
        Pattern modeButton;
        try {
            switch (mode) {
                case 1:
                    modeButton = new Pattern(Props.pathForRun("Recog_mode.png")).targetOffset(-55, 0);
                    switchedOperationMode = true;
                    setMode = "Recognition mode";
                    break;
                case 2:
                    modeButton = new Pattern(Props.pathForRun("Checkpoint_mode.png")).targetOffset(-100, 0);
                    switchedOperationMode = true;
                    setMode = " Checkpoint mode ";
                    break;
                case 3:
                    modeButton = new Pattern(Props.pathForRun("Parking_mode.png")).targetOffset(-25, 0);
                    switchedOperationMode = true;
                    setMode = " Parking Mode ";
                    break;
                default:
                    System.out.println("Please choose from 1-3");
                    return this;
            }
            System.out.println( "== Set operation mode to " + setMode);
            screen.click(modeButton);

            return this;
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    /**
     * @param chose 1 - ok
     *              <br>2 - cancel
     */

    public GeneralSubPage warningClick(int chose) {
        Pattern button;
        try {
            switch (chose) {
                case 1:
                    button = new Pattern(Props.pathForRun("OK_warningMsg.png"));
                    screen.click(button);
                    System.out.println("== click OK in warning massage ");
                    return this;
                case 2:
                    button = new Pattern(Props.pathForRun("Cancel_warningMsg.png"));
                    screen.click(button);
                    System.out.println("== click Cancel in warning massage ");
                    return this;
            }
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    /**
     * @param chose <br>1 - строгое сравнение
     *              <br> 2- мягкое
     */

    public GeneralSubPage setComparisonMode(int chose) {
        String compMode;
        Pattern mode ;
        switch (chose) {
            case 1:
                mode = new Pattern(Props.pathForRun("StrongCom_Rad_GeneralSubPage.png"));
               compMode = " Strong comparison ";
                break;
            case 2:
                mode = new Pattern(Props.pathForRun("SoftCom_Rad_GeneralSubPage.png"));
                compMode = " Soft comparison ";
                break;
            default:
                return this;
        }
        try {
            System.out.println("== Set comparison mode to " + compMode);
            screen.click(mode);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public boolean isValidPage() {
        return isPage(ID);
    }


}
