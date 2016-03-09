package Ui_Test.SettingsSubPages;

import Ui_Test.SettingsPage;
import org.junit.Assert;
import org.sikuli.script.*;
import utils.ButtonUtil;
import utils.Props;

import java.util.concurrent.TimeUnit;

/**
 * Created by DespicableMe on 29.02.2016.
 * Описание:
 */
public class ConnSubPage extends SettingsPage {


    private Location rtsp;
    private Location camDvr;
    private Location video;
    private boolean memorized = false;

    private ButtonUtil source1;
    private ButtonUtil source2;
    private ButtonUtil source3;
    private ButtonUtil source4;
    private ButtonUtil source5;
    private ButtonUtil source6;
    private ButtonUtil source7;
    private ButtonUtil source8;
    private ButtonUtil source9;
    private Region recognitionBlock;

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
     * НЕ ЗАБУДЬ СНАЧАЛА поставить радиобаттон chooseConn()
     *
     * @param conn 1 - RTSP Connection
     *             <br>2 - Что бы подключить камеру смотри tyopeConn(int,String,String,String)
     *             <br>3 - path to video
     * @param data path to video OR rtsp connection adress
     */
    public ConnSubPage typeConn(int conn, String data) {

        try {
            switch (conn) {
                case 1:
                    screen.click(rtsp.offset(20, 0));
                    screen.type(data);
                    return this;
                case 2:
                    System.out.println("Please enter correct data");
                    return this;
                case 3:
                    screen.click(video.offset(20, 0));
                    screen.type(data);
                    return this;
                default:
                    return this;
            }
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return this;
    }

    /**
     * НЕ ЗАБУДЬ СНАЧАЛА поставить радиобаттон chooseConn()
     * Прегруженный метод для заполнения данных в поле cam/DVR
     *
     * @param dropdown выбрать тип камеры по факту количество раз нажать на стрелку вниз
     * @param login    ввести логин
     * @param pass     ввести пароль
     * @param adress   ввести драесс
     */

    public ConnSubPage typeConn(int dropdown, String login, String pass, String adress) {

        try {
            screen.doubleClick(camDvr.offset(15, 0));
        } catch (FindFailed findFailed) {
            Assert.assertTrue(findFailed.getLocalizedMessage(), false);
        }

        for (int i = 0; i < dropdown; ++i) {
            System.out.println("I'm workiing");
            screen.type(Key.DOWN);
        }
        screen.type(Key.ENTER);
        try {
            screen.click(Props.getPathForRun("CamDVR_Login_ConnSubPage.png"));
            screen.type(login);
            screen.click(Props.getPathForRun("CamDVR_Pass_ConnSubPage.png"));
            screen.type(pass);
            screen.click(Props.getPathForRun("CamDVR_Adress_ConnSubPage.png"));
            screen.type(adress);
        } catch (FindFailed findFailed) {
            Assert.assertTrue(findFailed.getLocalizedMessage(), false);
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
            if (rgb == Props.getInt("RGB.Gray")) return false;
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
     * @param source Номер канала. Указывай аккуратно потому что я не знаю на сколько каналов номерок !
     *               1 - первый канал
     *               2 - второй
     *               и так далее
     */

    public ConnSubPage switchVidSource(int source) {
        if (!memorized) {
            memoryButtonsLoc();
        }
        try {
            switch (source) {
                case 1:
                    screen.click(source1.getLocation());
                    break;
                case 2:
                    screen.click(source2.getLocation());
                    break;
                case 3:
                    screen.click(source3.getLocation());
                    break;
                case 4:
                    screen.click(source4.getLocation());
                    break;
                case 5:
                    screen.click(source5.getLocation());
                    break;
                case 6:
                    screen.click(source6.getLocation());
                    break;
                case 7:
                    screen.click(source7.getLocation());
                    break;
                case 8:
                    screen.click(source8.getLocation());
                    break;
                case 9:
                    screen.click(source9.getLocation());
                    break;
                default:
                    System.out.println("please choose from 1 - 9 ");
                    break;
            }
        } catch (FindFailed findFailed) {
            Assert.assertTrue(findFailed.getLocalizedMessage(), false);
        }
        return this;
    }

    private void memoryButtonsLoc() {
        try {
            source1 = new ButtonUtil(screen, Props.getPathForRun("Source1_ConnSubPage.png"));
            source2 = new ButtonUtil(screen, Props.getPathForRun("Source2_ConnSubPage.png"));
            source3 = new ButtonUtil(screen, Props.getPathForRun("Source3_ConnSubPage.png"));
            source4 = new ButtonUtil(screen, Props.getPathForRun("Source4_ConnSubPage.png"));
            source5 = new ButtonUtil(screen, Props.getPathForRun("Source5_ConnSubPage.png"));
            source6 = new ButtonUtil(screen, Props.getPathForRun("Source6_ConnSubPage.png"));
            source7 = new ButtonUtil(screen, Props.getPathForRun("Source7_ConnSubPage.png"));
            source8 = new ButtonUtil(screen, Props.getPathForRun("Source8_ConnSubPage.png"));
            source9 = new ButtonUtil(screen, Props.getPathForRun("Source9_ConnSubPage.png"));
            memorized = true;
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    private void findRSBlock() {
        try {
            recognitionBlock = screen.find(Props.getPathForRun("RSBlock_Region_ConnSubPage.png"));
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
    }

    public void enableZone() {

        if (recognitionBlock == null) {
            findRSBlock();
        }
        try {
            recognitionBlock.click(Props.getPathForRun("CheckBox.png"));
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
    }

    /**(+)ВПРАВО ,(-)ВЛЕВО
     * @param angle - на сколько двигаем ползунок вправо (разница с выставляемым углом примерно в 20 и больше)
     *              <br> !!! Если мы поставим 100 то угол у нас поменяется на  131
     *              <br> если поставим 50 то угол поменяется на 70
     *              <br> пока что лучше ничего нет :*(
     */

    public void setDirAngle(int angle) {
        Region dirAngle = null;
        Pattern dragger = new Pattern(Props.getPathForRun("Dragger_ConnSubPage.png"));
        if (recognitionBlock == null) {
            findRSBlock();
        }
        try {
            dirAngle = recognitionBlock.find(Props.getPathForRun("DirAngle_ConnSubPage.png"));
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }

        if (dirAngle != null) {
            try {
                dirAngle.drag(dragger);
                dirAngle.dropAt(dragger.targetOffset(angle, 0));
            } catch (FindFailed findFailed) {
                System.out.println(findFailed.getLocalizedMessage());
            }
        }
    }

    /**
     * Ставь не больше  около 170
     *
     * @param sensitivity насколько двигаем ползунок (+)ВПРАВО ,(-)ВЛЕВО
     *                    <br> !!! Если мы поставим 100 то у нас поменяется на  131 проц
     *                    <br> если поставим 50 то поменяется на 70
     *                    <br> пока что лучше ничего нет :*(
     */

    public void setSensitivity(int sensitivity) {
        Pattern dragger = new Pattern(Props.getPathForRun("Dragger_ConnSubPage.png"));//нужно пересоздавать переменную Глобальную не получится
        if (recognitionBlock == null) {
            findRSBlock();
        }
        try {
            Region sensBlock = recognitionBlock.find(Props.getPathForRun("Sensivity_ConnSubPage.png"));
            sensBlock.drag(dragger);
            sensBlock.dropAt(dragger.targetOffset(sensitivity, 0));
        } catch (FindFailed findFailed) {
            System.out.println(findFailed.getLocalizedMessage());
        }
    }
}
