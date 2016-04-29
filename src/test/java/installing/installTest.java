package installing;


import InstallTest.InstallNumberok;


import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.Props;


/**
 * Created by DespicableMe on 17.02.2016.
 * Описание:
 */
public class installTest {
    final Screen sDriver = new Screen();
    String version = "1895";
    String key = "4Chan.USA";
//    @Test
    public void install() {
        System.out.println("Installing started");
        InstallNumberok install = new InstallNumberok(sDriver);
        InstallNumberok.runInstall(version,true);

        try {
            install
                    .langScreen()
                    .licenzeScreen(Props.get(key))
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
            Assert.assertTrue(false, findFailed.getMessage());
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("installing finished");
    }


}
