package InstallTest;

import org.sikuli.script.*;
import utils.DataProperties;


/**
 * Created by DespicableMe on 17.02.2016.
 * Описание:
 */
public class installScreens extends BaseInstall {

    private final Screen sDriver;
    private Region region;
    private Pattern window;
    private Pattern NextButton;

    public installScreens(Screen sDriver) {
        this.sDriver = sDriver;
    }

    public void runInstall() {
        runInstall(DataProperties.get("installer.path"));
    }

    public installScreens langScreen() throws FindFailed {
        window = new Pattern(DataProperties.getPathFor("lang.png"));
        Pattern ok = new Pattern(DataProperties.getPathFor("ok.png"));

        region = sDriver.wait(window);// сохраняет границы окна
        region.click(ok);
        return this;
    }

    public installScreens licenzeScreen(String licenze) throws FindFailed {
        Pattern licenzeWindow = new Pattern(DataProperties.getPathFor("licenze.PNG"));
        Pattern sendLic = new Pattern(DataProperties.getPathFor("licSendKeys.PNG"));
        Pattern ok = new Pattern(DataProperties.getPathFor("ok.png")).similar((float) 0.5);
        region = sDriver;
        region.wait(licenzeWindow,10);

        if(region!=null){
            region.click(sendLic);
            region.type(sendLic,licenze);
            region.click(ok);
            return this;
        }
        else System.out.println("License do not appear");
        return this;
    }

    public installScreens hiScreen() throws FindFailed {
        window = new Pattern(DataProperties.getPathFor("hi.png"));
        NextButton = new Pattern(DataProperties.getPathFor("nextButton.png"));

        region = sDriver.wait(window);
        region.click(NextButton);
        return this;
    }

    public installScreens infoScreen() throws FindFailed {
        window = new Pattern(DataProperties.getPathFor("info.png"));

        region = sDriver.wait(window);
        region.click(NextButton);

        return this;
    }


    public installScreens workCatScreen() throws FindFailed {
        window = new Pattern(DataProperties.getPathFor("WorkCat.PNG"));

        region = sDriver.wait(window);
        region.click(NextButton);
        return this;
    }


    public installScreens dbScreen() throws FindFailed {
        window = new Pattern(DataProperties.getPathFor("DB.png"));

        region = sDriver.wait(window);
        region.click(NextButton);

        return this;
    }

    public installScreens shootsScreen() throws FindFailed {
        window = new Pattern(DataProperties.getPathFor("ShootsCat.PNG"));

        region = sDriver.wait(window);
        region.click(NextButton);

        return this;
    }


    public installScreens installCatScreen() throws FindFailed {
        window = new Pattern(DataProperties.getPathFor("InstallCat.PNG"));

        region = sDriver.wait(window);
        region.click(NextButton);

        return this;
    }


    public installScreens shortCutScreen() throws FindFailed {
        window = new Pattern(DataProperties.getPathFor("ShortCut.PNG"));

        region = sDriver.wait(window);
        region.click(NextButton);

        return this;
    }

    public installScreens readyInstallScreen() throws FindFailed {
        window = new Pattern(DataProperties.getPathFor("redyInst.PNG"));
        Pattern installButton = new Pattern(DataProperties.getPathFor("installBut.PNG"));
        region = sDriver.wait(window);
        region.click(installButton);

        return this;
    }




}
