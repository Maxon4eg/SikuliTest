package Ui_Test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Created by DespicableMe on 29.02.2016.
 * Описание:
 */
public class BaseTest extends AbstractPage  {

    @BeforeClass
    public static void run() {
        AbstractPage.runNumberok();
        System.out.println("\n============Numberok Started============ \n");
    }

    @AfterClass
    public static void tearDown() {
//        AbstractPage.destroy();
        System.out.println("\n===========Numberok destroyed :)=========\n");
    }
}
