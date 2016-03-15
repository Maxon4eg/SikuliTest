package Ui_Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GuiTests extends NumberokWorker {

    private Process numberok;

    @Before
    public void setUP() {
        pasteCleanIni(9);
        numberok = runNumberok();
        Assert.assertTrue(isAppear());
        maximize();
    }

    @Test
    public void TestA() {
        Assert.assertEquals("Expected 9 channels", 9, onViewPage().howManyChannels());
    }

    @Test
    public void TestB() {
        Assert.assertTrue(clickResults().isStateSwitched());
        Assert.assertTrue(clickCarDB().isStateSwitched());
        Assert.assertTrue(clickReports().isStateSwitched());
        Assert.assertTrue(clickSettings().isStateSwitched());
        Assert.assertTrue(clickView().isStateSwitched());
    }

    @Test
    public void TestC() {
        clickSettings();
        Assert.assertTrue(onSettingsPage().clickIntegration().isStateSwitched());
    }

    @After
    public void tearDown() {
        if (numberok != null) {
            numberok.destroy();
        }
    }
}
