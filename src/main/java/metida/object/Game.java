package metida.object;

import metida.data.Data;
import metida.interfacable.Activable;
import metida.interfacable.Direction;
import metida.interfacable.Gameable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

import static metida.service.FileService.getConfigTest;


public class Game extends GameOptions{

    private static Logger LOGGER = LoggerFactory.getLogger(Game.class);

    //ToDo: пока считываю из файла
    public Game(String path)
    {
        Data data = getConfigTest(path);
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

    public Map<Integer, BaseObject> objectsAdd = new HashMap<>();

    public Map<Integer, BaseObject> objectsDeleteOld = new HashMap<>();

    public Map<Integer, BaseObject> getObjects() {
        return objects;
    }

    public Map<Integer, ThreadStrategy> threadStrategyMap = new HashMap<>();

    public void setObjects(Map<Integer, BaseObject> objects) {
        this.objects = objects;
    }

    public void addObjectAdd(BaseObject obj, int x, int y, GameOptions gameOptions)
    {
        obj.setX(x);
        obj.setY(y);
        obj.setGameOptions(gameOptions);
        obj.setGame(instance);
        Point point = new Point(x,y);

        gameOptions.hashmap.put(point.hashCode(), obj);
        objectsAdd.put(point.hashCode(), obj);
        LOGGER.info("Добавлен объект на добавление "+obj);
    }

    public void addObject(BaseObject obj, int x, int y, GameOptions gameOptions)
    {
        obj.setX(x);
        LOGGER.info("Добавлен объект "+obj);
        obj.setY(y);
        LOGGER.info("Добавлен объект "+obj);
        obj.setGameOptions(gameOptions);
        LOGGER.info("Добавлен объект "+obj);
        obj.setGame(instance);
        LOGGER.info("Добавлен объект "+obj);
        Point point = new Point(x,y);

        gameOptions.hashmap.put(point.hashCode(), obj);
        objects.put(point.hashCode(), obj);
        LOGGER.info("Добавлен объект "+obj);
    }

    public void removeObject(BaseObject obj, int x , int y)
    {
        Point point = new Point(x,y);
        gameOptions.hashmap.put(point.hashCode(), null);
        objects.remove(point.hashCode());
    }

    public void removeObjectOld(BaseObject obj, int x , int y, GameOptions gameOptions)
    {
        obj.setX(x);
        obj.setY(y);
        obj.setGameOptions(gameOptions);
        obj.setGame(instance);
        Point point = new Point(x,y);

        gameOptions.hashmap.put(point.hashCode(), null);
        objectsDeleteOld.put(point.hashCode(), obj);
    }

    public BaseObject findObject(int x , int y) {
        Point point = new Point(x,y);
        return gameOptions.hashmap.get(point.hashCode());
    }

    public void action() {

        objects.forEach((id, object) ->  {
            if(object.isFlag()==false){
                object.action();
                LOGGER.info("Объект "+object.toString()+" выполнил одно свое действие в свой ход");
                //object.setFlag(false);
            }
            if(!object.isLiving()){
                LOGGER.info("Удаление мертвого объекта "+objects.get(id));
                objects.remove(id);
                LOGGER.info("Объект "+objects.get(id)+" удален");
            }
        });

        objects.forEach((id, object) -> object.setFlag(false));
        //Добавление новых пуль на карту
        objectsAdd.forEach((id, object) -> objects.put(id,object));
        //Удаление старых пуль с карты
        objectsDeleteOld.forEach((id,object) -> objects.remove(id));
        objectsDeleteOld.clear();
        objectsAdd.clear();
        LOGGER.info("Конец одного действия");
    }

    public void setFalseStatus(){
        objects.forEach((id, object) -> {
            object.setFlag(false);
            LOGGER.info("Объекту установален статус false");
        });

    }
/*
    private void executeCommand(Command command) {
        if (command.execute()) {
            history.add(command);
        }
    }
*/
}
