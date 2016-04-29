package tests;

import org.sikuli.basics.Settings;
import org.testng.annotations.BeforeClass;
import util.Slave;


public abstract class AbstractTest {

    public static Slave slave;

    @BeforeClass
    public static void prepare() {
        Settings.ActionLogs = false;
        Settings.InfoLogs = false;
        Settings.DebugLogs = false;
        Settings.ProfileLogs = false;
        Settings.UserLogs = false;
        Settings.MoveMouseDelay = (float) 0.3;
        slave = new Slave();
        slave.init();
        System.out.println("Preparing is successful. Have fun ! \n");
    }

    abstract public void setUp() throws Exception;

    abstract public void tearDown() throws Exception;
}
