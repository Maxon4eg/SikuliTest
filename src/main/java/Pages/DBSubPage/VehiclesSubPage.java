package Pages.DBSubPage;

import Pages.CarDBPage;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import utils.Props;

public class VehiclesSubPage extends CarDBPage {
    private final Pattern ID = new Pattern(Props.pathForRun("VehiclesSubPage_Ident.png"));

    /**
     * Method types data in selected column
     * Use only in created number
     * if you want to update please use changeNumberData
     *
     * @param column :
     *               <br>1- country  - Type Country
     *               <br>2- Plate Number
     *               <br>3- Owner
     *               <br>4- Group (You may sent keys Key.DOWN)
     *               <br>5- Car Description
     *               <br>6- Notes
     * @param text   Text you want to sent
     * @see #changeNumberData
     */

    public VehiclesSubPage typeDataIn(int column, String text) {
        try {
            screen.doubleClick(getDefaultColumnPattern(column));
            screen.type(text);
            screen.type(Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    private Pattern getDefaultColumnPattern(int column) {
        switch (column) {
            case 1:
                return new Pattern(Props.pathForRun("DefaultCountry_VehiclesSubPage.png")).exact();
            case 2:
                return new Pattern(Props.pathForRun("DefaultPlate_VehiclesSubPage.png"));
            case 3:
                return new Pattern(Props.pathForRun("DefaultOwner_VehiclesSubPage.png"));
            case 4:
                return new Pattern(Props.pathForRun("DefaultGroup_VehiclesSubPage.png"));
            case 5:
                return new Pattern(Props.pathForRun("DefaultDesc_VehiclesSubPage.png"));
            case 6:
                return new Pattern(Props.pathForRun("DefaultNotes_VehiclesSubPage.png"));
            default:
                return null;
        }
    }

    /**
     * @param filter выбор фильтра от 1-6
     * @param text   текст который будем вписевать
     */

    public VehiclesSubPage typeFilter(int filter, String text) {
        try {
            screen.click(getFilterTarget(filter));
            screen.type(text);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        } catch (NullPointerException ignored) {
            System.out.println("Please choose from 1 - 6");
        }
        return this;
    }

    /**
     * Cleaning filter
     *
     * @param filter number of filter
     */

    public VehiclesSubPage clearFilter(int filter) {
        try {
            screen.doubleClick(getFilterTarget(filter));
            screen.type(Key.DELETE);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        } catch (NullPointerException ignored) {
            System.out.println("Please choose from 1 - 6");
        }
        return this;
    }


    /**
     * Choose filter
     *
     * @param filter 1 - country
     *               2 - PlateNumb
     *               3 - owner
     *               4 - group
     *               5 - Description
     *               6 - Notes
     */

    private Pattern getFilterTarget(int filter) throws FindFailed {
        switch (filter) {
            case 1:
                return new Pattern(Props.pathForRun("Country_Header.png")).targetOffset(0, 18);
            case 2:
                return new Pattern(Props.pathForRun("PlateNumber_Header.png")).targetOffset(0, 18);
            case 3:
                return new Pattern(Props.pathForRun("Owner_Header.png")).targetOffset(0, 18);
            case 4:
                return new Pattern(Props.pathForRun("Group_Header.png")).targetOffset(0, 18);
            case 5:
                return new Pattern(Props.pathForRun("CarsDesc_Header.png")).targetOffset(0, 18);
            case 6:
                return new Pattern(Props.pathForRun("Notes_Header.png")).targetOffset(0, 18);
            default:
                throw new FindFailed("please choose correct filter");
        }
    }

    public VehiclesSubPage deleteNumber(String number) {
        typeFilter(2, number);
        try {
            screen.click(getFilterTarget(2).targetOffset(0, -25));
            clickDelete();
            return this;
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            return this;
        } catch (NullPointerException ignored) {
            System.out.println("smth goes wrong with deleting Number. I've catch Null");
            return this;
        }
    }

    /**
     * Choosing number by typing into filter his number
     * Please be sure that filter is clean
     * <p/>
     * Updating new number data Please be sure that number is correct and created befor
     *
     * @param number  number whos data to change
     * @param column  which column is want to change
     *                <br>1 - country type country
     *                <br>2 - PlateNumb
     *                <br>3 - owner
     *                <br>4 - group this may not work
     *                <br>5 - Description
     *                <br>6 - Notes
     * @param newData new data to type
     */
    public VehiclesSubPage changeNumberData(String number, int column, String newData) {
        typeFilter(2, number);
        try {
            screen.doubleClick(getFilterTarget(column).targetOffset(0, -28)); // TODO: 15.04.2016  test is targetOffset correct ?
            screen.type(newData);
            screen.type(Key.ENTER);
            if (column == 4) {
                Thread.sleep(500);
                screen.type(Key.ENTER);
            } else return this;
        } catch (FindFailed | InterruptedException findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    /**
     * Its already created test , you need to call this method in Vehicles sub page
     * <p/>
     * Please be sure that you have an a default groups
     *
     * @return true if warning massage is appear
     */

    public boolean isWarningMsgPresent() {
        Pattern defGroup = getDefaultColumnPattern(4);
        clickAdd();
        typeDataIn(2, "TestNumber");
        try {
            screen.doubleClick(defGroup);
            Thread.sleep(500);// this is need because gui work is "magnificent"
            screen.click();
            screen.type(Key.DOWN);
            screen.type(Key.ENTER);
            //on this moment should be an a warning msg
            //lets do the next steps in other try/catch
        } catch (FindFailed | InterruptedException findFailed) {
            findFailed.printStackTrace();
        }
        try {
            screen.find(Props.pathForRun("warningMsg_VehiclesSubPage.png"));
            return true;
        } catch (FindFailed findFailed) {
            return false;
        }
    }

    public boolean isValidPage(){
        return isPage(ID);
    }

}
