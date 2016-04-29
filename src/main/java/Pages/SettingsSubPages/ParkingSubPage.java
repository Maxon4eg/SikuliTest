package Pages.SettingsSubPages;

import Pages.SettingsPage;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import util.Props;

/**
 * Created by DespicableMe on 09.03.2016.
 * Описание:
 */
public class ParkingSubPage extends SettingsPage {
    private final Pattern ID = new Pattern(Props.pathForRun("AddParking_Button_ParkingSubPage.png"));


    public ParkingSubPage clickAddParking (){
        try {
            screen.click(Props.pathForRun("AddParking_Button_ParkingSubPage.png"));
        } catch (FindFailed findFailed){
            System.out.println(findFailed.getLocalizedMessage());
        }
        return this;
    }

    public boolean isValidPage(){
        return isPage(ID);
    }


}
