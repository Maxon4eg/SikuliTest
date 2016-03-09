package Ui_Test.DBSubPage;

import Ui_Test.CarDBPage;
import org.sikuli.script.Region;

/**
 * Created by DespicableMe on 09.03.2016.
 * Описание:
 */
public class GroupsSubPage extends CarDBPage {

    private Region groupsRegion;

    public void newRegion() {
        // Задаем новый регион для работы
        groupsRegion = screen;
        groupsRegion.setY(125);
    }
}
