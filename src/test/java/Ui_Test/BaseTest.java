package Ui_Test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import utils.Props;

import java.io.IOException;

/**
 * Created by DespicableMe on 26.02.2016.
 * Описание:
 */
public class BaseTest {
    private static Process numberok;


    @BeforeClass
    public static void run() {
        try {
            numberok = Runtime.getRuntime().exec(Props.get("NumberOK.exe"));
            System.out.println("Numberok Started");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDown() {
//        numberok.destroy();
        System.out.println("Numberok destroyed :)");
    }



}
