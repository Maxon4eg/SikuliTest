package Ui_Test;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import utils.ButtonUtil;
import utils.Props;


public class MainController extends AbstractPage {
    protected static boolean stateSwitch; // необходимо делать статическим , потому что потом переиспользуем
    protected static boolean switchedOperationMode = false;
    protected Pattern emptyCheckbox = new Pattern(Props.pathForRun("_EmptyCheckBox.png"));
    private boolean memorized;
    private ButtonUtil view;
    private ButtonUtil results;
    private ButtonUtil carDB;
    private ButtonUtil reports;
    private ButtonUtil settings;
    private Region region;
    private boolean using;

    /**
     * Необходимо использовать перед тем как нажимать какие либо кнопки меню
     */
    private void memoryButtonsLocation() {
        Pattern mainMenu = new Pattern(Props.pathForRun("MainMenu_Widget.png"));
        try {
            region = screen.find(mainMenu);
            getButtons(region);
            memorized = true;
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            memorized = false;
        }
    }

    private void getButtons(Region region) throws FindFailed {
        view = new ButtonUtil(region, Props.pathForRun("View_Button.png"));
        results = new ButtonUtil(region, Props.pathForRun("Results_Button.png"));
        carDB = new ButtonUtil(region, Props.pathForRun("Car_Button.png"));
        reports = new ButtonUtil(region, Props.pathForRun("Reports_Button.png"));
        settings = new ButtonUtil(region, Props.pathForRun("Settings_Button.png"));
    }

    public ViewPage clickView() {
        if (!memorized) {
            memoryButtonsLocation();
        }
        try {

            region.click(view.getPattern());
            stateSwitch = !checkState(view);//инвертируем потому что изначально view активный
        } catch (FindFailed findFailed) {
            try {
                System.out.println("trying to click NA View Button ");
                region.click(Props.pathForRun("ViewNA_Button.png"));
            } catch (FindFailed findFailed1) {
                System.out.println("can't click View button " + findFailed1.getLocalizedMessage());
                System.out.println("can't click View button " + findFailed.getLocalizedMessage());
            }
        }
        return new ViewPage();
    }

    public MainController clickResults() {
        if (!memorized) {
            memoryButtonsLocation();
        }
        try {
            region.click(results.getPattern());
            stateSwitch = checkState(results);
        } catch (FindFailed findFailed) {
            System.out.println("can't click results button " + findFailed.getMessage());
        }
        return this;
    }

    public CarDBPage clickCarDB() {
        if (!memorized) {
            memoryButtonsLocation();
        }

        try {
            region.click(carDB.getPattern());
            stateSwitch = checkState(carDB);
        } catch (FindFailed findFailed) {
            System.out.println("can't click carDB button " + findFailed.getMessage());
        }
        return new CarDBPage();
    }

    public ReportsPage clickReports() {
        if (!memorized) {
            memoryButtonsLocation();
        }

        try {
            region.click(reports.getPattern());
            stateSwitch = checkState(reports);
        } catch (FindFailed findFailed) {
            System.out.println("can't click Reports button " + findFailed.getMessage());
        }
        return new ReportsPage();
    }

    public SettingsPage clickSettings() {
        if (!memorized) {
            memoryButtonsLocation();
        }

        try {
            region.click(settings.getPattern());
            stateSwitch = checkState(settings);
        } catch (FindFailed findFailed) {
            System.out.println("can't click Settings button " + findFailed.getMessage());
        }
        return new SettingsPage();
    }


    public boolean isAppear() {
        Pattern menu = new Pattern(Props.pathForRun("Main_Page.png")).similar((float) 0.7);
        try {
            region = screen.wait(menu, 20);
            System.out.println("numberok Started successfully");
            return true;
        } catch (FindFailed findFailed) {
            if (!using) {
                focus();
                using = true;
                isAppear();
            }
            System.out.println("failed to find menu trying to focus window ");
            return false;
        }
    }


    public void maximize() {
        Pattern pattern = new Pattern(Props.pathForRun("_winContr.png")).targetOffset(-15, 0);// смещение цели -x:left, -y:up

        try {
            screen.click(pattern);
        } catch (FindFailed findFailed) {
            System.out.println("could not maximize");
            try {
                pattern = new Pattern(Props.pathForRun("_winContrHover.png")).targetOffset(-12, 0).similar((float) 0.8);
                region.click(pattern);
            } catch (FindFailed findFailed2) {
                System.out.println("could'nt find  = " + findFailed2.getLocalizedMessage());
            }
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
//        System.out.println("Getter "+stateSwitch);
        return stateSwitch;
    }
}