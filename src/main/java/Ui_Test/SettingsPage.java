package Ui_Test;

import Ui_Test.SettingsSubPages.CheckpointSubPage;
import Ui_Test.SettingsSubPages.ConnSubPage;
import Ui_Test.SettingsSubPages.GeneralSubPage;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import utils.ButtonUtil;
import utils.Props;

import java.util.concurrent.TimeUnit;

/**
 * Created by DespicableMe on 29.02.2016.
 * Описание:
 */
@SuppressWarnings("ConstantConditions")
public class SettingsPage extends MainController {
    private ButtonUtil general;
    private ButtonUtil connection;
    private ButtonUtil checkpoint;
    private ButtonUtil parking;
    private ButtonUtil users;
    private ButtonUtil integration;

    private GeneralSubPage gsp;

    private ButtonUtil getButton(int button) {
        try {
            switch (button) {
                case 1:
                    if (general == null) {
                        return general = new ButtonUtil(screen, Props.getPathForRun("General_Button.png"));
                    } else return general;
                case 2:
                    if (connection == null) {
                        return connection = new ButtonUtil(screen, Props.getPathForRun("Connection_button.png"));
                    } else return connection;
                case 3:
                    if (checkpoint == null || switchedOperationMode) {
                        return checkpoint = new ButtonUtil(screen, Props.getPathForRun("Checkpoint_Button.png"));
                    } else return checkpoint;
                case 4:
                    if (parking == null || switchedOperationMode) {
                        return parking = new ButtonUtil(screen, Props.getPathForRun("Parking_Button.png"));
                    } else return parking;
                case 5:
                    if (integration == null || switchedOperationMode) {
                        return integration = new ButtonUtil(screen, Props.getPathForRun("Integration_button.png"));
                    } else return integration;
                case 6:
                    if (users == null || switchedOperationMode) {
                        return users = new ButtonUtil(screen, Props.getPathForRun("Users_Button.png"));
                    } else return users;
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
        Pattern button = new Pattern(Props.getPathForRun("Apply_button_Settings.png"));
        try {
            screen.click(button);
            TimeUnit.SECONDS.sleep(1);
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public GeneralSubPage onGeneral() {
        if (gsp == null) {
            gsp = new GeneralSubPage();
        }
        return gsp;
    }

    public GeneralSubPage clickGeneral() {
        try {
            ButtonUtil button = getButton(1);
            screen.click(button.getPattern());
            stateSwitch = checkState(button);
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
            try {
                System.out.println("Trying to find inactive button ");
                screen.click(Props.getPathForRun("General_NA_Button.png"));
            } catch (FindFailed findFailed1) {
                System.out.println(findFailed1.getLocalizedMessage());
            }
        }
        if (gsp == null) {
            gsp = new GeneralSubPage();
        }
        return gsp;
    }

    public ConnSubPage clickConnection() {
        try {
            ButtonUtil button = getButton(2);
            // нужно создавать переменную что бы в методе stateSwitch не перевызвать метод get button
            screen.click(button.getPattern());
            stateSwitch = checkState(button);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return new ConnSubPage();
    }

    public CheckpointSubPage clickCheckpoint() {
        try {
            ButtonUtil button = getButton(3);
            screen.click(button.getPattern());
            stateSwitch = checkState(button);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return new CheckpointSubPage();
    }

    public SettingsPage clickParking() {
        try {
            ButtonUtil button = getButton(4);
            screen.click(button.getPattern());
            stateSwitch = checkState(button);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        return this;
    }


    public SettingsPage clickIntegration() {
        try {
            ButtonUtil button = getButton(5);
            screen.click(button.getPattern());
            stateSwitch = checkState(button);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public SettingsPage clickUsers() {
        try {
            ButtonUtil button = getButton(6);
            screen.click(button.getPattern());
            stateSwitch = checkState(button);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }
}
