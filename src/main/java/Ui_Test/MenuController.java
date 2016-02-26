package Ui_Test;

import org.sikuli.script.*;
import utils.Props;


/**
 * Created by DespicableMe on 26.02.2016.
 * Описание:
 */
public class MenuController {
    private MenuButton view;
    private MenuButton results;
    private MenuButton carDB;
    private MenuButton reports;
    private MenuButton settings;
    private Region region;
    private MenuButton viewActive;

    public MenuController() {
    }

    public void getButtonsLocation(Screen screen) {
        Pattern mainMenu = new Pattern(Props.getPathForRun("MainMenu_Widget.png"));


        try {
            region = screen.find(mainMenu);
            getButtons();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    private void getButtons() throws FindFailed {
        view = new MenuButton(region, Props.getPathForRun("View2_Button.png"));
        results = new MenuButton(region, Props.getPathForRun("Results_Button.png"));
        carDB = new MenuButton(region, Props.getPathForRun("Car_Button.png"));
        reports = new MenuButton(region, Props.getPathForRun("Reports_Button.png"));
        settings = new MenuButton(region, Props.getPathForRun("Settings_Button.png"));
    }


    public MenuController clickView() {
        try {
            region.click(view.getLocation());
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            System.out.println("can't click View button " + findFailed.getMessage());
        }
        return this;
    }

    public MenuController clickResults() {
        try {
            region.click(results.getLocation());
        } catch (FindFailed findFailed) {
            System.out.println("can't click results button " + findFailed.getMessage());
        }
        return this;
    }

    public MenuController clickCarDB() {
        try {
            region.click(carDB.getLocation());
        } catch (FindFailed findFailed) {
            System.out.println("can't click carDB button " + findFailed.getMessage());
        }
        return this;
    }

    public MenuController clickReports() {
        try {
            region.click(reports.getLocation());
        } catch (FindFailed findFailed) {
            System.out.println("can't click Reports button " + findFailed.getMessage());
        }
        region.mouseMove(0, -20);
        return this;
    }

    public MenuController clickSettings() {
        try {
            region.click(settings.getLocation());
        } catch (FindFailed findFailed) {
            System.out.println("can't click Settings button " + findFailed.getMessage());
        }
        region.mouseMove(0, -20);
        return this;
    }

}
