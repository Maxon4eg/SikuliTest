package Ui_Test.DBSubPage;

import Ui_Test.CarDBPage;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import utils.Props;

/**
 * Created by DespicableMe on 09.03.2016.
 * Описание:
 */
public class GroupsSubPage extends CarDBPage {

    private Region groupsRegion;
    private Pattern dateCheckbox = new Pattern(Props.getPathForRun("DateWinCheckbox_GroupSubPage.png"));
    private Pattern timeCheckbox = new Pattern(Props.getPathForRun("TimeWinCheckbox_GroupSubPage.png"));

    public void newRegion() {
        // Задаем новый регион для работы
        groupsRegion = screen;
        groupsRegion.setY(125);
    }

    private Region allowByDate() {
        Region date = null;
        try {
            date = groupsRegion.find(Props.getPathForRun("AllowedByDate_GroupsSubPage.png"));
            date.highlight(1);
            date.doubleClick(emptyCheckbox);
            date.click();
        } catch (FindFailed findFailed){
            System.out.println(findFailed.getLocalizedMessage());
        }
        return date;
    }

    public GroupsSubPage setAllowBy(int by) {
        Region date = allowByDate();
        try {
            switch (by) {
                case 1://by date
                    date.click(dateCheckbox.targetOffset(-20, 0));
                    break;
                case 2://by time
                    date.click(timeCheckbox.targetOffset(-20, 0));
                    break;
                case 3://by time and date
                    date.click(dateCheckbox.targetOffset(-20, 0));
                    date.click(timeCheckbox.targetOffset(-20, 0));
                    break;
            }
        } catch (FindFailed findFailed){
            System.out.println(findFailed.getLocalizedMessage());
        }
        return this;
    }


}
