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

    private ButtonUtil channelEntry;
    private ButtonUtil zoneDropdown;
    private ButtonUtil accessDropdown;
    private ButtonUtil accessDropdownExit;
    private ButtonUtil channelExit;
    private ButtonUtil zoneDropdownExit;

    private ButtonUtil findCP(int cp) {
        try {
            switch (cp) {
                case 1:
                    return new ButtonUtil(screen, Props.pathForRun("CP1_Button_CPSubPage.png"));
                case 2:
                    return new ButtonUtil(screen, Props.pathForRun("CP2_Button_CPSubPage.png"));
                case 3:
                    return new ButtonUtil(screen, Props.pathForRun("CP3_Button_CPSubPage.png"));
                case 4:
                    return new ButtonUtil(screen, Props.pathForRun("CP4_Button_CPSubPage.png"));
                case 5:
                    return new ButtonUtil(screen, Props.pathForRun("CP5_Button_CPSubPage.png"));
                case 6:
                    return new ButtonUtil(screen, Props.pathForRun("CP6_Button_CPSubPage.png"));
                case 7:
                    return new ButtonUtil(screen, Props.pathForRun("CP7_Button_CPSubPage.png"));
                case 8:
                    return new ButtonUtil(screen, Props.pathForRun("CP8_Button_CPSubPage.png"));
                case 9:
                    return new ButtonUtil(screen, Props.pathForRun("CP9_Button_CPSubPage.png"));
                default:
                    return null;
            }
        } catch (FindFailed findFailed) {
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
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }

    }

    /**
     * выбор дропдауна по номеру в списке происходит в цикле с помощью нажатия стрелки вниз
     * к сожалению пока что вверх по списку мы не можем возвращатся !
     *
     * @param chooseChannel Выбор Канала
     * @param chooseZone    выбор зоны
     * @param access        выбор доступа
     */

    public CheckpointSubPage cpEntry(int chooseChannel, int chooseZone, int access) {
        Region entry = null;
        String key = Key.DOWN;


        try {
            entry = screen.find(Props.pathForRun("EntryCP_CPSubPage.png"));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        if (channelEntry == null && entry != null) {
            try {
                channelEntry = new ButtonUtil(entry, Props.pathForRun("DisabledDropdown_button_CPSubPage.png"));
                zoneDropdown = new ButtonUtil(entry, Props.pathForRun("ZoneDropdown_CPSubPage.png"));
                accessDropdown = new ButtonUtil(entry, Props.pathForRun("AccesDropdown_CPSubPage.png"));

            } catch (FindFailed findFailed) {
                System.out.println(findFailed.getLocalizedMessage());
            }
        }
        try {
            assert entry != null;

            entry.click(channelEntry.getLocation());
            Thread.sleep(300);
            if (chooseChannel < 0) {
                key = Key.UP;
            }
            for (int i = 1; i < chooseChannel + 1; i++) {
                entry.type(key);

            }
            entry.type(Key.ENTER);



            key = Key.DOWN;//пререзаписываем обратно на down
            entry.click(zoneDropdown.getLocation());
            Thread.sleep(300);
            if (chooseZone < 0) {
                key = Key.UP;
            }
            for (int i = 1; i < chooseZone + 1; i++) {
                entry.type(key);
            }
            entry.type(Key.ENTER);


            key = Key.DOWN;//пререзаписываем обратно на down
            entry.click(accessDropdown.getLocation());
            Thread.sleep(300);

            if (access < 0) {
                key = Key.UP;
            }
            for (int i = 1; i < access + 1; i++) {
                entry.type(key);
            }
            entry.type(Key.ENTER);


        } catch (FindFailed | InterruptedException findFailed) {
            findFailed.printStackTrace();
        }


        return this;
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

    public CheckpointSubPage cpExit(int chooseChannel, int chooseZone, int access) {
        Region entry = null;
        String key = Key.DOWN;


        try {
            entry = screen.find(Props.pathForRun("ExitCP_CPSubPage.png"));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        if (channelExit == null && entry != null) {
            try {
                channelExit = new ButtonUtil(entry, Props.pathForRun("DisabledDropdown_button_CPSubPage.png"));
                zoneDropdownExit = new ButtonUtil(entry, Props.pathForRun("ZoneDropdown_CPSubPage.png"));
                accessDropdownExit = new ButtonUtil(entry, Props.pathForRun("AccesDropdown_CPSubPage.png"));

            } catch (FindFailed findFailed) {
                System.out.println(findFailed.getLocalizedMessage());
            }
        }
        try {
            assert entry != null;

            entry.click(channelExit.getLocation());
            Thread.sleep(300);

            if (chooseChannel < 0) {
                key = Key.UP;
            }
            for (int i = 1; i < chooseChannel + 1; i++) {
                entry.type(key);
            }
            entry.type(Key.ENTER);



            key = Key.DOWN;//пререзаписываем обратно на down
            entry.click(zoneDropdownExit.getLocation());
            Thread.sleep(300);

            if (chooseZone < 0) {
                key = Key.UP;
            }
            for (int i = 1; i < chooseZone + 1; i++) {
                entry.type(key);
            }
            entry.type(Key.ENTER);


            key = Key.DOWN;//пререзаписываем обратно на down
            entry.click(accessDropdownExit.getLocation());
            Thread.sleep(300);

            if (access < 0) {
                key = Key.UP;
            }
            for (int i = 1; i < access + 1; i++) {
                entry.type(key);
            }
            entry.type(Key.ENTER);


        } catch (FindFailed | InterruptedException findFailed) {
            findFailed.printStackTrace();
        }


        return this;
    }

    /**
     * @param checkbox выбор какой из двух чекбоксов ставить
     *                 <br> 1 - по сенсорам
     *                 <br> 2 - по зонам распознавания
     *                 <br> 3 - выбрать оба
     */

    public void passageDetermination(int checkbox) {
        Pattern sensors = new Pattern(Props.pathForRun("CheckBoxSensors_CPSubPage.png"));
        Pattern recognition = new Pattern(Props.pathForRun("CheckBoxRecognition_CPSubPage.png"));
        try {
            Region region = screen.find(Props.pathForRun("PassageDetermination_CPSubPage.png"));
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

        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
    }
}
