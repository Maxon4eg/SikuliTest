package tests;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DatabaseTests extends AbstractTest {

    @BeforeMethod
    public void setUp() throws Exception {
        slave.runNumberok();
        Assert.assertTrue(slave.waitAppearing());
        slave.maximize();
//        slave.watchNumberok();
        // TODO: 15.04.2016 need to connect videos testing should performed with running videos
    }

    @AfterMethod
    public void tearDown() throws Exception {
        slave.closeNumberok();
        Thread.sleep(500);
        slave.rmDB();

    }

    @Test
    public void testAddNumberToDatabase() throws Exception {
        slave.clickCarDB();
        slave.onCarDbPage().clickVehicles();
        slave.inVehiclesSubPage().clickAdd();
        slave.inVehiclesSubPage().typeDataIn(2, "TestNumber");
        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("number", "TestNumber","TestNumber"), "Absent record in the database ");
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
        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("number", testNumber, testNumber), "Absent record in the database ");
        slave.inVehiclesSubPage().deleteNumber(testNumber);
        Assert.assertTrue(!slave.inDB().isDataInCarsCorrect("number",testNumber,testNumber), "Deleted record is present in DB");
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
        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("owner", "NUMBER", owner), "Absent record in the database ");
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
        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("NOTES", "NUMBER", note), "Absent record in the database ");
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
        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("description", "NUMBER", desc), "Absent record in the database ");
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

        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("number", testNumber, testNumber), "Absent record in the database ");
        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("owner", testNumber, owner), "Absent record in the database ");
        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("description", testNumber, desc), "Absent record in the database ");
        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("NOTES", testNumber, note), "Absent record in the database ");

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

        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("number",testNumber,testNumber), "Absent record number in DB");
        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("owner",testNumber,owner), "Absent record owner in DB");
        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("description",testNumber,desc), "Absent record description in DB");
        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("NOTES",testNumber,desc), "Absent record notes in DB");

        slave.inVehiclesSubPage().changeNumberData(testNumber, 2, testNumber2);
        slave.inVehiclesSubPage().changeNumberData(owner, 3, owner2);
        slave.inVehiclesSubPage().changeNumberData(desc, 5, desc2);
        slave.inVehiclesSubPage().changeNumberData(note, 6, note2);

        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("number",testNumber2,testNumber2), "Updated number are wrong");
        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("owner",testNumber2,owner2), "Updated owner are wrong");
        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("description",testNumber2,desc2), "Updated description are wrong");
        Assert.assertTrue(slave.inDB().isDataInCarsCorrect("NOTES",testNumber2,note2), "Updated notes are wrong");

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
