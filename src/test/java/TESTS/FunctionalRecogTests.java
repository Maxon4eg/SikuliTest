package TESTS;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.sikuli.script.Key;
import utils.Props;
import utils.Slave;


public class FunctionalRecogTests extends AbstractTest {


    @Override
    public void setUp() throws Exception {
    }

    @Override
    @After
    public void tearDown() throws Exception {
        slave.closeNumberok();
    }

    @Test
    public void testAppearingTests2Channels() throws Exception {
        slave.pasteCleanIni(2);//2 channels
        slave.runNumberok();
        Assert.assertTrue("Numberok is'nt appeared", slave.isAppear());
        slave.maximize();

        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        checkZonesAnsChannels(slave, 2);
    }

    @Test
    public void testAppearingTest4Channels() throws Exception {
        slave.pasteCleanIni(4);//4 channels
        slave.runNumberok();
        Assert.assertTrue("Numberok is'nt appeared", slave.isAppear());
        slave.maximize();

        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        checkZonesAnsChannels(slave, 4);
    }

    @Test
    public void testAppearingTest9Channels() throws Exception {
        slave.pasteCleanIni(9);//9 channels
        slave.runNumberok();
        Assert.assertTrue("Numberok is'nt appeared", slave.isAppear());
        slave.maximize();

        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        checkZonesAnsChannels(slave, 9);

    }

    @Test
    public void testAppearingTest1Channel() throws Exception {
        // TODO: 22.04.2016 Recive licenze key for 1 channel and do your work

    }

    private void checkZonesAnsChannels(Slave slave, int channels) throws Exception {
        channels += 1;
        boolean appeared;

        for (int i = 1; i < channels; i++) {
            slave.inConnSubPage().switchVidSource(i);
            slave.inConnSubPage()
                    .chooseConn(3)
                    .typeConn(3, Props.get("pl.video"));
            //cycle of configuring zones
            for (int j = 1; j < 5; j++) {
                slave.inConnSubPage().switchZone(j).enableZone();
                configureZone(slave, j);
                appeared = slave.inConnSubPage().clickConnect().isVideoAppear();
                while (!appeared) { // check for appearing video
                    Thread.sleep(800);
                    slave.inConnSubPage().clickConnect();
                    appeared = slave.inConnSubPage().isVideoAppear();
                }
                slave.inConnSubPage().clickApply();
                slave.clickResults();
                Assert.assertTrue("the result is'nt appear  on zone =" + j, slave.inRecognitionResults().isChanges());
                slave.clickSettings();
                slave.inConnSubPage()
                        .typeConn(3, Key.BACKSPACE)
                        .clickConnect()
                        .enableZone()
                        .typeConn(3, "i")
                        .clickApply();
            }
        }

    }

    private void configureZone(Slave slave, int zone) {

        switch (zone) {
            case 1:
                slave.inConnSubPage()
                        .moveTopRight(0, 50)
                        .moveTopLeft(0, 50)
                        .moveBottomRight(0, -50)
                        .moveBottomLeft(0, -50);
                break;
            default:
                break;
        }
    }
}
