package Pages;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import utils.ButtonUtil;
import utils.Props;


@SuppressWarnings("Duplicates")
public class MainController extends AbstractPage {
    protected static boolean stateSwitch; // необходимо делать статическим , потому что потом переиспользуем
    protected static boolean switchedOperationMode = false;
    protected Pattern emptyCheckbox = new Pattern(Props.pathForRun("_EmptyCheckBox.png"));

    private void getButtons(Region region) throws FindFailed {
    }// may be ,

    public ViewPage clickView() {

        try {
            ButtonUtil view = new ButtonUtil(screen, Props.pathForRun("View_Button.png"));
            screen.click(view.getPattern());
            stateSwitch = !checkState(view);//инвертируем потому что изначально view активный
        } catch (FindFailed findFailed) {
            try {
                System.out.println("trying to click NA View Button ");
                screen.click(Props.pathForRun("ViewNA_Button.png"));
            } catch (FindFailed findFailed1) {
                System.out.println("can't click View button " + findFailed1.getLocalizedMessage());
                System.out.println("can't click View button " + findFailed.getLocalizedMessage());
            }
        }
        return new ViewPage();
    }

    public MainController clickResults() {
        try {
            ButtonUtil results = new ButtonUtil(screen, Props.pathForRun("Results_Button.png"));
            screen.click(results.getPattern());
            stateSwitch = checkState(results);
        } catch (FindFailed findFailed) {
            System.out.println("can't click results button " + findFailed.getMessage());
        }
        return this;
    }

    public MainController clickCarDB() {
        try {
            ButtonUtil carDB = new ButtonUtil(screen, Props.pathForRun("Car_Button.png"));
            screen.click(carDB.getPattern());
            stateSwitch = checkState(carDB);
        } catch (FindFailed findFailed) {
            System.out.println("can't click carDB button " + findFailed.getMessage());
        }
        return this;
    }

    public MainController clickReports() {
        try {
            ButtonUtil reports = new ButtonUtil(screen, Props.pathForRun("Reports_Button.png"));
            screen.click(reports.getPattern());
            stateSwitch = checkState(reports);
        } catch (FindFailed findFailed) {
            System.out.println("can't click Reports button " + findFailed.getMessage());
        }
        return this;
    }

    public MainController clickSettings() {
        try {
            ButtonUtil settings = new ButtonUtil(screen, Props.pathForRun("Settings_Button.png"));
            screen.click(settings.getPattern());
            stateSwitch = checkState(settings);
        } catch (FindFailed findFailed) {
            System.out.println("can't click Settings button " + findFailed.getMessage());
        }
        return this;
    }


    public boolean isAppear() {
        Pattern menu = new Pattern(Props.pathForRun("Main_Page.png")).similar((float) 0.7);
        screen.onAppear(menu);
        return screen.observe(120);
    }


    public void maximize() {
        Pattern pattern = new Pattern(Props.pathForRun("_winContr.png")).targetOffset(-10, 0);// смещение цели -x:left, -y:up
        Pattern menu = new Pattern(Props.pathForRun("Main_Page.png")).similar((float) 0.7);
        Region region;
        try {
            region = screen.find(menu);
            region.click(pattern);
        } catch (FindFailed findFailed) {
            System.out.println("could not maximize");
        }
    }


    protected boolean checkState(ButtonUtil button) {
        int after = button.getTarget_RGB().getColor().getRGB();
        int before = button.getRGB();
//        System.out.println("================================");
//        System.out.println("RGB BEFORE = " + before);
//        System.out.println("RGB AFTER = " + after);
        return after != before;
    }


    public boolean isStateSwitched() {
        return stateSwitch;
    }

    protected void selector(int times) {
        String key;

        if (times > 0) {
            key = Key.DOWN;
            for (int i = 0; i < times - 1; ++i) {
                screen.type(key);
            }
        } else {
            key = Key.UP;
            for (int i = 0; i < times - 1; --i) {// TODO : 17.03.2016 проверь как нибудь
                screen.type(key);
            }
        }
    }

    protected boolean isPage(Pattern page) {
        try {
            screen.find(page.exact());
            return true;
        } catch (FindFailed findFailed) {
            return false;
        }
    }
}