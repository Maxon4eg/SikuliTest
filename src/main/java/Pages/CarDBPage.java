package Pages;

import org.sikuli.script.FindFailed;
import utils.ButtonUtil;
import utils.Props;

/**
 * Created by DespicableMe on 09.03.2016.
 * Описание:
 */
@SuppressWarnings("Duplicates")
public class CarDBPage extends MainController {

    public CarDBPage clickGroups() {
        try {
            ButtonUtil groups = new ButtonUtil(screen, Props.pathForRun("Groups_Button_CarDBSubPage.png"));
            screen.click(groups.getPattern());
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
        return this;
    }

    public CarDBPage clickVehicles() {
        ButtonUtil vehicles;
        try {
            vehicles = new ButtonUtil(screen, Props.pathForRun("Vehicles_Button_CarDBSubPage.png"));
            screen.click(vehicles.getPattern());
            checkState(vehicles);
        } catch (FindFailed findFailed) {
            System.out.println("trying to find NA button");
            try {
                vehicles = new ButtonUtil(screen, Props.pathForRun("Vehicles_Button_CarDBSubPage_NA.png"));
                screen.click(vehicles.getPattern());
                checkState(vehicles);
            } catch (FindFailed findFailed1) {
                System.out.println(findFailed.getLocalizedMessage());
            }
        }
        return this;
    }

    public CarDBPage clickReactions() {
        try {
            ButtonUtil reactions = new ButtonUtil(screen, Props.pathForRun("Reactions_Button_CarDBSubPage.png"));
            screen.click(reactions.getPattern());
            checkState(reactions);
        } catch (FindFailed findFailed) {
            try {
                System.out.println("trying to find not active button ");
                ButtonUtil reactionsNA = new ButtonUtil(screen, Props.pathForRun("Reactions_NA_Button_CarDBSubPage.png"));
                screen.click(reactionsNA.getPattern());
                checkState(reactionsNA);
            } catch (FindFailed findFailed1) {
                System.out.println(findFailed1.getLocalizedMessage());
            }
        }
        return this;
    }

    public void clickAdd() {
        try {
            ButtonUtil add = new ButtonUtil(screen, Props.pathForRun("Add_Button_CarDBPage.png"));
            screen.click(add.getPattern());
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
    }

    public void clickDelete() {
        try {
            ButtonUtil delete = new ButtonUtil(screen, Props.pathForRun("Delete_Button_CarDBPage.png"));
            screen.click(delete.getPattern());
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
    }


}
