package Ui_Test.SettingsSubPages;

import Ui_Test.SettingsPage;
import org.sikuli.script.FindFailed;
import utils.Props;

/**
 * Created by DespicableMe on 09.03.2016.
 * Описание:
 */
public class ParkingSubPage extends SettingsPage {

    public ParkingSubPage clickAddParking (){
        try {
            screen.click(Props.getPathForRun("AddParking_Button_ParkingSubPage.png"));
        } catch (FindFailed findFailed){
            System.out.println(findFailed.getLocalizedMessage());
        }
        return this;
    }


}
