package Ui_Test;

import org.junit.Assert;
import org.junit.Test;
import org.sikuli.script.Screen;



/**
 * Created by DespicableMe on 26.02.2016.
 * Описание:
 */
public class runTest extends BaseTest {
        MenuController mc = new MenuController();




    @Test
    public void start() throws InterruptedException {
        Screen screen = new Screen();
        MainPage mp = new MainPage(screen);
        SettingsPage sp;
        Assert.assertTrue("Numberok do not appeared",mp.isAppear());
        screen = mp.maximize();
        mc.getButtonsLocation(screen);
        mc.clickSettings();
        sp = new SettingsPage(screen);

        sp.switchMode(3)
                .switchMode(1)
                .warningClick(1)
                .switchMode(3);

        sp.clickIntegration()
                .clickGeneral()
                .clickUsers()
                .clickParking()
                .clickConnection()
                .clickCheckpoint();

    }

}
