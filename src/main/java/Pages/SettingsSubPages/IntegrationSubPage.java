package Pages.SettingsSubPages;

import Pages.SettingsPage;
import org.sikuli.script.Pattern;
import util.Props;


public class IntegrationSubPage extends SettingsPage {

    private final Pattern ID = new Pattern(Props.pathForRun("ModBUS_integrationSubPage.png"));
    public boolean isValidPage() {
        return isPage(ID);
    }
}
