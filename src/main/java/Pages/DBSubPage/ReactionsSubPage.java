package Pages.DBSubPage;

import Pages.CarDBPage;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import util.Props;


public class ReactionsSubPage extends CarDBPage {
    private final Pattern ID = new Pattern(Props.pathForRun("Reactions_Ident.png"));


    private Region reactionEditor;

    public ReactionsSubPage addReaction() {
        try {
            screen.click(Props.pathForRun("Add_Button_ReactionSubPage.png"));
            System.out.println("== Click Add Reaction.");
            reactionEditor = screen.find(Props.pathForRun("ReactionEditor_ReactionSubPage.png"));
            System.out.println("== Reaction Editor is Appear");
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public ReactionsSubPage clickRemove() {// // TODO: 25.04.2016  remake removing reaction
        try {
            screen.click(Props.pathForRun("RemoveReaction_Button_ReactionSubPage.png"));
            System.out.println("== Click Remove ");
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    /**
     * @param choose choose reaction from dropdown from 1 - 3
     */

    public ReactionsSubPage chooseEvents(int choose) {
        try {
            reactionEditor.click(Props.pathForRun("EventType_Field_ReactionsSubPage.png"));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        selector(choose);
        reactionEditor.type(Key.ENTER);
        return this;
    }

    /**
     * Just click checkbox and set empty number
     */
    public ReactionsSubPage byNumber() {
        return byNumber("");
    }

    /**
     * Set reaction by number
     *
     * @param number by which number
     */
    public ReactionsSubPage byNumber(String number) {
        Pattern plateBox = new Pattern(Props.pathForRun("PlateNumber_Box_ReactionEditor.png"));
        try {
            reactionEditor.click(plateBox);
            reactionEditor.click(plateBox.targetOffset(0, 10));
            reactionEditor.type(number);
            System.out.println("== Set reaction By number : " + number);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public ReactionsSubPage useCountry(String country) {
        Pattern use = new Pattern(Props.pathForRun("UseCountry_Box_ReactionEditor.png"));
        try {
            reactionEditor.click(use);
            reactionEditor.click(use.targetOffset(40, 0));
            reactionEditor.type(country + Key.ENTER);
            System.out.println("== Set Reaction by Country : " + country);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    /**
     * Color of ROW will be GREEN
     */

    public ReactionsSubPage setVisualReaction() {
        try {
            reactionEditor.click(Props.pathForRun("SetVisualReaction_ReactionSubPage.png"));
            System.out.println("== Set Visual Reaction");
            reactionEditor.click(Props.pathForRun("SetRowColor_ReactionSubPage.png"));
            Thread.sleep(500);
            reactionEditor.click(new Pattern(Props.pathForRun("GreenRowColor_ReactionSubPage.png")).exact());
            System.out.println("== Click  \"Set row color \" and Select Green Row ");
            reactionEditor.click(new Pattern(Props.pathForRun("SetColorOKbutton_ReactionSubPage.png")).targetOffset(0, 20));
            System.out.println("== Click \"Ok\" in color selector ");
            Thread.sleep(50);
        } catch (FindFailed | InterruptedException findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    /**
     * Click on the firs reaction and then you can clickRemove() easily all reactions
     */

    public ReactionsSubPage clickOnTheFirstReaction() {
        Pattern activeCheckBox = new Pattern(Props.pathForRun("ActiveCheckBox_ReactionSubPage.png")).similar((float) 0.68);
        try {
            screen.click(activeCheckBox);
            System.out.println("== Click on the first reaction");
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    /**
     * by default we are showing screenshot
     * @param always always show or 15 seconds ?
     */

    public ReactionsSubPage showWindow(boolean always) {
        try {
            reactionEditor.click(Props.pathForRun("WindowCheckBox_ReactionEditor.png"));
            System.out.println("== Set \"Window\" checkBox ");
            if (!always) {
                screen.click(Props.pathForRun("Show15Sec_ReactionEditor.png"));
                System.out.println("== Set show 15 seconds");
            }
            screen.click(Props.pathForRun("ShowScren_ReactionEditor.png"));
            System.out.println("== Set Show Screen Shot ");
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }


    public ReactionsSubPage clickOK() {
        try {
            reactionEditor.click(Props.pathForRun("OK_warningMsg.png"));
            System.out.println("== Click OK ");
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        return this;
    }

    public ReactionsSubPage clickCancel() {
        try {
            reactionEditor.click(Props.pathForRun("Cancel_warningMsg.png"));
            System.out.println("== Click Cancel");
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    /**
     * @param choose    to choose exact zone
     */

    public ReactionsSubPage byZones(int choose) {
        return byZones(new int[]{choose});
    }

    /**
     *
     * @param choose [] the arr to choose several zones
     *               <br> please count from " 0 "
     *               <br> if its several zones ! !
     */
    public ReactionsSubPage byZones(int[] choose) {
        try {

            reactionEditor.click(Props.pathForRun("ChannelZone_ReactionEditor.png")); //set checkbox
            reactionEditor.click(Props.pathForRun("ChannelZone_Selector_ReactionEditor.png"));// click on dropdown by zones
            selector(choose);
            reactionEditor.type(Key.ENTER);

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }


    /**
     * Please count from 0
     * for multiple choosing please use overloaded method byChekpoint(int choose[])
     *
     * @param choose = countFrom "0"
     * @return
     */
    public ReactionsSubPage byChekpoint(int choose) {
        return byChekpoint(new int[]{choose});
    }

    /**
     * if you want just set by checkpoint and all
     */
    public ReactionsSubPage byChekpoint() {
        try {
            reactionEditor.click(Props.pathForRun("ByCheckpoint_reactionSubPage.png"));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        System.out.println("= Set by checkpoint");
        return this;
    }

    /**
     * for multiple choosing byCheckpoint(new int []{})
     *
     * @param choose please count from "0"
     */
    public ReactionsSubPage byChekpoint(int choose[]) {
        try {

            reactionEditor.click(Props.pathForRun("CheckpointDropdown_ReactionsEditor.png"));
            selector(choose);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        reactionEditor.type(Key.ENTER);
        return this;
    }

    public ReactionsSubPage selectTypeOfCar() {
        try {
            reactionEditor.click(Props.pathForRun("TypeOfCar_ReactionEditor.png"));// TODO: 01.06.2016
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        System.out.println("= Set by type of car ");
        return this;
    }

    public ReactionsSubPage selectTypeOfCar(int groups[]) {
        Pattern byGorup = new Pattern(Props.pathForRun("ByGroup_ReactionEditor.png"));
        try {
            reactionEditor.click(byGorup);//click on group
            reactionEditor.click(byGorup.targetOffset(50, 0));// use target offset for click on dropdown
            selector(groups);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        reactionEditor.type(Key.ENTER);
        return this;
    }

    public ReactionsSubPage byDuration() {
        try {
            reactionEditor.click(Props.pathForRun("ByDuration_ReactionEditor.png"));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public ReactionsSubPage setDuration(int minutes) {
        return setDuration(minutes, false, 0);
    }

    public ReactionsSubPage setDuration(int minutes, int rangeMinutes) {
        return setDuration(minutes, true, rangeMinutes);
    }

    private ReactionsSubPage setDuration(int minutes, boolean useRange, int rangeMinutes) {
        Pattern rangeDur = new Pattern(Props.pathForRun("RangeDuration_ReactionEditor.png"));
        try {

            if (useRange) {
                reactionEditor.click(rangeDur.targetOffset(-30, 0));// for targeting checkbox
                reactionEditor.doubleClick(rangeDur.targetOffset(120, 0)); // click on minutes on range dur
                reactionEditor.type(String.valueOf(rangeMinutes));
                reactionEditor.type(Key.ENTER);
            }

            if (minutes == 0) { // to end of this shit if we only to want use range
                return this;
            }
            reactionEditor.doubleClick(rangeDur.targetOffset(-120, 0)); // click on minutes by usual duration ! Works properly
            reactionEditor.type(String.valueOf(minutes));
            reactionEditor.type(Key.ENTER);

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public boolean isValidPage() {
        return isPage(ID);
    }


}
