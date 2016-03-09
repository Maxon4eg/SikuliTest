package Ui_Test;

import org.sikuli.script.FindFailed;
import utils.ButtonUtil;
import utils.Props;

/**
 * Created by DespicableMe on 09.03.2016.
 * Описание:
 */
public class CarDBPage extends MainController {

    public void clickGroups() {
        try {
            ButtonUtil groups = new ButtonUtil(screen, Props.getPathForRun("Groups_Button_CarDBSubPage.png"));
            screen.click(groups.getPattern());
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
    }

    public void clickVehicles() {
        try {
            ButtonUtil vehicles = new ButtonUtil(screen, Props.getPathForRun("Vehicles_Button_CarDBSubPage.png"));
            screen.click(vehicles.getPattern());
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
    }

    public void clickReactions() {
        try {
            ButtonUtil reactions = new ButtonUtil(screen, Props.getPathForRun("Reactions_Button_CarDBSubPage.png"));
            screen.click(reactions.getPattern());
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
    }

    public void clickAdd() {
        try {
            ButtonUtil add = new ButtonUtil(screen, Props.getPathForRun("Add_Button_CarDBPage.png"));
            screen.click(add.getPattern());
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
    }

    public void clickDelete() {
        try {
            ButtonUtil delete = new ButtonUtil(screen, Props.getPathForRun("Delete_Button_CarDBPage.png"));
            screen.click(delete.getPattern());
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
    }
}
