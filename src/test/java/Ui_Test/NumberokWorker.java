package Ui_Test;

import Ui_Test.SettingsSubPages.*;
import org.junit.BeforeClass;
import org.sikuli.basics.Settings;
import utils.Props;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


public class NumberokWorker extends MainController {

    private static ViewPage viewPage;
    private static ResultsPage resultsPage;
    private static CarDBPage carDBPage;
    private static ReportsPage reportsPage;
    private static SettingsPage settingsPage;
    private static GeneralSubPage generalSubPage;
    private static ConnSubPage connSubPage;
    private static CheckpointSubPage cp;

    @BeforeClass
    public static void prepare() {
        Settings.ActionLogs= false ;
        viewPage = new ViewPage();
        resultsPage = new ResultsPage();
        carDBPage = new CarDBPage();
        reportsPage = new ReportsPage();
        settingsPage = new SettingsPage();
        generalSubPage = new GeneralSubPage();
        connSubPage = new ConnSubPage();
        cp = new CheckpointSubPage();
        System.out.println("Preparing is successful. Have fun ! \n");
    }

    public static Process runNumberok() {
        try {
            return Runtime.getRuntime().exec(Props.getProperty("NumberOK.exe"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultsPage onResultsPage() {
        return resultsPage;
    }

    public CarDBPage onCarDbPage() {
        return carDBPage;
    }

    public ReportsPage onReportsPage() {
        return reportsPage;
    }


    public CheckpointSubPage inCheckpointSubPage() {
        return cp;
    }


    public ViewPage onViewPage() {
        return viewPage;
    }

    public ConnSubPage inConnSubPage() {
        return connSubPage;
    }

    public SettingsPage onSettingsPage() {
        return settingsPage;
    }

    public GeneralSubPage inGeneralSubPage() {
        return generalSubPage;
    }

    /**
     *
     * @param channels насколько каналов вставлять чистый конфиг ?
     *                 <br> пока что только 2 и 9
     */

    public void pasteCleanIni(int channels) {
        try {
            FileChannel source = new FileInputStream(Props.getProperty("Clean.ini")+"\\"+channels +"\\" + "NumberOk.ini").getChannel();
            FileChannel destination;
            destination = new FileOutputStream(Props.getProperty("numberokData.path") + "NumberOk.ini").getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
