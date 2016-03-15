package Ui_Test;


import org.sikuli.basics.Settings;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import utils.Props;

/**
 * Created by DespicableMe on 22.02.2016.
 * Описание:
 */
abstract class AbstractPage {

    protected static App numberok;
    protected static Screen screen = new Screen();

//    public static void runNumberok() {
//        System.out.println(Props.getProperty("NumberOK.exe"));
//        numberok = App.open(Props.getProperty("NumberOK.exe"));
//    }

    public static void destroy() {
        numberok.close();
    }

    protected boolean isRunning() {
        try {
            return numberok.isRunning();
        } catch (NullPointerException ignored) {
            numberok = new App("NumberOk");
            return numberok.isRunning();
        }
    }

    protected boolean focus() {
        Pattern focused = new Pattern(Props.pathForRun("_Focused.png"));
        App.focus("NumberOk");
        try {
            screen.find(focused);
            return true;
        } catch (FindFailed findFailed) {
            App.focus("NumberOk", 1);
            try {
                screen.find(focused);
                return true;
            } catch (FindFailed findFailed1) {
                return false;
            }
        }
    }

    protected boolean isFocused() {
        Pattern focused = new Pattern(Props.pathForRun("_Focused.png"));
        try {
            screen.find(focused);
            return true;
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
            return false;
        }
    }

    public void takeScreenShoot() {
    }

}
