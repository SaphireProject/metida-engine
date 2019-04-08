package metida.object;


import metida.interfacable.Activable;
import metida.interfacable.Checkable;
import metida.interfacable.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Bullet extends BaseObject implements Activable, Checkable {
    private int speed;
    private int X;
    private int Y;
    private  int health;
    //private int idTeam;
    private  boolean living;
    Direction direction;
    public Map<Integer, BaseObject> hashmap = new HashMap<>();
    private static Logger LOGGER = LoggerFactory.getLogger(Bullet.class);


    public Bullet(int X, int Y, Direction direction) {
        this.speed = 3;
        this.X=X;
        this.Y=Y;
        this.health=1;
        this.direction=direction;
    }

    @Override
    public boolean isLiving() {
        return true;
    }

    @Override
    public int getX() {
        return X;
    }


    public void setX(int x) {
        X = x;
    }

    @Override
    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getSpeed() {
        return speed;
    }


    @Override
    public void action() {
        if(checkForward(this.direction)){
            switch (this.direction) {
                case LEFT:
                    Bullet bulletLEFT = new Bullet(this.X-1,this.Y,direction);
                    game.addObject(bulletLEFT ,X-1,Y,game.gameOptions);
                    Point pointLEFT = new Point(this.X-1,this.Y);
                    Point oldPointLEFT= new Point(this.X,this.Y);
                    gameOptions.hashmap.put(pointLEFT.hashCode(), bulletLEFT);
                    LOGGER.info("коорд пули "+bulletLEFT.X);
                    gameOptions.hashmap.put(oldPointLEFT.hashCode(),null);
                    LOGGER.info("переместилась ли пуля "+game.findObject(X,Y));
                    LOGGER.info("переместилась ли пуля "+game.findObject(X-1,Y));

                    game.objects.put(pointLEFT.hashCode(), bulletLEFT);
                    game.objects.put(oldPointLEFT.hashCode(),null);
                    break;
                case RIGHT:
                    Bullet bulletRIGHT = new Bullet(this.X-1,this.Y,this.direction);
                    Point pointRIGHT = new Point(this.X-1,this.Y);
                    Point oldPointRIGHT= new Point(this.X,this.Y);
                    gameOptions.hashmap.put(pointRIGHT.hashCode(), bulletRIGHT);
                    gameOptions.hashmap.put(oldPointRIGHT.hashCode(),null);
                    break;
                case UP:
                    Bullet bulletUP = new Bullet(this.X-1,this.Y,this.direction);
                    Point pointUP = new Point(this.X-1,this.Y);
                    Point oldPointUP= new Point(this.X,this.Y);
                    gameOptions.hashmap.put(pointUP.hashCode(), bulletUP);
                    gameOptions.hashmap.put(oldPointUP.hashCode(),null);
                    break;
                case DOWN:
                    Bullet bulletDOWN = new Bullet(this.X-1,this.Y,this.direction);
                    Point pointDOWN = new Point(this.X-1,this.Y);
                    Point oldPointDOWN= new Point(this.X,this.Y);
                    gameOptions.hashmap.put(pointDOWN.hashCode(), bulletDOWN);
                    gameOptions.hashmap.put(oldPointDOWN.hashCode(),null);
                    break;
            }
        }
    }

    @Override
    public Map<Integer, BaseObject> checkAround(int vision) {
        return null;
    }

    //ToDo: заменить проверку на поля
    @Override
    public boolean checkForward(Direction direction) {
       // Map<Integer,BaseObject> map = checkAround(gameOptions.getVision());
        switch(direction){
            /*case UP:
                Point pointUP=new Point(X,Y+1);
                if (map.get(pointUP.hashCode())==null){
                    return true;
                }
                else{
                    return false;
                }
            case DOWN:
                Point pointDOWN=new Point(super.X,super.Y-1);
                if (map.get(pointDOWN.hashCode())==null){
                    return true;
                }
                else{
                    return false;
                }
                /*gameOptions.hashmap.get(pointLEFT.hashCode())==null*/
            case LEFT:
                Point pointLEFT=new Point(this.X-1,this.Y);
                Point pointLEFTold=new Point(this.X,this.Y);
                LOGGER.info("hash"+ pointLEFT.hashCode());
                if (gameOptions.hashmap.get(pointLEFT.hashCode())==null/*map.get(pointLEFT.hashCode())==null*/){

                    return true;
                }
                else{
                    LOGGER.info("Движение невозможно в координату: "+(this.X-1)+" "+(Y));
                    BaseObject base=gameOptions.hashmap.get(pointLEFT.hashCode());
                    LOGGER.info("health " + gameOptions.hashmap.get(pointLEFT.hashCode()).getHealth());
                    base.getHit(gameOptions.hashmap.get(pointLEFT.hashCode()));
                    LOGGER.info("health " + gameOptions.hashmap.get(pointLEFT.hashCode()).getHealth());
                    //gameOptions.hashmap.get(pointLEFT.hashCode()).getHit(gameOptions.hashmap.get(pointLEFT.hashCode()));
                    gameOptions.hashmap.put(pointLEFTold.hashCode(),null);//удаляем пулю с карты
                    game.objects.put(pointLEFTold.hashCode(),null);//удаляем пулю из спика объектов
                    LOGGER.info("Повредился ли объект: "+gameOptions.hashmap.get(pointLEFT.hashCode()));
                    return false;
                }
           /* case RIGHT:
                Point pointRIGHT=new Point(super.X+1,super.Y);
                if (map.get(pointRIGHT.hashCode())==null){
                    return true;
                }
                else{
                    return false;
                }*/
        }
        return false;
    }


    @Override
    public String toString() {
        return "Bullet{" +
                "speed=" + speed +
                ", X=" + X +
                ", Y=" + Y +
                ", health=" + health +
                ", direction=" + direction +
                '}';
    }
}
