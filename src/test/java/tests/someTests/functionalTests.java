package tests.someTests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.AbstractTest;
import util.Props;


public class functionalTests extends AbstractTest {

    private int channels = 2;

    @BeforeMethod
    public void setUp() throws Exception {
        slave. pasteCleanIni(channels);
        slave.runNumberok();
        Assert.assertTrue(slave.waitAppearing());
        slave.maximize();
//        slave.watchNumberok(); in future
    }
    @AfterMethod
    public void tearDown() throws Exception {
        slave.closeNumberok();
    }

    @Test
    public void creatingCP()throws Exception {
        slave.onViewPage().howManyChannels();
        slave.clickSettings();
        slave.inGeneralSubPage().setOperationMode(2).clickApply();
        slave.onSettingsPage().clickConnection();
        slave.inConnSubPage()
                .chooseConn(3)
                .typeConn(3, Props.get("pl.video"))
                .enableZone()
                .setDirAngle(100)
                .moveTopRight(0, 50)
                .moveTopLeft(0, 50)
                .moveBottomRight(0, -50)
                .moveBottomLeft(0, -50)
                .clickConnect();
        Assert.assertTrue(slave.inConnSubPage().isVideoAppear());

        slave.inConnSubPage().switchVidSource(2)
                .chooseConn(3)
                .typeConn(3, Props.get("pl.video"))
                .enableZone()
                .setDirAngle(100)
                .moveTopRight(0, 50)
                .moveTopLeft(0, 50)
                .moveBottomRight(0, -50)
                .moveBottomLeft(0, -50)
                .clickConnect();
        Assert.assertTrue(slave.inConnSubPage().isVideoAppear());

        slave.onSettingsPage().clickCheckpoint();
        slave.inCheckpointSubPage().cpEntry(1, 1, 1).cpExit(2, 1, 2).setPassageDetermination(3);
        slave.clickView();

        Assert.assertTrue(slave.onViewPage().checkChan(1));
    }

    @Test
    public void enablingZone()throws Exception {
        slave.clickView();
        Assert.assertEquals(slave.onViewPage().howManyChannels(),channels,"channels is wrong");
        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        slave.inConnSubPage().switchVidSource(1).enableZone();
    }

    @Test
    public void resultsCheck () throws Exception {
        slave.onViewPage().howManyChannels();
        slave.clickSettings();
        slave.onSettingsPage().clickConnection();

        slave.inConnSubPage()
                .chooseConn(3)
                .typeConn(3, Props.get("test.video"))
                .enableZone()
                .setDirAngle(90)
                .moveTopRight(0, 50)
                .moveTopLeft(0, 50)
                .moveBottomRight(0, -50)
                .moveBottomLeft(0, -50)
                .clickConnect();
        Assert.assertTrue(slave.inConnSubPage().isVideoAppear());
        slave.clickResults();
        slave.onResultsPage().clickRecognitionResults();
        Assert.assertTrue(slave.inRecognitionResults().isChanges(50));
    }


    /**
     * Entry logic test
     */

    @Test
    public void cpLogicEntry()throws Exception {
        slave.clickSettings();
        slave.inGeneralSubPage().setOperationMode(2).clickApply();
        enablingZone();
        slave.inConnSubPage()
                .setDirAngle(200)
                .setSensitivity(50)
                .moveTopRight(0, 300)
                .moveTopLeft(0, 300);
        slave.inConnSubPage()
                .switchZone(2)
                .enableZone()
                .setDirAngle(200)
                .setSensitivity(50);
        slave.inConnSubPage()
                .chooseConn(3)
                .typeConn(3, Props.get("test.video"))
                .clickConnect();
        Assert.assertTrue(slave.inConnSubPage().isVideoAppear());

        slave.onSettingsPage().clickCheckpoint();
        slave.inCheckpointSubPage().cpEntry(1, 0, 1);
        slave.inCheckpointSubPage().cpExit(1, 0, 1);
        slave.inCheckpointSubPage().setPassageDetermination(2);
        slave.inCheckpointSubPage().clickApply();

        slave.clickResults();
        slave. onResultsPage()
                .clickRecognitionResults();
        Assert.assertTrue(slave.inRecognitionResults().entryLogic(), "Direction appearing is invalid");
    }



}
