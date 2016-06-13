package tests.someTests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.AbstractTest;
import util.NConfig;
import util.Props;

import java.util.concurrent.TimeUnit;

/**
 * class for buizness casses testing
 *
 */

public class BizCases extends AbstractTest {

    @Override
    @BeforeMethod
    public void setUp() throws Exception {
        slave.rmDB();
        slave.pasteCleanIni(2);
        NConfig.set("Vcu.File.Cycled", "0");
        slave.runNumberok();
        Assert.assertTrue(slave.waitAppearing(), "Numberok is'nt appear");
        slave.maximize();
    }

    @Override
    @AfterMethod
    public void tearDown() throws Exception {
        System.out.println("== mem usage is " + slave.memUsage() + " Kb");
        Thread.sleep(300);
        slave.closeNumberok();
    }

    /**
     * Appetit test logic
     *
     * 2 checkpoints WITHOUT determination of passage
     *
     *
     * 1 reaction on pop up window for cars that duration of stay is duration <3 min
     *
     */

    @Test
    public void testApetit() throws Exception {
        slave.clickSettings();
        slave.inGeneralSubPage()
                .setComparisonMode(2)
                .setOperationMode(2)
                .clickApply();
        slave.onSettingsPage().clickConnection();
        //configuring video but no to connect
        slave.inConnSubPage()
                .chooseConn(3)
                .typeConn(3, Props.get("1up.video"))
                .enableZone()
                .setSensitivity(50)
                .setDirAngle(210)
                .switchVidSource(2) // go to channel #2
                .chooseConn(3)
                .typeConn(3, Props.get("1up.video"))
                .enableZone()
                .setSensitivity(50)
                .setDirAngle(70)
                .switchVidSource(1)//return back in gui
                .clickApply();
        // configuring Checkpoint
        slave.onSettingsPage().clickCheckpoint();
        slave.inCheckpointSubPage()
                .cpEntry(1, 0, 3)
                .swichCP(2)
                .cpExit(2, 0, 2)
                .clickApply();

        slave.onSettingsPage().clickGeneral(); // return back in gui

        slave.clickCarDB();
        slave.onCarDbPage().clickReactions();
        System.out.println("== Create reaction");
        slave.inReactionPage()
                .addReaction()
                .chooseEvents(2)// add methods on checkpoint event
                .byChekpoint()
                .byChekpoint(2)
                .selectTypeOfCar()
                .byDuration()
                .setDuration(0, 3)
                .showWindow(false)
                .clickOK();

        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        slave.inConnSubPage().clickConnect();// enable video for in event

        slave.clickResults(); // just for go out from settings page
        TimeUnit.MINUTES.sleep(2);

        slave.clickSettings(); // enable video for out event
        slave
                .inConnSubPage()
                .switchVidSource(2)
                .clickConnect();
        Assert.assertTrue(slave.expectPopUP(10), "pop up is showing");
    }

    @Test
    public void testNegativApetit() throws Exception {
        slave.clickSettings();
        slave.inGeneralSubPage()
                .setComparisonMode(2)
                .setOperationMode(2)
                .clickApply();
        slave.onSettingsPage().clickConnection();
        //configuring video but no to connect
        slave.inConnSubPage()
                .chooseConn(3)
                .typeConn(3, Props.get("1up.video"))
                .enableZone()
                .setSensitivity(50)
                .setDirAngle(210)
                .switchVidSource(2) // go to channel #2
                .chooseConn(3)
                .typeConn(3, Props.get("1up.video"))
                .enableZone()
                .setSensitivity(50)
                .setDirAngle(70)
                .switchVidSource(1)//return back in gui
                .clickApply();
        // configuring Checkpoint
        slave.onSettingsPage().clickCheckpoint();
        slave.inCheckpointSubPage()
                .cpEntry(1, 0, 3)
                .swichCP(2)
                .cpExit(2, 0, 2)
                .clickApply();

        slave.onSettingsPage().clickGeneral(); // return back in gui

        slave.clickCarDB();
        slave.onCarDbPage().clickReactions();
        System.out.println("== Create reaction");
        slave.inReactionPage()
                .addReaction()
                .chooseEvents(2)// add methods on checkpoint event
                .byChekpoint()
                .byChekpoint(2)
                .selectTypeOfCar()
                .byDuration()
                .setDuration(0, 3)
                .showWindow(false)
                .clickOK();

        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        slave.inConnSubPage().clickConnect();// enable video for in event

        slave.clickResults(); // just for go out from settings page
        TimeUnit.MINUTES.sleep(5);

        slave.clickSettings(); // enable video for out event
        slave
                .inConnSubPage()
                .switchVidSource(2)
                .clickConnect();
        Assert.assertFalse(slave.expectPopUP(10), "pop up is showing");
    }

}
