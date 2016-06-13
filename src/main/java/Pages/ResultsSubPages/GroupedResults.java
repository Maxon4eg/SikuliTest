package Pages.ResultsSubPages;

import Pages.ResultsPage;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import util.Props;

public class GroupedResults extends ResultsPage {
    private final Pattern ID = new Pattern(Props.pathForRun("Headers_GroupedResultsSubPage.png"));


    private Region resultsRegion;
    private final Pattern headers = new Pattern(Props.pathForRun("Headers_GroupedResultsSubPage.png"));

    public boolean isChanges() {
        if (resultsRegion == null) {
            resultsRegion = findRegion(headers);
        }
        resultsRegion.onChange();
        return resultsRegion.observe(20);
    }

    public boolean isChanges(int howManyChangesWait) {
        if (resultsRegion == null) {
            resultsRegion = findRegion(headers);
        }
        boolean changed = false;
        for (int i = 0; i < howManyChangesWait; i++) {
            resultsRegion.onChange();
            changed = resultsRegion.observe(20);
        }
        return changed;
    }

    public boolean isChanges(int howManyChangesWait, int howLongToWait) {
        if (resultsRegion == null) {
            resultsRegion = findRegion(headers);
        }
        boolean changed = false;
        for (int i = 0; i < howManyChangesWait; i++) {
            resultsRegion.onChange();
            changed = resultsRegion.observe(howLongToWait);
        }
        return changed;
    }

    private Region getReactionRegion() {
        Region firstNumbersRegion;
        firstNumbersRegion = new Region(findRegion(headers).getRect());
        firstNumbersRegion.setH(110);
        return firstNumbersRegion;
    }




    public boolean isValidPage(){
        return isPage(ID);
    }
}
