package TESTS;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTests extends AbstractTest {

    @Before
    public void setUp() throws Exception {
        slave.rmDB();
        slave.runNumberok();
        Assert.assertTrue(slave.isAppear());
        slave.maximize();
//        slave.watchNumberok();
        // TODO: 15.04.2016 need to connect videos testing should performed with running videos
    }

    @After
    public void tearDown() throws Exception {
        slave.closeNumberok();
    }

    @Test
    public void testAddNumberToDatabase() throws Exception {
        slave.clickCarDB();
        slave.onCarDbPage().clickVehicles();
        slave.inVehiclesSubPage().clickAdd();
        slave.inVehiclesSubPage().typeDataIn(2, "TestNumber");
        Assert.assertTrue("Absent record in the database ", slave.inDB().isDataInCarsCorrect("number", "TestNumber","TestNumber"));
    }

    @Test
    public void testRmFromDatabase() throws Exception {
        String testNumber = "TestNumber";
        //add number
        slave.clickCarDB();
        slave.onCarDbPage().clickVehicles();
        slave.inVehiclesSubPage().clickAdd();
        slave.inVehiclesSubPage().typeDataIn(2, testNumber);
        //assert that number is present
        Assert.assertTrue("Absent record in the database ", slave.inDB().isDataInCarsCorrect("number", testNumber, testNumber));
        slave.inVehiclesSubPage().deleteNumber(testNumber);
        Assert.assertTrue("Deleted record is present in DB", !slave.inDB().isDataInCarsCorrect("number",testNumber,testNumber));
    }

    @Test
    public void testAddOwner() throws Exception {
        String owner = "its me The OWNER 123908455";
        slave.clickCarDB();
        slave.onCarDbPage().clickVehicles();
        slave.inVehiclesSubPage().clickAdd();
        slave.inVehiclesSubPage().typeDataIn(2, "NUMBER");
        slave.inVehiclesSubPage().typeDataIn(3, owner);
        //assert that number is present
        Assert.assertTrue("Absent record in the database ", slave.inDB().isDataInCarsCorrect("owner", "NUMBER", owner));
    }

    @Test
    public void testAddNote() throws Exception {
        String note = "My Note";
        slave.clickCarDB();
        slave.onCarDbPage().clickVehicles();
        slave.inVehiclesSubPage().clickAdd();
        slave.inVehiclesSubPage().typeDataIn(6, note);
        slave.inVehiclesSubPage().typeDataIn(2, "NUMBER");
        slave.inVehiclesSubPage().clickAdd();
        //assert that number is present
        Assert.assertTrue("Absent record in the database ", slave.inDB().isDataInCarsCorrect("NOTES", "NUMBER", note));
    }

    @Test
    public void testLongNote() throws Exception {
        String note = "Its my vvvvvvvvvvvvvvvvvvvvveeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrrrrryyyyyyyyyyyyyyyyyyyyyyyyyyy" +
                "llllllllllllllllllllllllllloooooooooooooooooooooooooooooooonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnggggggggggggggggggggggggggggggg" +
                "nnnnnnnnnnnnnnnnnnnnnnnnnnoooooooooooooooooooooooooootttttttttttttttttttttttteeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
        slave.clickCarDB();
        slave.onCarDbPage().clickVehicles();
        slave.inVehiclesSubPage().clickAdd();
        slave.inVehiclesSubPage().typeDataIn(6, note);
        slave.inVehiclesSubPage().typeDataIn(2, "NUMBER");
        slave.inVehiclesSubPage().clickAdd();
        //assert that number is present
        Assert.assertTrue("The long note is wrong", slave.inDB().isDataInCarsCorrect("NOTES", "NUMBER", note));
    }

    @Test
    public void testAddDescription() throws Exception {
        String desc = "THIS IS DESCRIPTION";
        slave.clickCarDB();
        slave.onCarDbPage().clickVehicles();
        slave.inVehiclesSubPage().clickAdd();
        slave.inVehiclesSubPage().typeDataIn(5, desc);
        slave.inVehiclesSubPage().typeDataIn(2, "NUMBER");
        slave.inVehiclesSubPage().clickAdd();
        //assert that number is present
        Assert.assertTrue("Absent record in the database ", slave.inDB().isDataInCarsCorrect("description", "NUMBER", desc));
    }

    @Test
    public void testAddFullNumbsData() throws Exception {
        String testNumber = "0123456";
        String note = "Its my note";
        String desc = "THIS IS DESCRIPTION";
        String owner = "its me The OWNER 123908455";
        slave.clickCarDB();
        slave.onCarDbPage().clickVehicles();
        slave.inVehiclesSubPage().clickAdd();

        slave.inVehiclesSubPage().typeDataIn(3, owner);
        slave.inVehiclesSubPage().typeDataIn(5, desc);
        slave.inVehiclesSubPage().typeDataIn(6, note);
        slave.inVehiclesSubPage().typeDataIn(2, testNumber);
        slave.inVehiclesSubPage().clickAdd();

        Assert.assertTrue("Absent record in the database ", slave.inDB().isDataInCarsCorrect("number", testNumber, testNumber));
        Assert.assertTrue("Absent record in the database ", slave.inDB().isDataInCarsCorrect("owner", testNumber, owner));
        Assert.assertTrue("Absent record in the database ", slave.inDB().isDataInCarsCorrect("description", testNumber, desc));
        Assert.assertTrue("Absent record in the database ", slave.inDB().isDataInCarsCorrect("NOTES", testNumber, note));

    }


    @Test
    public void testUpdateNumber() throws Exception {
        String testNumber = "0123456";
        String testNumber2 = "NEWNUMB";
        String note = "Its my note";
        String note2 = "Its my new note";
        String desc = "THIS IS DESCRIPTION";
        String desc2 = "THIS IS NEW DESCRIPTION";
        String owner = "its me The OWNER 123908455";
        String owner2 = "its me The OWNER numer two";

        slave.clickCarDB();
        slave.onCarDbPage().clickVehicles();
        slave.inVehiclesSubPage().clickAdd();

        slave.inVehiclesSubPage().typeDataIn(2, testNumber);
        slave.inVehiclesSubPage().typeDataIn(3, owner);
        slave.inVehiclesSubPage().typeDataIn(5, desc);
        slave.inVehiclesSubPage().typeDataIn(6, note);

        Assert.assertTrue("Absent record number in DB", slave.inDB().isDataInCarsCorrect("number",testNumber,testNumber));
        Assert.assertTrue("Absent record owner in DB", slave.inDB().isDataInCarsCorrect("owner",testNumber,owner));
        Assert.assertTrue("Absent record description in DB", slave.inDB().isDataInCarsCorrect("description",testNumber,desc));
        Assert.assertTrue("Absent record notes in DB", slave.inDB().isDataInCarsCorrect("NOTES",testNumber,desc));

        slave.inVehiclesSubPage().changeNumberData(testNumber, 2, testNumber2);
        slave.inVehiclesSubPage().changeNumberData(owner, 3, owner2);
        slave.inVehiclesSubPage().changeNumberData(desc, 5, desc2);
        slave.inVehiclesSubPage().changeNumberData(note, 6, note2);

        Assert.assertTrue("Updated number are wrong", slave.inDB().isDataInCarsCorrect("number",testNumber2,testNumber2));
        Assert.assertTrue("Updated owner are wrong", slave.inDB().isDataInCarsCorrect("owner",testNumber2,owner2));
        Assert.assertTrue("Updated description are wrong", slave.inDB().isDataInCarsCorrect("description",testNumber2,desc2));
        Assert.assertTrue("Updated notes are wrong", slave.inDB().isDataInCarsCorrect("NOTES",testNumber2,note2));

//        Assert.assertEquals("Updated record number are Wrong", testNumber2, inDB().getCarsData("number", testNumber2));
//        Assert.assertEquals("Updated record owner are Wrong", owner2, inDB().getCarsData("owner", owner2));
//        Assert.assertEquals("Updated record description are Wrong", desc2, inDB().getCarsData("description", desc2));
//        Assert.assertEquals("Updated record notes are Wrong", note2, inDB().getCarsData("NOTES", note2));
    }

//    @Test
//    public void testWarningMsgIsPresent() throws Exception {
//        clickCarDB();
//        Assert.assertTrue("Warning massage of changing groups is'nt present ", inVehiclesSubPage().isWarningMsgPresent());
//    }
}
