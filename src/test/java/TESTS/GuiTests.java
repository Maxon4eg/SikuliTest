package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GuiTests extends AbstractTest {


    @BeforeMethod
    public void setUp() throws Exception  {
        slave.pasteCleanIni(2);
        slave.runNumberok();
        Assert.assertTrue(slave.waitAppearing());
        slave.maximize();
    }


    @AfterMethod
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
        Assert.assertTrue(slave.inConnSubPage().isValidPage(), "Page is'nt valid ");

        Assert.assertTrue(slave.onSettingsPage().clickUsers().isStateSwitched());
        Assert.assertTrue(slave.inUsersSubPage().isValidPage(), "Users page is'nt valid ");

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
