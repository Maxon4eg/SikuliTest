package TESTS;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GuiTests extends AbstractTest {


    @Before
    public void setUp() throws Exception  {
        slave.pasteCleanIni(2);
        slave.runNumberok();
        Assert.assertTrue(slave.isAppear());
        slave.maximize();
    }


    @After
    public void tearDown() throws Exception {
        slave.closeNumberok();
    }

    @Test
    public void Test9Channels() {
        Assert.assertEquals("Expected 2 channels", 2, slave.onViewPage().howManyChannels());
    }

//    @Test
    public void isPagesCorrect (){

    }

    @Test
    public void mainControllerStateCheck() {
        Assert.assertTrue(slave.clickResults().isStateSwitched());
        Assert.assertTrue(slave.clickCarDB().isStateSwitched());
        Assert.assertTrue(slave.clickReports().isStateSwitched());
        Assert.assertTrue(slave.clickSettings().isStateSwitched());
        Assert.assertTrue(slave.clickView().isStateSwitched());
    }

    @Test
    public void testSettings_PagesIsPages() throws Exception {

        Assert.assertTrue(slave.clickSettings().isStateSwitched());
        Assert.assertTrue(slave.onSettingsPage().clickConnection().isStateSwitched());
        Assert.assertTrue("Connection page is'nt valid ",slave.inConnSubPage().isValidPage());

        Assert.assertTrue(slave.onSettingsPage().clickIntegration().isStateSwitched());
        Assert.assertTrue("Page is'nt valid ",slave.inConnSubPage().isValidPage());

        Assert.assertTrue(slave.onSettingsPage().clickUsers().isStateSwitched());
        Assert.assertTrue("Users page is'nt valid ",slave.inUsersSubPage().isValidPage());

        slave.onSettingsPage().clickGeneral();
        Assert.assertTrue("General sub page is'nt valid ",slave.inGeneralSubPage().isValidPage());
//      Do this in other test
//        slave.inGeneralSubPage().setOperationMode(2).clickApply();
//        Assert.assertTrue(slave.onSettingsPage().clickCheckpoint().isStateSwitched());
//        Assert.assertTrue("Checkpoint sub page is'nt valid ",slave.inCheckpointSubPage().isValidPage());
//
//        slave.onSettingsPage().clickGeneral();
//        slave.inGeneralSubPage().setOperationMode(3).clickApply();
//        Assert.assertTrue(slave.onSettingsPage().clickParking().isStateSwitched());
//        Assert.assertTrue("Parking sub page is'nt valid ",slave.inParkingSettingsPage().isValidPage());
    }

    @Test
    public void testResultsSubPages() throws Exception {
        slave.clickResults();
        Assert.assertTrue(slave.onResultsPage().clickRecognitionResults().isStateSwitched());
        slave.clickSettings();
        slave.inGeneralSubPage().setOperationMode(2).clickApply();
        slave.clickResults();
        Assert.assertTrue(slave.onResultsPage().clickGrouped().isStateSwitched());
    }

}
