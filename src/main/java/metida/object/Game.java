package metida.object;

import metida.interfacable.Activable;
import metida.interfacable.Gameable;

import java.util.*;

public class Game extends GameOptions{


    //ToDo: добавлять параметры из конфига в конструктор GameOptions
    public Game()
    {
        gameOptions = new GameOptions();
    }

    private static Game instance;

    public static Game Initialize()
    {
        if (instance == null)
            instance = new Game();
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

    public void AddObject(BaseObject obj,int x,int y)
    {
        Point point = new Point(x,y);
        objects.put(point.hashCode(), obj);
    }

    public void RemoveObject(BaseObject obj,int x , int y)
    {
        Point point = new Point(x,y);
        objects.remove(point.hashCode(), obj);
    }

    public BaseObject findObject(int x , int y) {
        Point point = new Point(x,y);
        return objects.get(point.hashCode());
    }

}