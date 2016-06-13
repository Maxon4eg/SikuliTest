package tests.someTests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.AbstractTest;
import util.Props;

public class ConfigTests extends AbstractTest {

    private int channels = 2;


    @BeforeMethod
    public void setUp() throws Exception {
        slave.pasteCleanIni(channels);
        slave.rmDB();
        slave.runNumberok();
        Assert.assertTrue(slave.waitAppearing());
        slave.maximize();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        slave.closeNumberok();
    }

    /**
     * Включим два канала
     * подключим видео
     * настроим зону распознавания
     * На вкладке результаты распознавания
     * убеждаемся в том что появляются 100 номеров
     * и убеждаемся в том что номера продолжают появлятся
     * todo добавь метод который будет скролить вниз результаты и убеждатся в том что внизу присутствует номер 100
     */

    public void connect2Chanels() throws Exception {
        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        slave.inConnSubPage().enableZone();
        Assert.assertTrue(slave.inConnSubPage().isVideoAppear(), "Is zone enabled ");
        slave.inConnSubPage()
                .chooseConn(3)
                .typeConn(3, Props.get("pl.video"))
                .setDirAngle(90)
                .moveTopRight(0, 50)
                .moveTopLeft(0, 50)
                .moveBottomRight(0, -50)
                .moveBottomLeft(0, -50)
                .clickConnect();
        Assert.assertTrue(slave.inConnSubPage().isVideoAppear(), "Is video appeared ");

        slave.inConnSubPage()
                .switchVidSource(2)
                .enableZone();
        Assert.assertTrue(slave.inConnSubPage().isVideoAppear(), "Is zone enabled ");
        slave.inConnSubPage()
                .chooseConn(3)
                .typeConn(3, Props.get("test.video"))
                .setDirAngle(90)
                .moveTopRight(0, 50)
                .moveTopLeft(0, 50)
                .moveBottomRight(0, -50)
                .moveBottomLeft(0, -50)
                .clickConnect();
        Assert.assertTrue(slave.inConnSubPage().isVideoAppear(), "Is video appeared ");

        slave.inConnSubPage().clickApply();

        slave.clickResults();
        slave.onResultsPage().clickRecognitionResults();

    }

    /**
     * Тест при строгом сравнении
     * включить 2 канала
     * включить распознавание на обеих каналах
     * ждать появления 100 результатов
     * после чего удостоверится что номера продолжают появлятся
     */
    @Test
    public void test100ResultsInRecogMode() throws Exception {
        System.out.println(" ==================== Test Description ======================   " +
                "     * Включим два канала\n" +
                "     * подключим видео\n" +
                "     * настроим зону распознавания\n" +
                "     * На вкладке результаты распознавания\n" +
                "     * убеждаемся в том что появляются 100 номеров\n" +
                "     * и убеждаемся в том что номера продолжают появлятся");
        connect2Chanels();
        Assert.assertTrue(slave.inRecognitionResults().isChanges(200), "numbers are not changed ");
        System.out.println("NumberOks memory usage is : " + slave.memUsage() + " Kb");
    }

    /**
     * ПРИ МЯГКОМ СРАВНЕНИИ
     * Включить 2 канала
     * включить распознавание на обеих каналах
     * ждать появления ста результатов.
     * после чего удостоверится что номера продолжают появлятся
     */

    @Test
    public void testSoft100Results() throws Exception {
        System.out.println(
                "\n   ПРИ МЯГКОМ СРАВНЕНИИ\n" +
                        "     Включить 2 канала\n" +
                        "     включить распознавание на обеих каналах\n" +
                        "     ждать появления ста результатов.\n" +
                        "     после чего удостоверится что номера продолжают появлятся" +
                        "\n======================================================================");

        System.out.println("testing with soft comparison in recognition mode ");
        slave.clickSettings();
        slave.inGeneralSubPage()
                .setComparisonMode(2)
                .clickApply();
        slave.clickView();
        connect2Chanels();
        Assert.assertTrue(slave.inRecognitionResults().isChanges(200), "numbers are not changed ");
        System.out.println("NumberOks memory usage is : " + slave.memUsage() + " Kb");
    }

    /**
     * Тест при строгом сравнении в режиме КПП
     * Тест на появление 100 результатов
     */

    @Test
    public void strongTest100CPMode() throws Exception {
        System.out.println("=================== Test Description ===================" +
                "    \n" +
                "     * Тест при строгом сравнении в режиме КПП\n" +
                "     * Тест на появление 100 результатов\n");

        slave.clickSettings();
        slave.inGeneralSubPage()
                .setOperationMode(2)
                .clickApply();
        slave.clickView();
        connect2Chanels();
        Assert.assertTrue(slave.inRecognitionResults().isChanges(200), "numbers are not changed ");
        System.out.println("NumberOks memory usage is : " + slave.memUsage() + " Kb");
    }


    /**
     * Тест при строгом сравнении в режиме парковки
     * Тест на появление 100 результатов
     */

