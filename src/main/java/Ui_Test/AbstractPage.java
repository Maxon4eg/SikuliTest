package Ui_Test;


import org.sikuli.script.App;
import org.sikuli.script.Screen;
import utils.Props;

/**
 * Created by DespicableMe on 22.02.2016.
 * Описание:
 */
abstract class AbstractPage {

    protected  Screen screen;
    protected static App numberok;


    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public static void runNumberok() {
        numberok = new App(Props.getProperty("NumberOK.exe"));
        numberok.open();
    }

    public static void destroy() {
        numberok.close();
    }

    protected boolean isRunning() {
        return numberok.isRunning();
    }


    public void takeScreenShoot() {
    }

}
