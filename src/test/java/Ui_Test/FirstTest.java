package Ui_Test;

import Ui_Test.SettingsSubPages.ConnSubPage;
import Ui_Test.SettingsSubPages.GeneralSubPage;
import org.junit.Assert;
import org.junit.Test;
import org.sikuli.script.Screen;
import utils.Props;


/**
 * Created by DespicableMe on 26.02.2016.
 * Описание:
 */
public class FirstTest extends BaseTest {

    @Test

    public void newTest() {
        GeneralSubPage gsp = new GeneralSubPage(new Screen());
        ConnSubPage csp;
        ViewPage vp;
        Assert.assertTrue("Can't find menu widget", gsp.isAppear());
        gsp.maximize();
        gsp.findChannels();
        gsp.clickSettings();
        csp = (ConnSubPage) gsp.clickConnection();
        csp
                .chooseConn(3)
                .typeConn(3, Props.getProperty("video.path"))
                .clickConnect();
        Assert.assertTrue("Video isn't started ", csp.isVideoAppear());
        csp.clickApply();

    }


    //    @Test
    public void start() {
        GeneralSubPage gsp = new GeneralSubPage(new Screen());
        Assert.assertTrue(gsp.isAppear());
        gsp.maximize();
        gsp.clickSettings();
        ConnSubPage csp = (ConnSubPage) gsp.clickConnection();
        csp.chooseConn(3).typeConn(3, Props.getProperty("video")).clickConnect();
        Assert.assertTrue(csp.isVideoAppear());
    }

}
