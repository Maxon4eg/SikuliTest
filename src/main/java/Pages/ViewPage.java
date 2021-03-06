package Pages;


import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import util.Props;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


/**
 * Created by DespicableMe on 01.03.2016.
 * Описание:
 */
public class ViewPage extends MainController {
    private ArrayList<Location> sources;

    private void findChann() throws Exception {
        sources = new ArrayList<>();
        Pattern notConn = new Pattern(Props.pathForRun("VidNotConn_ViewPage.png"));
        try {
            Iterator<Match> matchIterator = screen.findAll(notConn);
            while (matchIterator.hasNext()) {
                sources.add(new Location(matchIterator.next().getTarget()));
            }
            Collections.sort(sources);//соритруем
            Collections.reverse(sources);//переворачиваем , с конца на начало // TODO: 17.03.2016 Сделай сортировку массива соответсвенно нумерации канала
        } catch (FindFailed findFailed) {
            throw new Exception("Could'nt find channel");
        }
    }

    /**
     * @param chan номер канала, счет начинается с еденицы
     * @return false-если видео нет
     * true-если видео есть
     */
    public boolean checkChan(int chan) {
        chan -= 1;
        if (sources != null) {
            try {
                screen.mouseMove(sources.get(chan));
            } catch (FindFailed findFailed) {
                System.out.println(findFailed.getLocalizedMessage());
                return false;
            }
            int targetRGB = sources.get(chan).getColor().getRGB();
            return !((targetRGB == Props.getInt("RGB.DarkBlue")) || (targetRGB == Props.getInt("RGB.Red")));
        } else System.out.println("You forgot to check channel first ");
        return false;
    }

    public int howManyChannels() throws Exception {
        if (sources == null) {
            findChann();
        }
        return sources.size();
    }
}
