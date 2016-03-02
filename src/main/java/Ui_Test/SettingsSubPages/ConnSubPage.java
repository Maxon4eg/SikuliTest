package Ui_Test.SettingsSubPages;

import org.sikuli.script.*;
import utils.Props;
import utils.SourcesButton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by DespicableMe on 29.02.2016.
 * Описание:
 */
public class ConnSubPage extends SettingsPage {


    private Location rtsp;
    private Location camDvr;
    private Location video;
    private Region region;
    private ArrayList<SourcesButton> buttons;

    public ConnSubPage(Screen screen) {
        setScreen(screen);
    }


    /**
     * @param conn 1 - RTSP Connection
     *             2 - Camera\DVR connection
     *             3 - path to video
     */
    public ConnSubPage chooseConn(int conn) {
        Pattern connection;
        try {
            switch (conn) {

                case 1:
                    connection = new Pattern(Props.getPathForRun("RTSP_Conn.png"));
                    rtsp = screen.find(connection).getTarget();
                    screen.click(connection.targetOffset(-25, 0));
                    return this;
                case 2:
                    connection = new Pattern(Props.getPathForRun("CameraDVR_ConnSubPage.png"));
                    camDvr = screen.find(connection).getTarget();
                    screen.click(connection.targetOffset(-15, 0));
                    return this;
                case 3:
                    connection = new Pattern(Props.getPathForRun("VideoConnect_ConnSubPage.png"));
                    video = screen.find(connection).getTarget();
                    screen.click(connection.targetOffset(-15, 0));
                    return this;
                default:
                    System.out.println("please choose from 1 - 3 ");
            }
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }


    /**
     * @param conn 1 - RTSP Connection
     *             2 - Camera\DVR connection
     *             3 - path to video
     */
    public ConnSubPage typeConn(int conn, String data) {

        try {
            switch (conn) {
                case 1:
                    screen.click(rtsp.offset(20, 0));
                    screen.type(data);
                    return this;
                case 2:
                    screen.click(camDvr.offset(20, 0));
                    screen.type(data);
                    return this;
                case 3:
                    screen.click(video.offset(20, 0));
                    screen.type(data);
                    return this;
            }
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public ConnSubPage clickConnect() {
        Pattern button = new Pattern(Props.getPathForRun("Connect_Button_ConnSubPage.png"));

        try {
            screen.click(button);
            return this;
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    public boolean isVideoAppear() {
        try {
            TimeUnit.SECONDS.sleep(1);
            Region region = screen.find(Props.getPathForRun("RSBlock_Region_ConnSubPage.png"));
            int rgb = region.rightAt(80).getColor().getRGB();
            if (rgb == Props.getInt("Gray")) return false;
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param source номер канала ( счет каналов начинается с нуля)
     */

    public ConnSubPage switchVidSource(int source) {
        try {
            if (!sources(region)) {
                region = screen.find(new Pattern(Props.getPathForRun("SourcesRegion_ConnSubPage.png")));
            } else region.click(buttons.get(source).getLocation());
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        return this;
    }

    private boolean sources(Region region) {
        Pattern firsSource = new Pattern(new Pattern(Props.getPathForRun("VideoSource1_ConnSubPage.png")));
        Pattern sources = new Pattern(Props.getPathForRun("VideoSourceNA_ConnSubPage.png"));

        buttons = new ArrayList<SourcesButton>();
        try {
            //find First Button
            buttons.add(new SourcesButton(region.find(firsSource))); //add new obj to arr

            Iterator<Match> match = region.findAll(sources);//find all sources
            while (match.hasNext()) {
                buttons.add(new SourcesButton(match.next()));//adding matches to arr
            }
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return true;
    }
}
