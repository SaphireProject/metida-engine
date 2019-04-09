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
            if(!object.isFlag()){
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
    }

    public void setFalseStatus(){
        objects.forEach((id, object) -> {
            object.setFlag(false);
            LOGGER.info("Объекту установален статус false");
        });

    }

}


/*
            if(!object.isLiving()){
                LOGGER.info("Удаление мертвого объекта "+objects.get(id));
                objects.remove(id);
                LOGGER.info("Объект "+objects.get(id)+" удален");
            }
*/


/*
        Set<Map.Entry<Integer, BaseObject>> entrySet = objects.entrySet();

        Iterator<Map.Entry<Integer, BaseObject>> itr = entrySet.iterator();

        while (itr.hasNext())
        {
            Map.Entry<Integer, BaseObject> entry = itr.next();


            itr.remove();
        }



//Iterator it = objects.entrySet().iterator();
         while (it.hasNext())
        {
            Map.Entry item = (Map.Entry) it.next();

            item.
            it.remove();
        }




        Set<Map.Entry<Integer, BaseObject>> entrySet = objects.entrySet();

       Iterator<Map.Entry<Integer, BaseObject>> itr = entrySet.iterator();

       while (itr.hasNext())
       {
           Map.Entry<Integer, BaseObject> entry = itr.next();

           if(!objects.get(entry.getKey()).isFlag()){
               objects.get(entry.getKey()).action();
               //LOGGER.info("Объект "+objects.get(entry.getKey()).toString()+" выполнил одно свое действие в свой ход");
               //objects.get(entry.getKey()).setFlag(false);
           }
       }
       /*
       while (itr.hasNext())
       {
           Map.Entry<Integer, BaseObject> entry = itr.next();

           if(!objects.get(entry.getKey()).isLiving()){
               LOGGER.info("Удаление мертвого объекта "+objects.get(entry.getKey()));
               itr.remove();
               LOGGER.info("Объект "+objects.get(entry.getKey())+" удален");
           }
       }

       */
