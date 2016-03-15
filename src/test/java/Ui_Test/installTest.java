package Ui_Test;


import InstallTest.InstallNumberok;
import org.junit.Assert;
import org.junit.Test;


import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import utils.Props;


/**
 * Created by DespicableMe on 17.02.2016.
 * Описание:
 */
public class installTest {
    final Screen sDriver = new Screen();
    String version = "1895";
    @Test
    public void install() {
        System.out.println("Installing started");
        InstallNumberok install = new InstallNumberok(sDriver);

        install.runInstall(version);
        
        try {
            install
                    .langScreen()
                    .licenzeScreen(Props.getProperty("9Chan.All_SMB"))
                    .hiScreen()
                    .infoScreen()
                    .workCatScreen()
                    .dbScreen()
                    .shootsScreen()
                    .installCatScreen()
                    .shortCutScreen()
                    .readyInstallScreen()
                    .waitForIt()
                    .drivers();
        } catch (FindFailed findFailed) {
            Assert.assertTrue(findFailed.getMessage(),false);
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("installing finished");
    }


}
