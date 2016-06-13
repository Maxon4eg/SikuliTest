package demand;


import util.PlateNumber;
import util.Slave;


@SuppressWarnings("ALL")
public class onDemand {

    public static void main(String[] args) throws Exception {
        Slave slave = new Slave();
        PlateNumber number;
        slave.init();
        slave.inVehiclesSubPage().clickAdd();

         number = slave.inVehiclesSubPage().createNumber("Mynumber");

        slave.inVehiclesSubPage().setOwner(number,"owner").setDescr(number,"description");

//        Screen screen = new Screen();
//        Region region = screen.selectRegion();
//        ScreenImage screenImage = region.getLastScreenImage();
//        BufferedImage bi = screenImage.getImage();
//        System.out.println(screenImage.getFile()+ " the path of image ");
//        System.out.println(screenImage.getROI()+ " the roi of image ");
//        Pattern tempPattern = new Pattern(bi);
//        screen.find(tempPattern).highlight(2);




    }
}
