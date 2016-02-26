package InstallTest;

import org.sikuli.script.App;

/**
 * Created by DespicableMe on 17.02.2016.
 * Описание:
 */
abstract class BaseInstall {
    protected void runInstall(String pathInstaller) {
        App installer = new App(pathInstaller);
        installer.open().focus();
    }

}
