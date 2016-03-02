package Ui_Test;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import utils.ButtonUtil;
import utils.Props;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by DespicableMe on 26.02.2016.
 * Описание:
 */
public class MainController extends AbstractPage {
    private boolean memorized;
    private ButtonUtil view;
    private ButtonUtil results;
    private ButtonUtil carDB;
    private ButtonUtil reports;
    private ButtonUtil settings;
    private Region region;

    /**
     * Необходимо использовать перед тем как нажимать какие либо кнопки меню
     */
    private void memoryButtonsLocation() {
        Pattern mainMenu = new Pattern(Props.getPathForRun("MainMenu_Widget.png"));
        try {
            region = screen.find(mainMenu);
            getButtons();
            memorized = true;
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            memorized = false;
        }
    }

    private void getButtons() throws FindFailed {
        view = new ButtonUtil(region, Props.getPathForRun("View2_Button.png"));
        results = new ButtonUtil(region, Props.getPathForRun("Results_Button.png"));
        carDB = new ButtonUtil(region, Props.getPathForRun("Car_Button.png"));
        reports = new ButtonUtil(region, Props.getPathForRun("Reports_Button.png"));
        settings = new ButtonUtil(region, Props.getPathForRun("Settings_Button.png"));
    }


    public MainController clickView() {
        if (!memorized) {
            memoryButtonsLocation();
        }
        try {
            region.click(view.getLocation());
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            System.out.println("can't click View button " + findFailed.getMessage());
        }
        return new ViewPage();
    }

    public MainController clickResults() {
        if (!memorized) {
            memoryButtonsLocation();
        }

        try {
            region.click(results.getLocation());
        } catch (FindFailed findFailed) {
            System.out.println("can't click results button " + findFailed.getMessage());
        }
        return this;
    }

    public MainController clickCarDB() {
        if (!memorized) {
            memoryButtonsLocation();
        }

        try {
            region.click(carDB.getLocation());
        } catch (FindFailed findFailed) {
            System.out.println("can't click carDB button " + findFailed.getMessage());
        }
        return this;
    }

    public MainController clickReports() {
        if (!memorized) {
            memoryButtonsLocation();
        }

        try {
            region.click(reports.getLocation());
        } catch (FindFailed findFailed) {
            System.out.println("can't click Reports button " + findFailed.getMessage());
        }
        region.mouseMove(0, -20);
        return this;
    }

    public MainController clickSettings() {
        if (!memorized) {
            memoryButtonsLocation();
        }

        try {
            region.click(settings.getLocation());
        } catch (FindFailed findFailed) {
            System.out.println("can't click Settings button " + findFailed.getMessage());
        }
        return this;
    }


    public boolean isAppear() {
        Pattern menu = new Pattern(Props.getPathForRun("Main_Page.png")).similar((float) 0.7);
        try {
            region = screen.wait(menu, 20);
            return true;
        } catch (FindFailed findFailed) {
            System.out.println("failed to find menu trying to focus window ");
        }
        System.out.println("numberok Started successfully");

        try {
            Pattern logo = new Pattern(Props.getPathForRun("WinPanleLogo.png"));
            screen.click(logo);
            screen.find(menu);
            return true;
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return false;
    }


    public void maximize() {
        Pattern pattern = new Pattern(Props.getPathForRun("_winContr.png")).targetOffset(-15, 0);// смещение цели -x:left, -y:up

        try {
            region.click(pattern);
            return;
        } catch (FindFailed findFailed) {
            System.out.println("could not maximize");
        }
        try {
            pattern = new Pattern(Props.getPathForRun("_winContrHover.png")).targetOffset(-12, 0).similar((float) 0.8);
            region.click(pattern);
        } catch (FindFailed findFailed) {
            System.out.println("could'nt find  = "+findFailed.getLocalizedMessage());
        }

    }


    public MainController findChannels() {
        List<Match> match = new ArrayList<Match>();

        try {
            Iterator<Match> matches = screen.findAllText("Video stream is not connected");
            int i = 0;
            while (matches.hasNext()) {
                match.add(matches.next());
                System.out.println("Channel #" + i++);
            }
            System.out.println("Numbers of matches is " + match.size());
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        return this;
    }

}
