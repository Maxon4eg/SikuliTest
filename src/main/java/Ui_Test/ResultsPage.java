package Ui_Test;

import org.sikuli.script.FindFailed;
import utils.ButtonUtil;
import utils.Props;

/**
 * Created by DespicableMe on 09.03.2016.
 * Описание:
 */
@SuppressWarnings("Duplicates")//recheck later

public class ResultsPage extends MainController {

    public void clickGrouped() {
        try {
            ButtonUtil grouped = new ButtonUtil(screen, Props.getPathForRun("Grouped_Button_ResultsPage.png"));
            screen.click(grouped.getPattern());
            checkState(grouped);
        } catch (FindFailed findFailed){
            System.out.println(findFailed.getLocalizedMessage());
        }
    }


    public void clickParkings() {
        try {
            ButtonUtil parkings = new ButtonUtil(screen, Props.getPathForRun("Parkings_Button_ResultsPage.png"));
            screen.click(parkings.getPattern());
            checkState(parkings);
        } catch (FindFailed findFailed){
            System.out.println(findFailed.getLocalizedMessage());
        }
    }

    public void clickRecognitionResults() {
        try {
            ButtonUtil recResults = new ButtonUtil(screen, Props.getPathForRun("RecognitionResults_Button_ResutlsPage.png"));
            screen.click(recResults);
            checkState(recResults);
        } catch (FindFailed findFailed){
            System.out.println(findFailed.getLocalizedMessage());
            try {
                ButtonUtil recResutltsNA = new ButtonUtil(screen,Props.getPathForRun("RecognitionResults_NA_Button_ResultsPage.png"));
            } catch (FindFailed findFailed1){
                System.out.println(findFailed1.getLocalizedMessage());
            }
        }
    }
}