    @Test
    public void strongTest100ParkMode() throws Exception {
        System.out.println("============== Test Description ================" +
                "    \n" +
                "     * Тест при строгом сравнении в режиме парковки\n" +
                "     * Тест на появление 100 результатов\n");

        slave.clickSettings();
        slave.inGeneralSubPage()
                .setOperationMode(3)
                .clickApply();
        slave.clickView();
        connect2Chanels();
        Assert.assertTrue(slave.inRecognitionResults().isChanges(200), "numbers are not changed ");
        System.out.println("NumberOks memory usage is : " + slave.memUsage() + " Kb");
    }

    /**
     * Тест при мягком сравнении в режиме Парковки
     * Тест на появление более 100 результатов
     * при подключенных 2-х каналов
     */

    @Test
    public void softTest100ParkMode() throws Exception {
        System.out.println("================= Test Description =================" +
                "     * Тест при мягком сравнении в режиме Парковки\n" +
                "     * Тест на появление более 100 результатов\n" +
                "     * при подключенных 2-х каналов\n");

        slave.clickSettings();
        slave.inGeneralSubPage()
                .setComparisonMode(2)
                .setOperationMode(3)
                .clickApply();
        slave.clickView();
        connect2Chanels();
        Assert.assertTrue(slave.inRecognitionResults().isChanges(200), "numbers are not changed ");
        System.out.println("NumberOks memory usage is : " + slave.memUsage() + " Kb");
    }


    @Test
    public void testWithHighRecognitionSensitivity() throws Exception {
        System.out.println("Testing with high recognition sensitivity");
        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        slave.inConnSubPage()
                .chooseConn(3)
                .typeConn(3, Props.get("test.video"))
                .enableZone();
        Assert.assertTrue(slave.inConnSubPage().isVideoAppear(), "Zone is'nt appeared "); // check is zone appear
        slave.inConnSubPage()
                .setDirAngle(66)
                .setSensitivity(130)
                .moveTopRight(0, 50)
                .moveTopLeft(0, 50)
                .moveBottomRight(0, -50)
                .moveBottomLeft(0, -50)
                .clickConnect()
                .clickApply();
        slave.clickResults();
        slave.onResultsPage().clickRecognitionResults();
        slave.inRecognitionResults().isChanges(5);
    }


    @Test
    public void cpStress() throws Exception {
        System.out.println();
        System.out.println("CPStress test");
        slave.clickSettings();
        slave.onSettingsPage().clickConnection();
        enableZones();
        enableCP(2);
        Assert.assertTrue(!slave.waitVanish(600), "Numberok is Vanished probably felt"); // waiting wanish 10 minutes
        int memUsage = slave.memUsage();
        System.out.println("Memory usage is : " + memUsage + " kb ");
        Assert.assertTrue(memUsage < 500000, "Memory usage is higher than 500 000 kb");
    }

    /**
     * Тест при мягком сравнении в режиме КПП
     * Тест на появление более 100 результатов
     * при подключенных 2-х каналов
     */

    @Test
    public void softTest100CPMode() throws Exception {
        System.out.println("================= Test Description =================" +
                "     * \n Тест при мягком сравнении в режиме КПП\n" +
                "     * Тест на появление 100 результатов\n" +
                "     * при подключенных 2-х каналов");

        System.out.println("Testing with soft comparison in CP mode");
        slave.clickSettings();
        slave.inGeneralSubPage()
                .setComparisonMode(2)
                .setOperationMode(2)
                .clickApply();
        slave.clickView();
        connect2Chanels();
        Assert.assertTrue(slave.inRecognitionResults().isChanges(200), "numbers are not changed ");
        System.out.println("NumberOks memory usage is : " + slave.memUsage() + " Kb");
    }

    /**
     * Methods to create zones and cps
     */
    public void enableZones() throws Exception {
        slave.inConnSubPage()
                .chooseConn(3)
                .typeConn(3, Props.get("pl.video"))
                .enableZone()
                .setDirAngle(70)
                .moveTopRight(0, 300)
                .moveTopLeft(0, 300)
                .moveBottomRight(0, -50)
                .moveBottomLeft(0, -50)
                .switchZone(2)
                .enableZone()
                .setDirAngle(70)
                .clickConnect()
                .clickApply();
        slave.onSettingsPage().clickGeneral();//return back in gui
        slave.clickView();
    }

    public void enableCP(int comparisonMode) throws Exception {
        slave.clickSettings();
        slave.inGeneralSubPage()
                .setOperationMode(2)
                .setComparisonMode(comparisonMode)
                .clickApply();
        slave.onSettingsPage().clickCheckpoint();
        slave.inCheckpointSubPage()
                .cpEntry(1, 0, 1)
                .cpExit(1, 0, 1)
                .setPassageDetermination(2)
                .clickApply();
        slave.onSettingsPage().clickGeneral();//return back in gui
        slave.clickView();
    }
}
