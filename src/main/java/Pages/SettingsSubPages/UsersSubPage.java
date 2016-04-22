package Pages.SettingsSubPages;

import Pages.SettingsPage;
import org.sikuli.script.Pattern;
import utils.Props;

public class UsersSubPage extends SettingsPage {
    private final Pattern ID = new Pattern(Props.pathForRun("UsersPage_Ident.png"));

    public boolean isValidPage() {
        return isPage(ID);
    }
}
