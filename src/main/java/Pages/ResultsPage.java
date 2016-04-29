package Pages;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import util.ButtonUtil;
import util.Props;

/**
 * Created by DespicableMe on 09.03.2016.
 * Описание:
 */
@SuppressWarnings("Duplicates")//recheck later

public class ResultsPage extends MainController {

    public ResultsPage clickGrouped() throws FindFailed {
        System.out.println("== Click Grouped By Number ");
        ButtonUtil grouped;
        try {
            grouped = new ButtonUtil(screen, Props.pathForRun("Grouped_Button_ResultsPage.png"));
            isStateSwitched();
            screen.click(grouped.getPattern());
            checkState(grouped);
        } catch (FindFailed findFailed) {
            try {
                grouped = new ButtonUtil(screen, Props.pathForRun("Grouped_Active_Button_ResultsPage.png"));
                screen.click(grouped.getPattern());
            } catch (FindFailed findFailed1) {
                throw new FindFailed("Cant find button Grouped");
            }
        }
        return this;
    }

    public ResultsPage clickParkings() throws FindFailed {
        System.out.println("== Click Parkings");
        ButtonUtil parkings;
        try {
            parkings = new ButtonUtil(screen, Props.pathForRun("Parkings_Button_ResultsPage.png"));
            screen.click(parkings.getPattern());
            checkState(parkings);
        } catch (FindFailed findFailed) {
            parkings = new ButtonUtil(screen, Props.pathForRun("Parkings_A_Button_ResultsPage.png"));
            screen.click(parkings.getPattern());
        }
        return this;
    }

    public ResultsPage clickRecognitionResults() throws FindFailed {
        System.out.println("== Click Recognition results");
        ButtonUtil recResults;
        try {
            recResults = new ButtonUtil(screen, Props.pathForRun("RecognitionResults_Button_ResutlsPage.png"));
            screen.click(recResults.getLocation());
            checkState(recResults);
        } catch (FindFailed findFailed) {
            recResults = new ButtonUtil(screen, Props.pathForRun("RecognitionResults_NA_Button_ResultsPage.png"));
            screen.click(recResults.getLocation());
            checkState(recResults);
        }
        return this;
    }

    protected Region findRegion(Pattern headers) {
        Region resultsRegion;
        try {
            resultsRegion = screen.find(headers);
            resultsRegion.setH(screen.getH() - 200); // расшираем регион
            return resultsRegion;
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            return null;
        }
    }

}
