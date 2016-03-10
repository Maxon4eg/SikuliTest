package Ui_Test;

import Ui_Test.ResultsSubPages.RecognitionResults;
import Ui_Test.SettingsSubPages.CheckpointSubPage;
import Ui_Test.SettingsSubPages.ConnSubPage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sikuli.script.App;

/**
 * Created by DespicableMe on 29.02.2016.
 * Описание:
 */
public class TestForTest extends MainController {

//    @Test
    public void testing(){
//        clickView();
//        clickSettings();
//        clickCarDB().clickReactions();
//        clickCarDB().clickVehicles();
//        clickCarDB().clickGroups();

        CheckpointSubPage cp = clickSettings().clickCheckpoint();
        cp.passageDetermination(3);
//        cp.chooseCP(2);
//        cp.cpEntry(1,1,1);


//        clickReports().clickGroupedByEvent();
//        clickReports().clickRecResults();
//        clickReports().clickGenerate();
//        clickReports().clickMakeExcelFile();
    }
    @Test
    public void observe (){


    }



}
