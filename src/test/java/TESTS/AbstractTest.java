package TESTS;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.sikuli.basics.Settings;
import utils.Slave;


public abstract class AbstractTest {

    static Slave slave;

    @BeforeClass
    public static void prepare() {
//        Settings.ActionLogs = true;
        slave = new Slave();
        slave.init();
        System.out.println("Preparing is successful. Have fun ! \n");
    }

    abstract public void setUp() throws Exception;

    abstract public void tearDown() throws Exception;
}
