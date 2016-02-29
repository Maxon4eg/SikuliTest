package Ui_Test;

import javafx.beans.binding.When;
import org.junit.Assert;
import org.sikuli.script.*;
import utils.Props;

/**
 * Created by DespicableMe on 22.02.2016.
 * Описание:
 */
public class MainPage extends AbstractPage {

    private Screen screen;

    public MainPage(Screen screen) {
        this.screen = screen;
    }

    public boolean isAppear() {
        Pattern viewPage = new Pattern(Props.getPathForRun("Main_Page.png"));
        try {
            screen.wait(viewPage, 20);
            System.out.println("numberok Started successfully");

        } catch (FindFailed findFailed) {
            Assert.assertTrue(findFailed.getLocalizedMessage(), false);
            return false;
        }
        return true;
    }

    public Screen maximize() {
        Pattern pattern = new Pattern(Props.getPathForRun("_winContr.png")).targetOffset(-15, 0);// смещение цели -x:left, -y:up
        Region region = screen.exists(pattern);

        try {
            region.click(pattern);
        } catch (FindFailed findFailed) {
            System.out.println("could not maximize");
        }

        return new Screen();
    }


}
