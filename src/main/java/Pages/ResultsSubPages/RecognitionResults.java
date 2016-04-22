package Pages.ResultsSubPages;

import Pages.ResultsPage;
import org.sikuli.basics.Settings;
import org.sikuli.script.Observer;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Sikulix;
import utils.Props;

/**
 * Created by DespicableMe on 10.03.2016.
 * Описание:
 */
public class RecognitionResults extends ResultsPage {
    private final Pattern ID = new Pattern(Props.pathForRun("Headers_RecogResultsSubPage.png"));


    private Pattern headers = new Pattern(Props.pathForRun("Headers_RecogResultsSubPage.png"));
    private Region resultsRegion;

    /**
     * Как только появиться хоть одно изменение в регионе где появляються номера метод сразу возвращает
     * <br> По умолчанию ждем изменение 10 Секунд
     *
     * @return true-есть изменение false - нету
     */

    public boolean isChanges() {
        if (resultsRegion == null || resultsRegion.hasObserver()) {
            resultsRegion = findRegion(headers);
        }
        resultsRegion.onChange();
        return resultsRegion.observe(15);
    }


    /**
     * По умолчанию ждем изменение 10 Секунд
     *
     * @param howManyChangesWait Соклько изменений ждать после чего возвращать
     * @return true-есть изменение false - нету
     */
    public boolean isChanges(int howManyChangesWait) {
        return isChanges(howManyChangesWait, 15);
    }

    /**
     * @param howManyChangesWait Сколько изменений ждать после чего возвращать observer TrashHold
     * @param howLongToWait      сколько ждать появление изменения
     * @return true-есть изменение false - нету
     */

    public boolean isChanges(int howManyChangesWait, int howLongToWait) {
        if (resultsRegion == null || resultsRegion.hasObserver()) {
            resultsRegion = findRegion(headers);
        }
        boolean changed = false;
        for (int i = 0; i < howManyChangesWait; i++) {
            resultsRegion.onChange();
            changed = resultsRegion.observe(howLongToWait);
            if (!changed) {
                return false;
            }
        }
        return changed;
    }


    public boolean entryLogic() {
        Pattern attempt = new Pattern(Props.pathForRun("attemptToEnter_img.png")).exact();
        Pattern entry = new Pattern(Props.pathForRun("entry_RecognitionResults_img.png")).exact();

        if (resultsRegion == null) {
            resultsRegion = findRegion(headers);
        }
        Settings.ObserveScanRate = (float) 1;

        resultsRegion.onAppear(attempt);

        if (resultsRegion.observe(10)) {
            resultsRegion.onAppear(entry);
            return resultsRegion.observe(5);
        } else return false;
    }

    public boolean isValidPage() {
        return isPage(ID);
    }
}
