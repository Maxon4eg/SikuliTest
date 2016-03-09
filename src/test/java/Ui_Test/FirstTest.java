package Ui_Test;

import Ui_Test.SettingsSubPages.ConnSubPage;
import org.junit.*;
import utils.Props;


/**
 * Created by DespicableMe on 26.02.2016.
 * Описание:
 */
public class FirstTest extends ConnSubPage {

//    @Test
    public void a(){
        runNumberok();
        isAppear();
        maximize();
        int channels = clickView().howManyChannels();
        System.out.println("Counted "+channels+" channels");
        Assert.assertEquals("Wrong channels",9,channels);
    }


//    @Test
    public void b () {
        clickSettings();
        clickConnection();
        switchVidSource(6);
        chooseConn(3);
        typeConn(3,Props.getProperty("video.path"));
        clickConnect();
        Assert.assertTrue(isVideoAppear());
        switchVidSource(1);
        Assert.assertTrue(clickView().checkChan(6));
    }

//    @Test
    public void c () {
        clickSettings();
        clickGeneral()
                .setOperationMode(3);
        clickApply();
        clickView();

    }
//    @Test
    public void d (){
        clickSettings();
        clickConnection();
        chooseConn(3);
        typeConn(3, Props.getProperty("video.path"));
        clickConnect();
        clickApply();
        Assert.assertTrue("Video isn't appeared ",isVideoAppear());
        Assert.assertTrue("Video isn't appeared in view page ",clickView().checkChan(1));
    }




//    @Test
    public void e () {
        System.out.println("car db is state switched = "+clickCarDB().isStateSwitched());
        System.out.println("Results is state switched = "+clickResults().isStateSwitched());
        System.out.println("repots is state switched = "+clickReports().isStateSwitched());
        System.out.println("Settings is state switched = "+clickSettings().isStateSwitched());
        System.out.println("General  is state switched "+clickGeneral().isStateSwitched());
        System.out.println("Connection  "+clickConnection().isStateSwitched());
        System.out.println("Users "+clickUsers().isStateSwitched());
        System.out.println("Integration "+clickIntegration().isStateSwitched());
        System.out.println("Second general  "+clickGeneral().isStateSwitched());
        onGeneral().setOperationMode(3).clickApply();
        System.out.println("Checkpoint ="+ clickCheckpoint().isStateSwitched());
        System.out.println("Parking ="+ clickParking().isStateSwitched());
        System.out.println("Integration after parking ="+ clickIntegration().isStateSwitched());
        System.out.println("Users after parking ="+ clickIntegration().isStateSwitched());


    }

    @Test
    public void test(){
//        enableZone();
        setDirAngle(100);
        setSensitivity(200);

    }


}