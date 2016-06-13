package Pages;

import org.sikuli.script.FindFailed;
import util.ButtonUtil;
import util.Props;

/**
 * Created by DespicableMe on 09.03.2016.
 * Описание:
 */
public class CarDBPage extends MainController {

    public CarDBPage clickGroups() throws FindFailed {
        ButtonUtil groups;
        System.out.println("== Click Groups ");
        try {
            groups = new ButtonUtil(screen, Props.pathForRun("Groups_NA_Button_CarDBPage.png"));
            screen.click(groups.getPattern());
        } catch (FindFailed findFailed) {
            groups = new ButtonUtil(screen, Props.pathForRun("Groups_Button_CarDBSubPage.png"));
            screen.click(groups.getPattern());
            System.out.println(findFailed.getLocalizedMessage());
        }
        return this;
    }

    public CarDBPage clickVehicles() {
        ButtonUtil vehicles;
        System.out.println("== Click Vehicles ");
        try {
            vehicles = new ButtonUtil(screen, Props.pathForRun("Vehicles_Button_CarDBSubPage.png"));
            screen.click(vehicles.getPattern());
            checkState(vehicles);
        } catch (FindFailed findFailed) {
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

    public CarDBPage clickReactions() throws FindFailed {
        System.out.println("== Click Reactions");
        try {
            ButtonUtil reactions = new ButtonUtil(screen, Props.pathForRun("Reactions_Button_CarDBSubPage.png"));
            screen.click(reactions.getPattern());
            checkState(reactions);
        } catch (FindFailed findFailed) {
            ButtonUtil reactionsNA = new ButtonUtil(screen, Props.pathForRun("Reactions_NA_Button_CarDBSubPage.png"));
            screen.click(reactionsNA.getPattern());
            System.out.println("== Click Reactions");
            checkState(reactionsNA);
        }
        return this;
    }

    public void clickAdd() {
        System.out.println("== Click Add");
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
            System.out.println("== Click Delete");
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
    }


}
