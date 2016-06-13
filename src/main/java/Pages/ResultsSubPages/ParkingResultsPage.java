package Pages.ResultsSubPages;

import Pages.ResultsPage;
import org.sikuli.script.Pattern;
import util.Props;


public class ParkingResultsPage extends ResultsPage {
    private final Pattern ID = new Pattern(Props.pathForRun("ParkingResultSubPage_Ident.png"));

    public boolean isValid (){
        return isPage(ID);
    }
}
