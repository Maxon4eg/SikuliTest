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
    private String version = "1849";

    public InstallNumberok(Screen sDriver) {
        this.sDriver = sDriver;

    }

    public void runInstall() {
        runInstall(Props.get("installer.path") + version + Props.get("installer"));
    }

    public InstallNumberok langScreen() throws FindFailed {
        window = new Pattern(Props.getPathFor("lang.png"));
        Pattern ok = new Pattern(Props.getPathFor("ok.png"));
        region = sDriver.wait(window);// сохраняет границы окна
        region.click(ok);
        return this;
    }

    public InstallNumberok licenzeScreen(String licenze) {
        Pattern licenzeWindow = new Pattern(Props.getPathFor("licenze.PNG"));
        Pattern sendLic = new Pattern(Props.getPathFor("licSendKeys.PNG"));
        Pattern ok = new Pattern(Props.getPathFor("ok.png")).similar((float) 0.5);

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
        window = new Pattern(Props.getPathFor("Hi.PNG"));
        NextButton = new Pattern(Props.getPathFor("nextButton.png"));
        region = sDriver.wait(window,5000);
        region.click(NextButton);
        return this;
    }

    public InstallNumberok infoScreen() throws FindFailed {
        window = new Pattern(Props.getPathFor("info.png"));
        region = sDriver.wait(window);
        region.click(NextButton);
        return this;
    }


    public InstallNumberok workCatScreen() throws FindFailed {
        window = new Pattern(Props.getPathFor("WorkCat.PNG"));
        region = sDriver.wait(window);
        region.click(NextButton);
        return this;
    }


    public InstallNumberok dbScreen() throws FindFailed {
        window = new Pattern(Props.getPathFor("DB.png"));
        region = sDriver.wait(window);
        region.click(NextButton);
        return this;
    }

    public InstallNumberok shootsScreen() throws FindFailed {
        window = new Pattern(Props.getPathFor("ShootsCat.PNG"));
        region = sDriver.wait(window);
        region.click(NextButton);
        return this;
    }


    public InstallNumberok installCatScreen() throws FindFailed {
        window = new Pattern(Props.getPathFor("InstallCat.PNG"));
        region = sDriver.wait(window);
        region.click(NextButton);
        return this;
    }


    public InstallNumberok shortCutScreen() throws FindFailed {
        window = new Pattern(Props.getPathFor("ShortCut.PNG"));
        region = sDriver.wait(window);
        region.click(NextButton);
        return this;
    }

    public InstallNumberok readyInstallScreen() throws FindFailed {
        window = new Pattern(Props.getPathFor("redyInst.PNG"));
        Pattern installButton = new Pattern(Props.getPathFor("installBut.PNG"));
        region = sDriver.wait(window);
        region.click(installButton);

        return this;
    }
    public InstallNumberok waitForIt () throws FindFailed, InterruptedException {
//        window = new Pattern (Proprs.getPathForRun("instProc.PNG"));
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
