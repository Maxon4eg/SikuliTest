package Pages;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import util.ButtonUtil;
import util.Props;

import java.util.concurrent.TimeUnit;

/**
 * Created by DespicableMe on 29.02.2016.
 * Описание:
 */

public class SettingsPage extends MainController {
    private ButtonUtil general;
    private ButtonUtil connection;
    private ButtonUtil checkpoint;
    private ButtonUtil parking;
    private ButtonUtil users;
    private ButtonUtil integration;

    private ButtonUtil getButton(int button) throws FindFailed {
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
    }

    public SettingsPage clickApply() throws FindFailed {
        Pattern button = new Pattern(Props.pathForRun("Apply_button_Settings.png"));
        screen.click(button);
        System.out.println("== Click Apply");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this;
    }


    public SettingsPage clickGeneral() throws FindFailed, NullPointerException {
        try {
            ButtonUtil button = getButton(1);
            screen.click(button.getPattern());
            stateSwitch = checkState(button);
        } catch (FindFailed findFailed) {
            screen.click(Props.pathForRun("General_NA_Button.png"));
        }
        System.out.println("== Click General ");
        return this;

    }

    public SettingsPage clickConnection() throws FindFailed, NullPointerException {
        ButtonUtil button = getButton(2);
        // нужно создавать переменную что бы в методе stateSwitch не перевызвать метод get button
        screen.click(button.getPattern());
        System.out.println("== Click Connection ");
        stateSwitch = checkState(button);
        return this;
    }

    public SettingsPage clickCheckpoint() throws FindFailed, NullPointerException {
        ButtonUtil button = getButton(3);
        screen.click(button.getPattern());
        System.out.println("== Click Checkpoint ");
        stateSwitch = checkState(button);
        return this;
    }

    public SettingsPage clickParking() throws FindFailed, NullPointerException {
        ButtonUtil button = getButton(4);
        screen.click(button.getPattern());
        System.out.println("== Click Parking ");
        stateSwitch = checkState(button);
        return this;
    }


    public SettingsPage clickIntegration() throws FindFailed, NullPointerException {
        ButtonUtil button = getButton(5);
        screen.click(button.getPattern());
        System.out.println("== Click Integration ");
        stateSwitch = checkState(button);
        return this;
    }

    public SettingsPage clickUsers() throws FindFailed, NullPointerException {
        ButtonUtil button = getButton(6);
        screen.click(button.getPattern());
        System.out.println("== Click users ");
        stateSwitch = checkState(button);
        return this;
    }
}
