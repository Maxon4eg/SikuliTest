package demand;

import TESTS.AbstractTest;
import org.junit.Assert;
import org.sikuli.script.Key;
import utils.Props;
import utils.Slave;

@SuppressWarnings("ALL")
public class onDemand {
    private static int channels = 2;

    public static void main(String[] args) {
//        Slave slave = new Slave();
//        slave.init();
//        ;
//
//        try {
//            checkZonesAnsChannels(slave);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

//
//    private static void checkZonesAnsChannels(Slave slave) throws Exception {
//        channels += 1;
//
//        for (int i = 1; i < channels; i++) {
//            slave.inConnSubPage().switchVidSource(i);
//            slave.inConnSubPage()
//                    .chooseConn(3)
//                    .typeConn(3, Props.get("pl.video"));
//            for (int j = 1; j < 5; j++) {
//                slave.inConnSubPage().switchZone(j).enableZone();
//                System.out.println(" the  j is " + j);
//                configureZone(slave);
//                slave.inConnSubPage()
//                        .clickConnect()
//                        .clickApply();
//                slave.clickResults();
//                Assert.assertTrue("the result is'nt appear ", slave.inRecognitionResults().isChanges(1, 1));
//                slave.clickSettings();
//                slave.inConnSubPage()
//                        .typeConn(3, Key.BACKSPACE)
//                        .clickConnect()
//                        .enableZone()
//                        .typeConn(3, "i")
//                        .clickApply();
//            }
//        }
//    }
//
//    private static void configureZone(Slave slave) {
//        slave.inConnSubPage()
//                .moveTopRight(0, 50)
//                .moveTopLeft(0, 50)
//                .moveBottomRight(0, -50)
//                .moveBottomLeft(0, -50);
//    }

}
