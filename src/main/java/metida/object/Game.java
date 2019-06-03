package metida.object;

import metida.data.Data;
import metida.data.ParameterMetida;
import metida.factory.TankFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static metida.service.FileService.getConfigTest;


public class Game extends GameOptions{

    private static Logger LOGGER = LoggerFactory.getLogger(Game.class);

    public Game(ParameterMetida data)
    {
        //Data data = getConfigTest(path);
        gameOptions = new GameOptions(data.getWidthOfMapForGame(), data.getHeightOfMapForGame());
        LOGGER.info("Игра создана");

    }

    private static Game instance;

    public static Game Initialize(ParameterMetida data)
    {
        if (instance == null)
            instance = new Game(data);
        return instance;
    }

    @Autowired
    private TankFactory factory;

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

    public Map<Integer, BaseObject> objTank = new HashMap<>();

    public Map<Integer, BaseObject> objWall = new HashMap<>();

    public Map<Integer, BaseObject> getObjWall() {
        return objWall;
    }

    public void setObjWall(Map<Integer, BaseObject> objWall) {
        this.objWall = objWall;
    }

    public Map<Integer, BaseObject> getObjects() {
        return objects;
    }

    public Map<Integer, ThreadStrategy> threadStrategyMap = new HashMap<>();

    public void setObjects(Map<Integer, BaseObject> objects) {
        this.objects = objects;
    }



    public void addTank(BaseObject obj, int x, int y, GameOptions gameOptions){
        obj.setX(x);
        obj.setY(y);
        obj.setGameOptions(gameOptions);
        obj.setGame(instance);
        Point point = new Point(x,y);

        objTank.put(point.hashCode(), obj);
        //Для того чтобы бежать по танкам и собирать очереди с методами
        LOGGER.info("Добавлен танк "+obj);
    }

    public Map<Integer, BaseObject> getObjTank() {
        return objTank;
    }

    public void setObjTank(Map<Integer, BaseObject> objTank) {
        this.objTank = objTank;
    }

    public Map<Integer, BaseObject> getObjectsDeleteOld() {
        return objectsDeleteOld;
    }

    public void setObjectsDeleteOld(Map<Integer, BaseObject> objectsDeleteOld) {
        this.objectsDeleteOld = objectsDeleteOld;
    }

    public void addObjectWall(BaseObject obj, int x, int y, GameOptions gameOptions)
    {
        obj.setX(x);
        obj.setY(y);
        obj.setGameOptions(gameOptions);
        obj.setGame(instance);
        Point point = new Point(x,y);

        gameOptions.hashmap.put(point.hashCode(), obj);
        objects.put(point.hashCode(), obj);
        objWall.put(point.hashCode(), obj);
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
        obj.setY(y);
        obj.setGameOptions(gameOptions);
        obj.setGame(instance);
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

                if(object.getType()==TypeObjects.BULLET){


                }
                object.action();
                if(object.getType()==TypeObjects.WALL){

                }
                else{
                    LOGGER.info("Объект "+object.toString()+" выполнил одно свое действие в свой ход");
                }

                //object.setFlag(false);
            }
            //object.setFlag(false);
            /*if(!object.isLiving() && object.getType()==TypeObjects.TANK){
                factory.objectsTank.remove(id);
            }
            if(!object.isLiving()){
                LOGGER.info("Удаление мертвого объекта "+objects.get(id));
                objects.remove(id);
                LOGGER.info("Объект "+objects.get(id)+" удален");
            }*/

        });

        //установка статуса, что все объекты не совершали действий
        objects.forEach((id, object) -> object.setFlag(false));
        //Добавление новых пуль на карту
        objectsAdd.forEach((id, object) -> objects.put(id,object));
        //Удаление старых пуль с карты
        objectsDeleteOld.forEach((id,object) -> objects.remove(id));
        //objectsDeleteOld.clear();
        objectsAdd.clear();
        LOGGER.info("Конец одного действия");
    }

    public void setFalseStatus(){
        objects.forEach((id, object) -> {
            object.setFlag(false);
            LOGGER.info("Объекту установален статус false");
        });

    }
}
