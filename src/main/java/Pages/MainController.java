package Pages;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import util.ButtonUtil;
import util.Props;

import java.util.Arrays;


@SuppressWarnings("Duplicates")
public class MainController extends AbstractPage {
    protected static boolean stateSwitch; // необходимо делать статическим , потому что потом переиспользуем
    protected static boolean switchedOperationMode = false;
    protected Pattern emptyCheckbox = new Pattern(Props.pathForRun("_EmptyCheckBox.png"));

    public ViewPage clickView() {//// TODO: 25.04.2016  deal with return; please make me to return control pages

        try {
            ButtonUtil view = new ButtonUtil(screen, Props.pathForRun("View_Button.png"));
            screen.click(view.getPattern());
            stateSwitch = !checkState(view);//инвертируем потому что изначально view активный
        } catch (FindFailed findFailed) {
            try {
                screen.click(Props.pathForRun("ViewNA_Button.png"));
            } catch (FindFailed findFailed1) {
                System.out.println("can't click View button " + findFailed1.getLocalizedMessage());
                System.out.println("can't click View button " + findFailed.getLocalizedMessage());
            }
        }
        System.out.println("== Click View");
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
        System.out.println("== Click Results");
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
        System.out.println("== Click car data base ");
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
        System.out.println("== Click Reports");
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
        System.out.println("== Click Settings");
        return this;
    }


    public boolean waitAppearing() {
        System.out.println("== Wait for Appear ");
        Pattern menu = new Pattern(Props.pathForRun("Main_Page.png"));
        Region screenRegion = screen.getScreen();
        try {
            screenRegion.wait(menu.exact(), 120);
//            screenRegion.find(menu).highlight(1);
            return true;
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            return false;
        }
    }


    public void maximize() {
        System.out.println("== Maximize window ");
        Pattern pattern = new Pattern(Props.pathForRun("_winContr.png"));// смещение цели -x:left, -y:up
        Pattern menu = new Pattern(Props.pathForRun("Main_Page.png")).similar((float) 0.7);
        Region region;
        try {
            region = screen.find(menu);
            region.click(pattern);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            System.out.println("!could not maximize!");
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


    /**
     * For multiple choose checkboxes from dropdown
     *
     * @param arr please count from 0
     */


    protected void selector(int arr[]) {
        int maxValue = 0;
        Arrays.sort(arr);
        try {
            Thread.sleep(100);

            if (arr.length == 1) {
                int arrN = arr[0];
                if (arrN == 0) {
                    screen.type(Key.DOWN);
                    screen.type(Key.SPACE);
                } else {
                    for (int i = 0; i < arrN; i++) {
                        screen.type(Key.DOWN);
                    }
                    screen.type(Key.SPACE);
                }
            } else {

                for (int anArr1 : arr) {
                    if (maxValue < anArr1) {
                        maxValue = anArr1;
                    }
                }

                for (int i = 0; i < maxValue + 1; i++) {
                    screen.type(Key.DOWN);
                    Thread.sleep(100);

                    for (int anArr : arr) {
                        if (anArr == i) {
                            screen.type(Key.SPACE);
                            Thread.sleep(100);
                        }
                    }
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    protected void selector(int times) {
        try {
            if (times > 0) {
                if (times == 1) {
                    Thread.sleep(500);
                    screen.type(Key.DOWN);
                } else {

                    for (int i = 0; i < times; ++i) {
                        Thread.sleep(500);
                        screen.type(Key.DOWN);
                    }
                }
            } else {
                for (int i = 0; i > times - 1; --i) {// TODO : 17.03.2016 проверь как нибудь
                    Thread.sleep(500);
                    screen.type(Key.UP);
                }
            }
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected boolean isPage(Pattern page) {
        System.out.println("== Assert that page is valid");
        try {
            screen.find(page.exact());
            return true;
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            return false;
        }
    }
}