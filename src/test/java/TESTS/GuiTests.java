package tests;

/**
 *  For testing gui validity
 *  While tests is executing numberOk is not closing
 *  please be sure that you are not mess with opened pages
 */

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GuiTests extends AbstractTest {

    @BeforeClass
    public void setUp() throws Exception  {
        slave.pasteCleanIni(2);
        slave.runNumberok();
        Assert.assertTrue(slave.waitAppearing());
        slave.maximize();
    }


    @AfterClass
    public void tearDown() throws Exception {
        slave.closeNumberok();
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
        Assert.assertTrue(slave.inConnSubPage().isValidPage(), "Connection page is'nt valid ");

        Assert.assertTrue(slave.onSettingsPage().clickIntegration().isStateSwitched());
        Assert.assertTrue(slave.inIntegrationSubPage().isValidPage(), "Page Integration is'nt valid ");

        Assert.assertTrue(slave.onSettingsPage().clickUsers().isStateSwitched());
        Assert.assertTrue(slave.inUsersSubPage().isValidPage(), "Users page Users is'nt valid ");

        slave.onSettingsPage().clickGeneral();
        Assert.assertTrue(slave.inGeneralSubPage().isValidPage(), "General sub page is'nt valid ");

        slave.inGeneralSubPage().setOperationMode(2).clickApply();
        Assert.assertTrue(slave.onSettingsPage().clickCheckpoint().isStateSwitched());
        Assert.assertTrue(slave.inCheckpointSubPage().isValidPage(), "Checkpoint sub page is'nt valid ");

        slave.onSettingsPage().clickGeneral();
        slave.inGeneralSubPage().setOperationMode(3).clickApply();
        Assert.assertTrue(slave.onSettingsPage().clickParking().isStateSwitched());
        Assert.assertTrue(slave.inParkingSettingsPage().isValidPage(), "Parking sub page is'nt valid ");


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
