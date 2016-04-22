package utils;

import Pages.*;
import Pages.DBSubPage.*;
import Pages.ResultsSubPages.*;
import Pages.SettingsSubPages.*;
import org.sikuli.script.App;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Slave extends MainController {

    private Process numberok;
//pages
    private ViewPage viewPage;
    private ResultsPage resultsPage;
    private CarDBPage carDBPage;
    private ReportsPage reportsPage;

    private SettingsPage settingsPage;
    private GeneralSubPage generalSubPage;
    private ConnSubPage connSubPage;
    private CheckpointSubPage cp;
    private ParkingSubPage parkingSubPage;
    private IntegrationSubPage integrationSubPage;
    private UsersSubPage userSubPage;

    private RecognitionResults recognitionResults;
    private GroupedResults groupedResults;
    private VehiclesSubPage vehiclesSubPage;


    public Slave init() {
        viewPage = new ViewPage();
        resultsPage = new ResultsPage();
        carDBPage = new CarDBPage();
        vehiclesSubPage = new VehiclesSubPage();
        reportsPage = new ReportsPage();
        settingsPage = new SettingsPage();
        generalSubPage = new GeneralSubPage();
        connSubPage = new ConnSubPage();
        cp = new CheckpointSubPage();
        userSubPage = new UsersSubPage();
        integrationSubPage = new IntegrationSubPage();
        recognitionResults = new RecognitionResults();
        groupedResults = new GroupedResults();
        parkingSubPage = new ParkingSubPage();
        return this;
    }

    public void runNumberok() {
        closeNumberok();
        try {
            numberok = Runtime.getRuntime().exec(Props.get("numberok.exe"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DBWorker inDB() {
        return new DBWorker();
    }

    public ResultsPage onResultsPage() {
        return resultsPage;
    }

    public CarDBPage onCarDbPage() {
        return carDBPage;
    }

    public VehiclesSubPage inVehiclesSubPage() {
        return vehiclesSubPage;
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

    public GroupedResults inGropedResults() {
        return groupedResults;
    }

    public RecognitionResults inRecognitionResults() {
        return recognitionResults;
    }

    public UsersSubPage inUsersSubPage() {
        return userSubPage;
    }

    public IntegrationSubPage inIntegrationSubPage (){
        return integrationSubPage;
    }

    public ParkingSubPage inParkingSettingsPage (){
        return parkingSubPage;
    }

    public void closeNumberok() {
        App.close("NumberOk3.exe");
        if (numberok!=null &&numberok.isAlive()) {
            System.out.println(" Closing numberok ");
            numberok.destroy();
        } else System.out.println("Numberok is closed");
    }

    /**
     * @param channels насколько каналов вставлять чистый конфиг ?
     *                 <br> пока что только 2 и 9
     */

    public void pasteCleanIni(int channels) {
        try {
            FileChannel source = new FileInputStream(Props.get("Clean.ini") + "\\" + channels + "\\" + "NumberOk.ini").getChannel();
            FileChannel destination;
            destination = new FileOutputStream(Props.get("numberokData.path") + "NumberOk.ini").getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
