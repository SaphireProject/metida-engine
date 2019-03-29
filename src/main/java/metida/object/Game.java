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

    //ToDo: не лист, map с разными ключами на один элемент ссылающиеся
    public List<BaseObject> objects = new ArrayList<BaseObject>();

    public void AddObject(BaseObject obj)
    {
        objects.add(obj);
    }

    public void RemoveObject(BaseObject obj)
    {
        objects.remove(obj);
    }

    //ToDo: нормальную реализацию
    public BaseObject findObject(int x , int y) {
        return null;
    }

}
