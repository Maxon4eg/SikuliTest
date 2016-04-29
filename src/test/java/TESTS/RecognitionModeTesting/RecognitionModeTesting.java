package tests.RecognitionModeTesting;

import tests.AbstractTest;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Testing is pages accesability correct in recognition mode
 */
public class RecognitionModeTesting extends AbstractTest {
    @Override
    @BeforeMethod
    public void setUp() throws Exception {
        slave.pasteCleanIni(2);//2 channels
        slave.runNumberok();
        Assert.assertTrue(slave.waitAppearing(), "Numberok is'nt appeared");
        slave.maximize();
    }

    @Override
    @AfterMethod
    public void tearDown() throws Exception {
        slave.closeNumberok();
    }

    @Test
    public void testDefaultPages() throws Exception {
        slave.clickResults();
        //check on results
        testsOnResultPage();
        slave.clickCarDB();
        // go to groups to check validity of groups
        slave.onCarDbPage().clickGroups();
        Assert.assertTrue(slave.inGroupsAndACSPage().isGoupsInModeCorrect(1), "Groups in current mode is not correct ");
        //go to settings page
        testOnSettingsPages();
    }

    @Test
    public void testDowncastFromParkingToRecognition() throws Exception {
        slave.clickSettings();
        slave.inGeneralSubPage().setOperationMode(3).clickApply();
        slave.onSettingsPage().clickParking();
        //checkSettings
        Assert.assertTrue(slave.inParkingSettingsPage().isValidPage(), "Parking page is not valid ");
        slave.onSettingsPage().clickCheckpoint();
        Assert.assertTrue(slave.inCheckpointSubPage().isValidPage(), "CP page is not valid ");
        //check resuts Page
        slave.clickResults();
        Assert.assertTrue(slave.inRecognitionResults().isValidPage(), "Recognition Results page is not valid");
        slave.onResultsPage().clickGrouped();
        Assert.assertTrue(slave.inGropedResults().isValidPage(), "Grouped by number is not valid ");
        slave.onResultsPage().clickParkings();
        Assert.assertTrue(slave.inParkngResults().isValid(), "Parking results page is not valid ");
        //go back in gui
        slave.onResultsPage().clickRecognitionResults();
        //check groups
        slave.clickCarDB();
        slave.onCarDbPage().clickGroups();
        Assert.assertTrue(slave.inGroupsAndACSPage().isGoupsInModeCorrect(2), "Groups are not correct in parking mode");
        //go back
        slave.clickSettings();
        slave.onSettingsPage().clickGeneral();
        slave.inGeneralSubPage()
                .setOperationMode(1)
                .warningClick(1)
                .clickApply();
        //assert that pages is absent
        testOnSettingsPages();
        slave.clickResults();
        testsOnResultPage();
        slave.clickSettings();
        testOnSettingsPages();

    }

    @Test
    public void testTestDowncastFromCPToRecognition() throws Exception {
        slave.clickSettings();
        slave.inGeneralSubPage().setOperationMode(2).clickApply();
        //checkSettings
        try {
            slave.onSettingsPage().clickParking();
            Assert.assertTrue(false, " Parking Settings is Present in CP mode");
        } catch (FindFailed | NullPointerException e) {
            Assert.assertTrue(true, " Parking Settings is Absent in CP mode");
        }

        slave.onSettingsPage().clickCheckpoint();
        Assert.assertTrue(slave.inCheckpointSubPage().isValidPage(), "CP page is not valid ");

        //check resuts Page
        slave.clickResults();
        Assert.assertTrue(slave.inRecognitionResults().isValidPage(), "Recognition Results page is not valid");
        slave.onResultsPage().clickGrouped();
        Assert.assertTrue(slave.inGropedResults().isValidPage(), "Grouped by number is not valid ");
        try {
            slave.onResultsPage().clickParkings();
            Assert.assertTrue(slave.inParkngResults().isValid(), "Parking results page is not Allowed in CP mode ");
        } catch (FindFailed e) {
            Assert.assertTrue(true, "Parking results page is absent in CP mode");
        }

        //go back in gui
        slave.onResultsPage().clickRecognitionResults();

        //check groups
        slave.clickCarDB();
        slave.onCarDbPage().clickGroups();
        Assert.assertTrue(slave.inGroupsAndACSPage().isGoupsInModeCorrect(2), "Groups are not correct in parking mode");

        //go back
        slave.clickSettings();
        slave.onSettingsPage().clickGeneral();
        slave.inGeneralSubPage()
                .setOperationMode(1)
                .warningClick(1)
                .clickApply();
        //assert that pages is absent
        testOnSettingsPages();
        slave.clickResults();
        testsOnResultPage();
        slave.clickSettings();
        testOnSettingsPages();

    }

    private void testOnSettingsPages() {

        boolean pagePresent;
        try {
            slave.onSettingsPage().clickCheckpoint();
            pagePresent = true;
            slave.onSettingsPage().clickGeneral();//to return in GUI

        } catch (FindFailed e) {
            pagePresent = false;
        }
        Assert.assertFalse(pagePresent, "CP button page is Present");

        try {
            slave.onSettingsPage().clickParking();
            pagePresent = true;
            slave.onSettingsPage().clickGeneral();// to return in GUI
        } catch (FindFailed findFailed) {
            pagePresent = false;
        }
        Assert.assertFalse(pagePresent, "Parking button page is Present ");

    }

    /**
     * Click on results page before Using
     */
    private void testsOnResultPage() {
        boolean pagePresent;
        try {
            slave.onResultsPage().clickGrouped();// try ty click on grouped
            pagePresent = true;
            slave.onResultsPage().clickRecognitionResults();
        } catch (FindFailed e) {
            pagePresent = false; // if absent then false
        }
        Assert.assertFalse(pagePresent, "Grouped is Present "); // assert that

        try {
            slave.onResultsPage().clickParkings();
            pagePresent = true;
            slave.onResultsPage().clickRecognitionResults();// to return back in gui
        } catch (FindFailed e) {
            pagePresent = false;
        }
        Assert.assertFalse(pagePresent, "Parkings is Present");
    }
}
