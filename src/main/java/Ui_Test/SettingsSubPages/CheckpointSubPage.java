package Ui_Test.SettingsSubPages;

import Ui_Test.SettingsPage;
import org.sikuli.script.*;
import utils.ButtonUtil;
import utils.Props;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by DespicableMe on 09.03.2016.
 * Описание:
 */

@SuppressWarnings("Duplicates")

public class CheckpointSubPage extends SettingsPage {

    private ButtonUtil findCP(int cp) {
        try {
            switch (cp) {
                case 1:
                    return new ButtonUtil(screen, Props.getPathForRun("CP1_Button_CPSubPage.png"));
                case 2:
                    return new ButtonUtil(screen, Props.getPathForRun("CP2_Button_CPSubPage.png"));
                case 3:
                    return new ButtonUtil(screen, Props.getPathForRun("CP3_Button_CPSubPage.png"));
                case 4:
                    return new ButtonUtil(screen, Props.getPathForRun("CP4_Button_CPSubPage.png"));
                case 5:
                    return new ButtonUtil(screen, Props.getPathForRun("CP5_Button_CPSubPage.png"));
                case 6:
                    return new ButtonUtil(screen, Props.getPathForRun("CP6_Button_CPSubPage.png"));
                case 7:
                    return new ButtonUtil(screen, Props.getPathForRun("CP7_Button_CPSubPage.png"));
                case 8:
                    return new ButtonUtil(screen, Props.getPathForRun("CP8_Button_CPSubPage.png"));
                case 9:
                    return new ButtonUtil(screen, Props.getPathForRun("CP9_Button_CPSubPage.png"));
                default:
                    return null;
            }
        } catch (FindFailed findFailed){
            System.out.println(findFailed.getLocalizedMessage());
            return null;
        }
    }

    /**
     * @param checkpoint номер чекпоинта (счет начинается с еденицы )
     *                   если поставить больше чем есть на самом деле получишь либо findFailed либо nullPointer
     */

    public void chooseCP(int checkpoint) {
        try {
            ButtonUtil cp = findCP(checkpoint);
            if (cp != null) {
                screen.click(cp.getPattern());
                checkState(cp);
            }
        } catch (FindFailed findFailed){
            System.out.println(findFailed.getLocalizedMessage());
        }

    }

    /**
     * выбор дропдауна по номеру в списке происходит в цикле с помощью нажатия стрелки вниз
     * к сожалению пока что вверх по списку мы не можем возвращатся ...
     *
     * @param chooseChannel Выбор Канала
     * @param chooseZone    выбор зоны
     * @param access        выбор доступа
     */

    public void cpEntry(int chooseChannel, int chooseZone, int access) {
        Pattern dropdown = new Pattern(Props.getPathForRun("DropDown_CPSubPage.png"));
        ArrayList<Match> matches = new ArrayList<>();
        Region entry;
        try {
            entry = screen.find(Props.getPathForRun("EntryCP_CPSubPage.png"));
            Iterator<Match> matchIterator = entry.findAll(dropdown);
            while (matchIterator.hasNext()) {
                matches.add(matchIterator.next());
            }
            Collections.sort(matches);
            Collections.reverse(matches);
            for (int i = 0; i < matches.size(); i++) {
                matches.get(i).highlight(1);
            }

            matches.get(2).click();//так отсортировало что это третий в листе
            for (int i = 1; i < chooseChannel + 1; i++) {
                entry.type(Key.DOWN);
            }
            entry.type(Key.ENTER);
            matches.get(0).click();
            for (int i = 1; i < chooseZone + 1; i++) {
                entry.type(Key.DOWN);
            }
            entry.type(Key.ENTER);
            matches.get(1).click();
            for (int i = 1; i < access + 1; i++) {
                entry.type(Key.DOWN);
            }
            entry.type(Key.ENTER);
        } catch (FindFailed findFailed){
            System.out.println(findFailed.getLocalizedMessage());
        }
    }

    /**
     * Идентичнно cpEntry
     * выбор дропдауна по номеру в списке происходит в цикле с помощью нажатия стрелки вниз
     * к сожалению пока что вверх по списку мы не можем возвращатся ...
     *
     * @param chooseChannel Выбор Канала
     * @param chooseZone    выбор зоны
     * @param access        выбор доступа
     */

    public void cpExit(int chooseChannel, int chooseZone, int access) {
        Pattern dropdown = new Pattern(Props.getPathForRun("DropDown_CPSubPage.png"));
        Region entry ;
        ArrayList<Match> matches = new ArrayList<>();
        try {
            entry = screen.find(Props.getPathForRun("ExitCP_CPSubPage.png"));

            Iterator<Match> matchIterator = entry.findAll(dropdown);
            while (matchIterator.hasNext()) {
                matches.add(matchIterator.next());
            }
            Collections.sort(matches);
            Collections.reverse(matches);
            for (int i = 0; i < matches.size(); i++) {
                matches.get(i).highlight(1);
            }
            matches.get(2).click();//так отсортировало что это третий в листе
            for (int i = 1; i < chooseChannel + 1; i++) {
                entry.type(Key.DOWN);
            }
            entry.type(Key.ENTER);
            matches.get(0).click();
            for (int i = 1; i < chooseZone + 1; i++) {
                entry.type(Key.DOWN);
            }
            entry.type(Key.ENTER);
            matches.get(1).click();
            for (int i = 1; i < access + 1; i++) {
                entry.type(Key.DOWN);
            }
            entry.type(Key.ENTER);

        } catch (FindFailed findFailed){
            System.out.println(findFailed.getLocalizedMessage());
        }
    }

    /**
     * @param checkbox выбор какой из двух чекбоксов ставить
     *                 <br> 1 - по сенсорам
     *                 <br> 2 - по зонам распознавания
     *                 <br> 3 - выбрать оба
     */

    public void passageDetermination(int checkbox) {
        Pattern sensors = new Pattern(Props.getPathForRun("CheckBoxSensors_CPSubPage.png"));
        Pattern recognition = new Pattern(Props.getPathForRun("CheckBoxRecognition_CPSubPage.png"));
        try {
            Region region = screen.find(Props.getPathForRun("PassageDetermination_CPSubPage.png"));
            switch (checkbox) {
                case 1:
                    region.click(sensors);
                    break;
                case 2:
                    region.click(recognition);
                    break;
                case 3:
                    region.click(sensors);
                    region.click(recognition);
                    break;
                default:
                    break;
            }

        } catch (FindFailed findFailed){
            System.out.println(findFailed.getLocalizedMessage());
        }
    }
}
