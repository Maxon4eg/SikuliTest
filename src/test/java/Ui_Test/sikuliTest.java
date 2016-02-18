package Ui_Test;


import InstallTest.installScreens;
import org.junit.Test;


import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import utils.DataProperties;


/**
 * Created by DespicableMe on 17.02.2016.
 * Описание:
 */
public class sikuliTest {
    final Screen sDriver = new Screen();

    @Test
    public void install() {
        System.out.println("Installing started");
        installScreens install = new installScreens(sDriver);

        install.runInstall();
        
        try {
            install
                    .langScreen()
                    .licenzeScreen(DataProperties.get("license.All_SMB"))
                    .hiScreen()
                    .infoScreen()
                    .workCatScreen()
                    .dbScreen()
                    .shootsScreen()
                    .installCatScreen()
                    .shortCutScreen()
                    .readyInstallScreen();

        } catch (FindFailed findFailed) {
//            findFailed.printStackTrace();
            System.out.println(findFailed);
        }
        System.out.println("installing finished");
    }
}
