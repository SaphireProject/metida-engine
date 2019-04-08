package metida.object;

import metida.data.Data;
import metida.interfacable.Activable;
import metida.interfacable.Direction;
import metida.interfacable.Gameable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static metida.service.FileService.getConfigTest;

public class Game extends GameOptions{

    private static Logger LOGGER = LoggerFactory.getLogger(Game.class);

    //ToDo: пока считываю из файла
    public Game(String path)
    {
        Data data=getConfigTest(path);
        gameOptions = new GameOptions(data.getLengthX(),data.getLengthY(),data.getVision());
    }

    private static Game instance;

    public static Game Initialize(String path)
    {
        if (instance == null)
            instance = new Game(path);
        return instance;
    }

    public GameOptions gameOptions;

    public GameOptions getGameOptions() {
        return gameOptions;
    }

    public void setGameOptions(GameOptions gameOptions) {
        this.gameOptions = gameOptions;
    }

    public Map<Integer, BaseObject> objects = new HashMap<>();

    public Map<Integer, BaseObject> getObjects() {
        return objects;
    }

    public void setObjects(Map<Integer, BaseObject> objects) {
        this.objects = objects;
    }

    public void addObject(BaseObject obj, int x, int y, GameOptions gameOptions)
    {
        obj.setX(x);
        obj.setY(y);
        obj.setGameOptions(gameOptions);
        obj.setGame(instance);
        Point point = new Point(x,y);

        gameOptions.hashmap.put(point.hashCode(), obj);
        objects.put(point.hashCode(), obj);
    }

    public void removeObject(BaseObject obj, int x , int y)
    {
        Point point = new Point(x,y);
        gameOptions.hashmap.put(point.hashCode(), null);
        objects.put(point.hashCode(), null);
    }

    public BaseObject findObject(int x , int y) {
        Point point = new Point(x,y);
        return gameOptions.hashmap.get(point.hashCode());
    }


    public void action() {
        objects.forEach((id, object) ->  {
            object.action();
            if(!object.isLiving()){
                LOGGER.info("Удаление мертвого объекта");
                objects.put(id, null);
            }
        });
    }
}