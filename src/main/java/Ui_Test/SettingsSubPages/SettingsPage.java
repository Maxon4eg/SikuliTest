package Ui_Test.SettingsSubPages;

import Ui_Test.MenuButton;
import Ui_Test.MainController;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Pattern;
import utils.Props;

/**
 * Created by DespicableMe on 29.02.2016.
 * Описание:
 */
public class SettingsPage extends MainController {


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

    public void clickApply() {
        Pattern button = new Pattern(Props.getPathForRun("Apply_GeneralPage.png"));
        try {
            screen.click(button);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
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
        return new GeneralSubPage(screen);
    }

    public SettingsPage clickConnection() {
        try {
            screen.click(getButtonLoc(2));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return new ConnSubPage(screen);
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
}