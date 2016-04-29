package Pages;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import util.ButtonUtil;
import util.Props;

/**
 * Created by DespicableMe on 09.03.2016.
 * Описание:
 */
@SuppressWarnings("Duplicates")
public class ReportsPage extends MainController {
    private final Pattern ID = new Pattern(Props.pathForRun(""));


    public ReportsPage clickMakeExcelFile() {
        try {
            screen.click(Props.pathForRun("Excel_Button_ReportsPage.png"));
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
        return this;
    }

    public void clickRecResults() {
        try {
            ButtonUtil recResults = new ButtonUtil(screen, Props.pathForRun("RecResults_Button_RepotsPage.png"));
            screen.click(recResults.getPattern());
            checkState(recResults);
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }

    }

    public void clickGroupedByEvent() {
        try {
            ButtonUtil groupedEvent = new ButtonUtil(screen, Props.pathForRun("GroupedByEvent_Button_ReportsPage.png"));
            screen.click(groupedEvent.getPattern());
            checkState(groupedEvent);
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
    }

    public void clickGenerate() {

        try {
            screen.click(Props.pathForRun("Generate_Button_ReportsPage.png"));
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
    }

     public boolean isValidPage(){
        return isPage(ID);
    }
}
