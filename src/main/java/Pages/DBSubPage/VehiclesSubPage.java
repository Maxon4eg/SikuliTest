package Pages.DBSubPage;

import Pages.CarDBPage;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import util.PlateNumber;
import util.Props;

import java.awt.*;
import java.awt.image.BufferedImage;

public class VehiclesSubPage extends CarDBPage {
    private final Pattern ID = new Pattern(Props.pathForRun("VehiclesSubPage_Ident.png"));


    public PlateNumber createNumber(String number) throws FindFailed {
        Pattern defaultPlate = new Pattern(Props.pathForRun("DefaultPlate_VehiclesSubPage.png"));
        BufferedImage img = null;
        Rectangle rectangle;
        try {
            rectangle = screen.find(defaultPlate.exact()).getRect();
            screen.doubleClick(defaultPlate);
            screen.type(number);
            screen.mouseMove(-300, -100);
            screen.type(Key.ENTER);
            rectangle.grow(-10, 1);
            img = screen.capture(rectangle).getImage();

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        if (img != null) {
            return new PlateNumber(img,number);
        } else throw new FindFailed("Failed to create Plate Number Obj");

    }

    public void createNumber (PlateNumber number){
        try {
            screen.doubleClick(Props.pathForRun("DefaultPlate_VehiclesSubPage.png"));
            screen.type(number.getName());
            screen.type(Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }



    public VehiclesSubPage setOwner (PlateNumber number , String data ){
        try {
            screen.doubleClick(number.getPattern().targetOffset(200,0));
            screen.type(data);
            screen.type(Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public VehiclesSubPage setGroup (PlateNumber number , String group ){
        try {
            screen.doubleClick(number.getPattern().targetOffset(400,0));
            screen.type(group);
            screen.type(Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public VehiclesSubPage setDescr (PlateNumber number , String desctription ){
        try {
            screen.doubleClick(number.getPattern().targetOffset(600,0));
            screen.type(desctription);
            screen.type(Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
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
            clearFilter(2);
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


    public boolean isValidPage() {
        return isPage(ID);
    }

}
