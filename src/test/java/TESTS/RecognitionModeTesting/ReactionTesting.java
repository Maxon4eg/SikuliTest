package tests.RecognitionModeTesting;

import tests.AbstractTest;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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
        enableVideo();
        Assert.assertTrue(slave.inRecognitionResults().isReactionWorks(40), "Reaction is'n works ");
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
        enableVideo();
        Assert.assertTrue(slave.inRecognitionResults().isReactionWorks(40), "Reaction is'nt works ");

        slave.clickCarDB();
        slave.inReactionPage()
                .clickOnTheFirstReaction()
                .clickRemove();
        slave.clickResults();
        Assert.assertTrue(slave.inRecognitionResults().isReactionNOTworks(30), "Removed reaction still works or its not removed ");
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
        enableVideo();
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
        enableVideo();
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
                .typeConn(3, Props.get("test.video"))
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

    private void enableVideo() throws FindFailed {
        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        slave.inConnSubPage()
                .chooseConn(3)
                .typeConn(3, Props.get("test.video"))
                .enableZone()
                .clickConnect()
                .clickApply();
        slave.clickResults();
    }
}
