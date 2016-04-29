package demand;

import util.Slave;

@SuppressWarnings("ALL")
public class onDemand {

    public static void main(String[] args) throws Exception {
        Slave slave = new Slave();
        slave.init();
        slave.rmDB();
        slave.runNumberok();
        slave.waitAppearing();
        slave.maximize();

    }

}
