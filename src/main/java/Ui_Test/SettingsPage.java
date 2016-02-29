package Ui_Test;

import org.sikuli.script.*;
import utils.Props;

/**
 * Created by DespicableMe on 29.02.2016.
 * Описание:
 */
public class SettingsPage extends AbstractPage {

    private Screen screen;
    private GeneralPage gp;

    public SettingsPage(Screen screen) {
        this.screen = screen;
    }

    private Location getButtonLoc(int button) {
        try {
            switch (button) {
                case 1:
                    MenuButton general = new MenuButton(screen, Props.getPathForRun("General_SubPage.png"));
                    return general.getLocation();
                case 2:
                    MenuButton connection = new MenuButton(screen, Props.getPathForRun("Conn_SubPage.png"));
                    return connection.getLocation();
                case 3:
                    MenuButton checkpoint = new MenuButton(screen, Props.getPathForRun("Checkpoint_SubPage.png"));
                    return checkpoint.getLocation();
                case 4:
                    MenuButton parking = new MenuButton(screen, Props.getPathForRun("Parking_SubPage.png"));
                    return parking.getLocation();
                case 5:
                    MenuButton integration = new MenuButton(screen, Props.getPathForRun("Integration_SubPage.png"));
                    return integration.getLocation();
                case 6:
                    MenuButton users = new MenuButton(screen, Props.getPathForRun("Users_SubPage.png"));
                    return users.getLocation();
                default:
                    System.out.println("Please choose from 1 - 6");
                    return null;
            }
        } catch (FindFailed findFailed) {
            System.out.println("could not find sub menu button " + findFailed.getMessage());
            return null;
        }

    }

    /**
     * Можем работать с страницей General только после вызова этого метода !
     */
    public SettingsPage clickGeneral() {
        try {
            screen.click(getButtonLoc(1));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        gp = new GeneralPage(screen);
        return this;
    }

    public SettingsPage clickConnection() {
        try {
            screen.click(getButtonLoc(2));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public SettingsPage clickCheckpoint() {
        try {
            screen.click(getButtonLoc(3));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public SettingsPage clickParking() {
        try {
            screen.click(getButtonLoc(4));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        return this;
    }


    public SettingsPage clickIntegration() {
        try {
            screen.click(getButtonLoc(5));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public SettingsPage clickUsers() {
        try {
            screen.click(getButtonLoc(6));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    /**
     * @param mode - change mode param :
     *             1 - recognition
     *             2 - Checkpoint
     *             3 - Parking
     */

    public SettingsPage switchMode(int mode) {
        clickGeneral();
        try {
            gp.setOperationMode(mode);
        } catch (FindFailed findFailed) {
            System.out.println("could not find radiobuttons " + findFailed.getMessage());
            findFailed.printStackTrace();
        }

        return this;
    }

    /**
     * @param button 1-ok 2 cancel
     */
    public SettingsPage warningClick(int button) {
        gp.warningClick(button);
        return this;
    }


}
