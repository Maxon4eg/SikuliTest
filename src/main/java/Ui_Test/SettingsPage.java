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
                        return general = new ButtonUtil(screen, Props.pathForRun("General_Button.png"));
                    } else return general;
                case 2:
                    if (connection == null) {
                        return connection = new ButtonUtil(screen, Props.pathForRun("Connection_button.png"));
                    } else return connection;
                case 3:
                    if (checkpoint == null || switchedOperationMode) {
                        return checkpoint = new ButtonUtil(screen, Props.pathForRun("Checkpoint_Button.png"));
                    } else return checkpoint;
                case 4:
                    if (parking == null || switchedOperationMode) {
                        return parking = new ButtonUtil(screen, Props.pathForRun("Parking_Button.png"));
                    } else return parking;
                case 5:
                    if (integration == null || switchedOperationMode) {
                        return integration = new ButtonUtil(screen, Props.pathForRun("Integration_button.png"));
                    } else return integration;
                case 6:
                    if (users == null || switchedOperationMode) {
                        return users = new ButtonUtil(screen, Props.pathForRun("Users_Button.png"));
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
        Pattern button = new Pattern(Props.pathForRun("Apply_button_Settings.png"));
        try {
            screen.click(button);
            TimeUnit.SECONDS.sleep(1);
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void clickGeneral() {
        try {
            ButtonUtil button = getButton(1);
            screen.click(button.getPattern());
            stateSwitch = checkState(button);
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
            try {
                System.out.println("Trying to find inactive button ");
                screen.click(Props.pathForRun("General_NA_Button.png"));
            } catch (FindFailed findFailed1) {
                System.out.println(findFailed1.getLocalizedMessage());
            }
        }

    }

    public SettingsPage clickConnection() {
        try {
            ButtonUtil button = getButton(2);
            // нужно создавать переменную что бы в методе stateSwitch не перевызвать метод get button
            screen.click(button.getPattern());
            stateSwitch = checkState(button);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public SettingsPage clickCheckpoint() {
        try {
            ButtonUtil button = getButton(3);
            screen.click(button.getPattern());
            stateSwitch = checkState(button);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
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
