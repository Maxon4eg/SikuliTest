package tests.RecognitionModeTesting;

import org.sikuli.script.Key;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import tests.AbstractTest;
import util.Props;
import util.Slave;

/**
 * Testing for appearing result for diff zones in diff channels
 * about 2 min testing for 1 channel
 */
// TODO: 25.04.2016 check the workability after refactor

public class ChannelsTesting extends AbstractTest {



    @Override
    public void setUp() throws Exception {
    }

    @Override
    @AfterMethod
    public void tearDown() throws Exception {
        slave.closeNumberok();
    }

    @Test
    public void testAppearingTests2Channels() throws Exception {
        slave.pasteCleanIni(2);//2 channels
        slave.runNumberok();
        Assert.assertTrue(slave.waitAppearing(), "Numberok is'nt appeared");
        slave.maximize();

        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        checkZonesAnsChannels(slave, 2);

        System.out.println(" ========= Memory usage after test in " + 2 + " Channels : " + slave.memUsage() + " KB");

    }

    @Test
    public void testAppearingTest4Channels() throws Exception {
        slave.pasteCleanIni(4);//4 channels
        slave.runNumberok();
        Assert.assertTrue(slave.waitAppearing(), "Numberok is'nt appeared");
        slave.maximize();

        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        checkZonesAnsChannels(slave, 4);
        System.out.println(" ========= Memory usage after test in " + 4 + " Channels : " + slave.memUsage() + " KB");


    }

    @Test
    public void testAppearingTest9Channels() throws Exception {
        slave.pasteCleanIni(9);//9 channels
        slave.runNumberok();
        Assert.assertTrue(slave.waitAppearing(), "Numberok is'nt appeared");
        slave.maximize();

        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        checkZonesAnsChannels(slave, 9);

        System.out.println(" ========= Memory usage after test in " + 9 + " Channels : " + slave.memUsage() + " KB");



    }

    @Test
    public void testAppearingTest1Channel() throws Exception {

        slave.pasteCleanIni(1);//4 channels
        slave.runNumberok();
        Assert.assertTrue(slave.waitAppearing(), "Numberok is'nt appeared");
        slave.maximize();

        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        checkZonesAnsChannels(slave, 1);

        System.out.println(" ========= Memory usage after test in " + 1 + " Channels : " + slave.memUsage() + " KB");

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
            System.out.println();
            System.out.println("== Now im checking channel : "+i );

            for (int j = 1; j < 5; j++) {
                slave
                        .inConnSubPage()
                        .switchZone(j)
                        .enableZone();
                System.out.println("== enable zone : "+j);

                configureZone(slave, j);
                appeared = slave.inConnSubPage().clickConnect().isVideoAppear();

                while (!appeared) { // check for appearing video
                    Thread.sleep(800);
                    slave.inConnSubPage().clickConnect();
                    appeared = slave.inConnSubPage().isVideoAppear();
                }

                slave.inConnSubPage().clickApply();
                Thread.sleep(500);// gui callback may slowdown
                slave.clickResults();
                Assert.assertTrue(slave.inRecognitionResults().isChanges(), "the result is'nt appear  on zone =" + j);
                System.out.println("Memory usage  : " + slave.memUsage() + " KB");
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

    private void configureZone(Slave slave, int zone) { // in in future need to remake this code

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
