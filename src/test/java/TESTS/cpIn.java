package TESTS;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.NConfig;
import utils.Props;

public class cpIn extends AbstractTest {

    @Before
    public void setUp() {
        slave.pasteCleanIni(4);
        slave.runNumberok();
        Assert.assertTrue(slave.isAppear());
        slave.maximize();
//        slave.watchNumberok();
        slave.clickSettings();
        setUpConnection();
    }

    @After
    public void tearDown() throws Exception {
//        closeProcceses();
        slave.closeNumberok();
    }


    public void assertCP() {

        Assert.assertEquals("channel Saved", Props.get("test.video"), NConfig.get("Channel1File"));
        Assert.assertEquals("lprZone enabled ", "1", NConfig.get("LprZone11enabled"));

        Assert.assertEquals("checkpoint entrance  channel ", "0", NConfig.get("Checkpoint1EntranceChannel"));
        Assert.assertEquals("Checkpoint EntranceZone", "0", NConfig.get("Checkpoint1EntranceZone"));
        Assert.assertEquals("Checkpoint EntranceWhom", "1", NConfig.get("Checkpoint1EntranceWhom"));
        Assert.assertEquals("Checkpoint Exit Channel ", "0", NConfig.get("Checkpoint1ExitChannel"));
        Assert.assertEquals("Checkpoint Exit Zone ", "1", NConfig.get("Checkpoint1ExitZone"));
        Assert.assertEquals("Checkpoint Exit Whom ", "1", NConfig.get("Checkpoint1ExitWhom"));
        Assert.assertEquals("Checkpoint Determinate PassageByZones", "1", NConfig.get("Checkpoint1DeterminatePassageByRecognitionZones"));
    }

    @Test
    public void scenario111() {
        createCP(0, new int[]{1, 0, 1}, new int[]{1, 0, 1}, 2);
    }

    private void setUpConnection() {
        slave.inGeneralSubPage().setOperationMode(2).clickApply();
        slave.onSettingsPage().clickConnection();
        slave.inConnSubPage()
                .enableZone()
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
                .clickConnect()
                .clickApply();
        slave.onSettingsPage().clickGeneral();

    }

    private void createCP(int checkpoint, int[] entry, int[] exit, int determination) {
        slave.onSettingsPage().clickCheckpoint();
        if (checkpoint > 0) {
            slave.inCheckpointSubPage().swichCP(checkpoint);
        }
        slave.inCheckpointSubPage()
                .cpEntry(entry[0], entry[1], entry[2])
                .cpExit(exit[0], exit[1], exit[2]);
        slave.inCheckpointSubPage().setPassageDetermination(determination);
        slave.inCheckpointSubPage().clickApply();
        slave.onSettingsPage().clickGeneral();
    }
}
