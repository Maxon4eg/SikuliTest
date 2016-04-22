package Pages;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import utils.ButtonUtil;
import utils.Props;

/**
 * Created by DespicableMe on 09.03.2016.
 * Описание:
 */
@SuppressWarnings("Duplicates")//recheck later

public class ResultsPage extends MainController {
    private final Pattern ID = new Pattern(Props.pathForRun(""));

    public ResultsPage clickGrouped() {
        ButtonUtil grouped;
        try {
            grouped = new ButtonUtil(screen, Props.pathForRun("Grouped_Button_ResultsPage.png"));
            isStateSwitched();
            screen.click(grouped.getPattern());
            checkState(grouped);
        } catch (FindFailed findFailed) {
            System.out.println("can't find NA button. Trying to find Active Button");
            ButtonUtil groupedA;
            try {
                groupedA = new ButtonUtil(screen, Props.pathForRun("Grouped_Active_Button_ResultsPage.png"));
                screen.click(groupedA.getPattern());
            } catch (FindFailed findFailed1) {
                findFailed1.printStackTrace();
            }
        }
        return this;
    }

    public ResultsPage clickParkings() {
        ButtonUtil parkings;
        try {
            parkings = new ButtonUtil(screen, Props.pathForRun("Parkings_Button_ResultsPage.png"));
            screen.click(parkings.getPattern());
            checkState(parkings);
        } catch (FindFailed findFailed) {
            System.out.println("can't find NA button trying to find Active Button");
            ButtonUtil parkingsA;
            try {
                parkingsA = new ButtonUtil(screen, Props.pathForRun("Parkings_A_Button_ResultsPage.png"));
                screen.click(parkingsA.getPattern());
            } catch (FindFailed findFailed1) {
                findFailed1.printStackTrace();
            }
        }
        return this;
    }

    public ResultsPage clickRecognitionResults() {
        try {
            ButtonUtil recResults = new ButtonUtil(screen, Props.pathForRun("RecognitionResults_Button_ResutlsPage.png"));
            screen.click(recResults);
            checkState(recResults);
        } catch (FindFailed findFailed) {
            System.out.println("can't find active Button! trying to click not active");
            try {
                ButtonUtil recResutltsNA = new ButtonUtil(screen, Props.pathForRun("RecognitionResults_NA_Button_ResultsPage.png"));
                checkState(recResutltsNA);
            } catch (FindFailed findFailed1) {
                System.out.println(findFailed1.getLocalizedMessage());
            }
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

    public boolean isValidPage(){
        return isPage(ID);
    }


}
