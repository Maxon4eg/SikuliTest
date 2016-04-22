package Pages.DBSubPage;

import Pages.CarDBPage;
import org.sikuli.script.*;
import utils.Props;

public class ReactionsSubPage extends CarDBPage {
    private final Pattern ID = new Pattern(Props.pathForRun("Reactions_Ident.png"));


    private App reaction;
    private Region reactionEditor;

    public boolean isEditorAppear() {
        reaction = App.focus("Reaction editor");
        if (reaction.isRunning()) {
            reactionEditor = reaction.window();
//            reactionEditor.highlight(1);
            return true;
        } else return false;
    }

    public ReactionsSubPage addReaction() {
        try {
            screen.click(Props.pathForRun("Add_Button_ReactionSubPage.png"));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public ReactionsSubPage removeReaction() {
        try {
            screen.click(Props.pathForRun("RemoveReaction_Button_ReactionSubPage.png"));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public ReactionsSubPage chooseEvents(int choose) {
        try {
            reactionEditor.click(Props.pathForRun("EventType_Field_ReactionsSubPage.png"));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        selector(choose);
        return this;
    }

    public ReactionsSubPage byNumber() {
        Pattern plateBox = new Pattern(Props.pathForRun("PlateNumber_Box_ReactionEditor.png"));
        try {
            reactionEditor.click(plateBox);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public ReactionsSubPage byNumber(String number) {
        Pattern plateBox = new Pattern(Props.pathForRun("PlateNumber_Box_ReactionEditor.png"));
        try {
            reactionEditor.click(plateBox);
            reactionEditor.click(plateBox.targetOffset(0, 10));
            reactionEditor.type(number);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public ReactionsSubPage useCountry (String country){
        Pattern use = new Pattern(Props.pathForRun("UseCountry_Box_ReactionEditor.png"));
        try {
            reactionEditor.click(use);
            reactionEditor.click(use.targetOffset(25,0));
            reactionEditor.type(country+ Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public boolean isValidPage(){
        return isPage(ID);
    }

//    public ReactionsSubPage selectType


}
