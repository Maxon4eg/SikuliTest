package InstallTest;

import org.sikuli.script.*;
import utils.Props;

import java.util.concurrent.TimeUnit;


/**
 * Created by DespicableMe on 17.02.2016.
 * Описание:
 */
public class InstallNumberok extends BaseInstall {

    private final Screen sDriver;
    private Region region;
    private Pattern window;
    private Pattern NextButton;

    public InstallNumberok(Screen sDriver) {
        this.sDriver = sDriver;

    }

    public static void runInstall(String version) {

        BaseInstall.runInstall(Props.getProperty("installer.path") + version + Props.getProperty("installer"));
    }

    public InstallNumberok langScreen() throws FindFailed {
        window = new Pattern(Props.pathFor("lang.png"));
        Pattern dropDown = new Pattern(Props.pathFor("langDropdown.png"));
        Pattern ok = new Pattern(Props.pathFor("ok.png"));
        region = sDriver.wait(window);// сохраняет границы окна
        region.click(dropDown);
        region.type("eng");
        region.type(Key.ENTER);
        region.click(ok);
        return this;
    }

    public InstallNumberok licenzeScreen(String licenze) {
        Pattern licenzeWindow = new Pattern(Props.pathFor("licenze.PNG"));
        Pattern sendLic = new Pattern(Props.pathFor("licSendKeys.PNG"));
        Pattern ok = new Pattern(Props.pathFor("ok.png")).similar((float) 0.5);

        try {
//            region.wait(licenzeWindow, 10); // ожидаем появления окна
            region = sDriver.wait(licenzeWindow, 10);
            region.click(sendLic);
            region.type(sendLic, licenze);
            region.click(ok);
            return this;
        } catch (FindFailed findFailed) {
            System.out.println("!!!! License do not appear !!!!");// если окно не появилось
            return this;
        }
    }

    public InstallNumberok hiScreen() throws FindFailed {
        window = new Pattern(Props.pathFor("Hi.PNG"));
        NextButton = new Pattern(Props.pathFor("nextButton.png"));
        region = sDriver.wait(window,5000);
        region.click(NextButton);
        return this;
    }

    public InstallNumberok infoScreen() throws FindFailed {
        window = new Pattern(Props.pathFor("info.png"));
        region = sDriver.wait(window);
        region.click(NextButton);
        return this;
    }


    public InstallNumberok workCatScreen() throws FindFailed {
        window = new Pattern(Props.pathFor("WorkCat.PNG"));
        region = sDriver.wait(window);
        region.click(NextButton);
        return this;
    }


    public InstallNumberok dbScreen() throws FindFailed {
        window = new Pattern(Props.pathFor("DB.png"));
        region = sDriver.wait(window);
        region.click(NextButton);
        return this;
    }

    public InstallNumberok shootsScreen() throws FindFailed {
        window = new Pattern(Props.pathFor("ShootsCat.PNG"));
        region = sDriver.wait(window);
        region.click(NextButton);
        return this;
    }


    public InstallNumberok installCatScreen() throws FindFailed {
        window = new Pattern(Props.pathFor("InstallCat.PNG"));
        region = sDriver.wait(window);
        region.click(NextButton);
        return this;
    }


    public InstallNumberok shortCutScreen() throws FindFailed {
        window = new Pattern(Props.pathFor("ShortCut.PNG"));
        region = sDriver.wait(window);
        region.click(NextButton);
        return this;
    }

    public InstallNumberok readyInstallScreen() throws FindFailed {
        window = new Pattern(Props.pathFor("redyInst.PNG"));
        Pattern installButton = new Pattern(Props.pathFor("installBut.PNG"));
        region = sDriver.wait(window);
        region.click(installButton);

        return this;
    }
    public InstallNumberok waitForIt () throws FindFailed, InterruptedException {
//        window = new Pattern (Proprs.pathForRun("instProc.PNG"));
//        sDriver.waitVanish(window); //не знаю но почему то не работает будет слип
        TimeUnit.SECONDS.sleep(13);
        return this;
    }

    public InstallNumberok drivers () throws FindFailed {
        sDriver.mouseMove(11,35);
        InstallDrivers drivers = new InstallDrivers(sDriver);
        drivers.ftdi().device();

        return this;
    }




}
