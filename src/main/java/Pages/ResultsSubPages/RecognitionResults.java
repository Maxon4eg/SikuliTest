package Pages.ResultsSubPages;

import Pages.ResultsPage;
import org.sikuli.basics.Settings;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import util.Props;

/**
 * Created by DespicableMe on 10.03.2016.
 * Описание:
 */
public class RecognitionResults extends ResultsPage {
    private final Pattern ID = new Pattern(Props.pathForRun("Headers_RecogResultsSubPage.png"));


    private Pattern headers = new Pattern(Props.pathForRun("Headers_RecogResultsSubPage.png"));

    /**
     * Как только появиться хоть одно изменение в регионе где появляються номера метод сразу возвращает
     * <br> По умолчанию ждем изменение 10 Секунд
     *
     * @return true-есть изменение false - нету
     */

    public boolean isChanges() {
        Region resultsRegion = findRegion(headers);
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
        Region resultsRegion = findRegion(headers);

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
        Region resultsRegion = findRegion(headers);

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

    /**
     * For catching reaction GREEN row color
     * Please wait for appearing 3 or more results
     *
     * @param howLongToWait how long to wait SECONDS for appearing green rows
     */

    public boolean isReactionWorks(int howLongToWait) {
        Pattern greenReaction = new Pattern(Props.pathForRun("GreenBOX_RecognitionResultSubPage.png"));
        Region reactionRegion = getReactionRegion();
        reactionRegion.onAppear(greenReaction);
        System.out.println("== Wait for appear green row reaction");
        return reactionRegion.observe(howLongToWait);
    }

    /**
     * Param howLongTo wit May double if reactions truly don't work and waiting will be doubled
     *
     * @param howLongToWait how long to expect green box
     * @return returns true if box is NOT appeared
     */

    public boolean isReactionNOTworks(int howLongToWait) {
        Pattern greenReaction = new Pattern(Props.pathForRun("GreenBOX_RecognitionResultSubPage.png"));
        Region reactionRegion = getReactionRegion();

        if (reactionRegion.exists(greenReaction) != null) { // if green box still present in region
            reactionRegion.onVanish(greenReaction);
            return reactionRegion.observe(howLongToWait);
        }
        reactionRegion = new Region(getReactionRegion());
        reactionRegion.onAppear(greenReaction);
        return !reactionRegion.observe(howLongToWait);
    }

    private Region getReactionRegion() {
        Region firstNumbersRegion;
        firstNumbersRegion = new Region(findRegion(headers).getRect());
        firstNumbersRegion.setH(110);
        return firstNumbersRegion;
    }
}
