package Ui_Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.Props;


public class functionalTests extends NumberokWorker {

    Process numberokProcces;

    @Before
    public void startTest() {
        pasteCleanIni(9);
        numberokProcces = runNumberok();
        isAppear();
        maximize();
    }

    @Test
    public void creatingCP() {
        onViewPage().howManyChannels();
        clickSettings();
        inGeneralSubPage().setOperationMode(2).clickApply();
        onSettingsPage().clickConnection();
        inConnSubPage()
                .chooseConn(3)
                .typeConn(3, Props.getProperty("video.path"))
                .enableZone()
                .setDirAngle(100)
                .moveTopRight(0, 50)
                .moveTopLeft(0, 50)
                .moveBottomRight(0, -50)
                .moveBottomLeft(0, -50)
                .clickConnect();
        Assert.assertTrue(inConnSubPage().isVideoAppear());

        inConnSubPage().switchVidSource(2)
                .chooseConn(3)
                .typeConn(3, Props.getProperty("video.path"))
                .enableZone()
                .setDirAngle(100)
                .moveTopRight(0, 50)
                .moveTopLeft(0, 50)
                .moveBottomRight(0, -50)
                .moveBottomLeft(0, -50)
                .clickConnect();
        Assert.assertTrue(inConnSubPage().isVideoAppear());

        onSettingsPage().clickCheckpoint();
        inCheckpointSubPage().cpEntry(1, 1, 1).cpExit(2, 1, 2).passageDetermination(2);
        clickView();

        Assert.assertTrue(onViewPage().checkChan(1));
        Assert.assertTrue(onViewPage().checkChan(2));

    }

    @Test
    public void enablingZone() {
        onViewPage().howManyChannels();
        clickResults();
        clickSettings();
        onSettingsPage().clickConnection();
        inConnSubPage().switchVidSource(2).enableZone();
        clickView();
    }

    @After
    public void tearDown() {
        numberokProcces.destroy();
    }
}
