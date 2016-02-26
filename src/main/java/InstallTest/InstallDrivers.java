package InstallTest;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import utils.Props;

/**
 * Created by DespicableMe on 18.02.2016.
 * Описание:
 */
public class InstallDrivers {
    private Region region;
    private Pattern window;
    private Screen sDriver;
    private Pattern button;

    public InstallDrivers(Screen sDriver) {
        this.sDriver = sDriver;
    }

    public InstallDrivers ftdi() throws FindFailed {
        window = new Pattern(Props.getPathFor("FTDI.PNG"));
        button = new Pattern(Props.getPathFor("Extract.PNG"));
        region = sDriver.find(window);
        region.click(button);

        return this;
    }

    public InstallDrivers device() throws FindFailed {
        window = new Pattern(Props.getPathFor("device.PNG"));
        button = new Pattern(Props.getPathFor("nextButton.PNG"));

        region = sDriver.find(window);
        region.click(button);
        deviceInstall();
        return this;
    }

    private InstallDrivers deviceInstall() {
        window = new Pattern(Props.getPathFor("readyDeviceScreen.PNG"));
        button = new Pattern(Props.getPathFor("readyDevButt.PNG"));

        try {
            region = sDriver.wait(window, 3);
            region.click(button);
        } catch (FindFailed findFailed) {
            System.out.println("Can't find device finish window " + "\n" + findFailed.getMessage());
            return this;
        }
        return this;
    }
}
