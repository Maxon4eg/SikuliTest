package InstallTest;

import org.sikuli.script.App;
import utils.DataProperties;

import java.io.IOException;

/**
 * Created by DespicableMe on 17.02.2016.
 * Описание:
 */
abstract class BaseInstall {
    protected void runInstall(String pathInstaller) {
        App installer = new App(pathInstaller);
        installer.open().focus();
    }

    protected boolean assertInstalation() {
        return false;
    }
}
