package tests.RecognitionModeTesting;

import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.AbstractTest;
import util.Props;

import java.util.concurrent.TimeUnit;


public class ReactionTesting extends AbstractTest {

    @Override
    @BeforeMethod
    public void setUp() throws Exception {
        slave.pasteCleanIni(1);
        slave.runNumberok();
        Assert.assertTrue(slave.waitAppearing(), "Numberok is'nt appear");
        slave.maximize();
    }

    @Override
    @AfterMethod
    public void tearDown() throws Exception {
        System.out.println("== mem usage is " + slave.memUsage() + " Kb");
        slave.closeNumberok();
        Thread.sleep(500);
        slave.rmDB();

    }

    @Test
    public void testGreenRowReaction() throws Exception {
        slave.clickCarDB();
        slave.onCarDbPage().clickReactions();
        slave.inReactionPage()
                .addReaction()
                .chooseEvents(1)
                .byNumber()
                .setVisualReaction()
                .clickOK();
        enableVideo(Props.get("test.video"));
        Assert.assertTrue(slave.inRecognitionResults().isReactionWorks(40), "Reaction is'n works ");
    }


    @Test
    public void testPopUpWindow() throws Exception {
        slave.clickCarDB();
        slave.onCarDbPage().clickReactions();
        slave.inReactionPage()
                .addReaction()
                .chooseEvents(1)
                .byNumber()
                .showWindow(true)
                .clickOK();
        enableVideo(Props.get("2numbs.video"));
        slave.clickResults();
        Assert.assertTrue(slave.expectPopUP(40), "Pop Up is not appeared");
        Assert.assertTrue(slave.isScreenShotPresent(), "Screenshot is missing ");
        TimeUnit.SECONDS.sleep(15);
        Assert.assertTrue(slave.closePopUp(), "Probably reaction window is unexpectedly closed ");

    }

    @Test
    public void testPopUpWindow15Sec() throws FindFailed, InterruptedException {//// TODO: 28.04.2016 need to make video
        slave.clickCarDB();
        slave.onCarDbPage().clickReactions();
        slave.inReactionPage()
                .addReaction()
                .chooseEvents(1)
                .byNumber()
                .showWindow(false)
                .clickOK();
        enableVideo(Props.get("2numbs.video"));
        Assert.assertTrue(slave.expectPopUP(40), "Pop up is not appeared ");
        Assert.assertTrue(slave.isScreenShotPresent(), "screenshot is missing ");
        TimeUnit.SECONDS.sleep(16);
        Assert.assertFalse(slave.closePopUp(), "Probably reaction window is NOT close");
    }

    @Test
    public void testPopUp_ByZones() throws Exception {
        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        slave.inConnSubPage()
                .chooseConn(3)
                .typeConn(3, Props.get("2numbs.video"))
                .enableZone();

        slave.clickCarDB();
        slave.onCarDbPage().clickReactions();
        slave.inReactionPage()
                .addReaction()
                .chooseEvents(1)
                .byNumber()
                .showWindow(true)
                .clickOK();

        slave.clickSettings();
        slave.inConnSubPage()
                .clickConnect()
                .clickApply();

        slave.clickResults();
        Assert.assertTrue(slave.expectPopUP(40), "Pop up is not appeared ");
        Assert.assertTrue(slave.isScreenShotPresent(), "screenshot is missing ");
        TimeUnit.SECONDS.sleep(5);
        slave.closePopUp();

    }


    @Test
    public void testRemovingReaction() throws Exception {

        slave.clickCarDB();
        slave.onCarDbPage().clickReactions();
        slave.inReactionPage()
                .addReaction()
                .chooseEvents(1)
                .byNumber()
                .setVisualReaction()
                .clickOK();
        enableVideo(Props.get("test.video"));
        Assert.assertTrue(slave.inRecognitionResults().isReactionWorks(40), "Reaction isn't works ");

        slave.clickCarDB();
        slave.inReactionPage()
                .clickOnTheFirstReaction()
                .clickRemove();
        slave.clickResults();
        Assert.assertTrue(slave.inRecognitionResults().isReactionNOTworks(30), "Removed reaction still works or its not removed ");
    }

    private void enableVideo(String videoPath) throws FindFailed {
        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        slave.inConnSubPage()
                .chooseConn(3)
                .typeConn(3, videoPath)
                .enableZone()
                .clickConnect()
                .clickApply();
        slave.clickResults();
    }
}
