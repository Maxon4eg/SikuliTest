package Pages;


import org.junit.Assert;
import org.sikuli.script.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import utils.Props;

/**
 * Created by DespicableMe on 22.02.2016.
 * Описание:
 */
abstract class AbstractPage {

    protected static Screen screen = new Screen();
    private Thread toucher;

    protected boolean focus() {
        Pattern focused = new Pattern(Props.pathForRun("_Focused.png"));
        App.focus("NumberOk");
        try {
            screen.find(focused);
            return true;
        } catch (FindFailed findFailed) {
            App.focus("NumberOk", 1);
            try {
                screen.find(focused);
                return true;
            } catch (FindFailed findFailed1) {
                return false;
            }
        }
    }

    /**
     * seems works fine
     *
     * @return возвращаем сколько сожирает номерок КБ
     */

    public int memUsage() {
        int usage;
        try {
            Process p = Runtime.getRuntime().exec("TASKLIST /FI \"IMAGENAME eq  NumberOk3.exe\" /FO CSV /NH");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String inLineResult = in.readLine();
            if (inLineResult.contains("No tasks")) {
                return -1;
            }
            String[] strings = inLineResult.split(",");// сплитим через запятую
            usage = Integer.parseInt(
                    strings[strings.length - 1]// берем последнюю стрингу
                            .replaceAll("[\\D]", ""));// удаляем нах*й непонятные пробелы
            return usage;
        } catch (IOException e) {
            e.printStackTrace();
            return -2;
        }
        //or use that
//        ProcessInfo processInfo = JProcesses.getProcess(3620);//pid of the process
//        System.out.println(processInfo.getVirtualMemory() + " Virtual memory");
//        System.out.println(processInfo.getPhysicalMemory() + " Physical memory");

    }

    /**
     * Removes db files
     */

    public void rmDB() {
        File dir = new File(Props.get("numberokData.path"));
        File[] files;
        if (dir.isDirectory()) {
            files = dir.listFiles();
            assert files != null;
            // получаем все вложенные объекты в каталоге
            for (File item : files) {
                if (item.getName().contains(".db")) item.delete();
            }
        }

        System.out.println("Database is removed");
    }

    public void toucher(final App numberok) {
        toucher = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean running;

                System.out.println("toucher is started ");
                while ((running = numberok.isRunning())) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Toucher's sleep is interrupted");
                    }
                }
                try {
                    Assert.assertTrue(running);

                } catch (AssertionError e) {
                    System.out.println("Numberok is not running ");
                }
                Thread.currentThread().interrupt();

            }
        });
        toucher.start();
    }

    public void stopTouching() {
        if (toucher.isAlive() || !toucher.isInterrupted()) {
            toucher.interrupt();
        }
    }

    /**
     * Waiting FOREVER of vanish green fingerprint  from the screen
     *
     * @return true if vanished
     */

    public boolean waitVanish() {
        return waitVanish(null);
    }

    /**
     * Waiting of vanish green fingerprint  from the screen
     *
     * @param howLong how long to wait
     * @return true if vanished
     */

    public boolean waitVanish(Object howLong) {
        screen.onVanish(new Pattern(Props.pathForRun("GreenFinger_MainMenu.png")));
        if (howLong == null) return screen.observe();
        else return screen.observe((Double) howLong);

    }

}
