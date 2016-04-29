package Pages;

import org.sikuli.script.FindFailed;
import util.ButtonUtil;
import util.Props;

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
            System.out.println("== Click Groups ");
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
            System.out.println("== Click Vehicles ");
        } catch (FindFailed findFailed) {
            try {
                vehicles = new ButtonUtil(screen, Props.pathForRun("Vehicles_Button_CarDBSubPage_NA.png"));
                screen.click(vehicles.getPattern());
                System.out.println("== Click Vehicles ");
                checkState(vehicles);
            } catch (FindFailed findFailed1) {
                System.out.println(findFailed.getLocalizedMessage());
            }
        }
        return this;
    }

    public CarDBPage clickReactions() throws FindFailed {
        try {
            ButtonUtil reactions = new ButtonUtil(screen, Props.pathForRun("Reactions_Button_CarDBSubPage.png"));
            screen.click(reactions.getPattern());
            System.out.println("== Click Reactions");
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
        try {
            ButtonUtil add = new ButtonUtil(screen, Props.pathForRun("Add_Button_CarDBPage.png"));
            screen.click(add.getPattern());
            System.out.println("== Click Add");
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
