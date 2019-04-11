package metida.object;

import metida.interfacable.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Tank extends BaseObject implements Movable, Activable, Checkable, Shootable {

    private int idTeam;
    private int damage;
    private int step;
    private  int health;
    private  boolean isFlag = false;
    private  boolean living;

    private Direction direction;

    private static Logger LOGGER = LoggerFactory.getLogger(Tank.class);


    @Override
    public boolean isFlag() {
        return isFlag;
    }

    @Override
    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    @Override
    public boolean isLiving() {
        if(health>0) {
            return true;
        }
        return false;
    }

    @Override
    public void setLiving(boolean living) {
        this.living = living;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private GameOptions gameOptions;

    public GameOptions getGameOptions() {
        return gameOptions;
    }

    public void setGameOptions(GameOptions gameOptions) {
        this.gameOptions = gameOptions;
    }

    public Tank(int idTeam) {
        this.idTeam = idTeam;
        this.damage = 1;
        this.step = 1;
        this.health=2;
        this.direction=Direction.UP;
    }

    public Tank() {
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    public int getStep() {
        return step;
    }

    public int getDamage() {
        return damage;
    }


    public int getIdTeam() {
        return idTeam;
    }


    public void action() {

    }

    @Override
    public void moveUp() {
        if(Y+step<=gameOptions.getHeight()) {
            if(checkForward(Direction.UP)){
                LOGGER.info("Начало движения");
                Point oldPoint = new Point(X,Y);
                Y=Y+1;
                setDirection(Direction.UP);
                Point newpoint = new Point(X,Y);
                LOGGER.info("Объект, который должен сдвинуться " + gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(newpoint.hashCode(), gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(oldPoint.hashCode(),null);
            }
        }
    }

    @Override
    public void moveDown() {
        if(Y-step>=0) {
            if(checkForward(Direction.DOWN)){
                LOGGER.info("Начало движения");
                Point oldPoint = new Point(X,Y);
                Y=Y-1;
                setDirection(Direction.DOWN);
                Point newpoint = new Point(X,Y);
                LOGGER.info("Объект, который должен сдвинуться "+gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(newpoint.hashCode(), gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(oldPoint.hashCode(),null);
            }
        }
    }

    @Override
    public void moveRight() {
        if(X+step<=gameOptions.getWidth()) {
            if(checkForward(Direction.RIGHT)){
                LOGGER.info("Начало движения");
                Point oldPoint = new Point(X,Y);
                X=X+1;
                setDirection(Direction.RIGHT);
                Point newpoint = new Point(X,Y);
                LOGGER.info("Объект, который должен сдвинуться "+gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(newpoint.hashCode(), gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(oldPoint.hashCode(),null);
            }
        }
    }

    @Override
    public void moveLeft() {
        if(X-step>=0) {
            setDirection(Direction.LEFT);
            if(checkForward(Direction.LEFT)){
                LOGGER.info("Начало движения");
                Point oldPoint = new Point(X,Y);
                X=X-1;

                Point newpoint = new Point(X,Y);
                LOGGER.info("Объект, который должен сдвинуться " + gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(newpoint.hashCode(), gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(oldPoint.hashCode(),null);
            }
        }
    }

    public void turn(Direction direction) {
        setDirection(direction);
        LOGGER.info("Танк повернулся на " +direction);
    }

    public Map<Integer,BaseObject> checkAround(int vis) {
        vis=gameOptions.getVision();
        Map<Integer,BaseObject> map = gameOptions.getByRange(gameOptions.hashmap,
                X-vis,X+vis,
                Y-vis,Y+vis);
        return map;
    }

    public boolean checkForward(Direction direction) {
        Map<Integer,BaseObject> map = checkAround(gameOptions.getVision());
        switch(direction){
            case UP:
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
                Point pointLEFT=new Point(super.X-1,super.Y);
                LOGGER.info("hash"+ pointLEFT.hashCode());
                if (gameOptions.hashmap.get(pointLEFT.hashCode())==null/*map.get(pointLEFT.hashCode())==null*/){
                    return true;
                }
                else{
                    LOGGER.info("Движение невозможно в координату: "+(X-1)+" "+(Y));
                    return false;
                }
            case RIGHT:
                Point pointRIGHT=new Point(super.X+1,super.Y);
                if (map.get(pointRIGHT.hashCode())==null){
                    return true;
                }
                else{
                    return false;
                }
        }
        return false;
    }

    public void shoot(Direction direction) {
        switch (direction){
            case DOWN:
                Bullet bulletDOWN = new Bullet(X, Y-1, direction,false);
                Point pointDOWN = new Point(X,Y-1);
                gameOptions.hashmap.put(pointDOWN.hashCode(),bulletDOWN);
                LOGGER.info("произошел выстрел вни");
                game.addObject(bulletDOWN,X,Y-1,game.gameOptions);
            case UP:
                Bullet bulletUP = new Bullet(X, Y+1, direction,false);
                Point pointUP = new Point(X,Y+1);
                gameOptions.hashmap.put(pointUP.hashCode(),bulletUP);
                LOGGER.info("произошел выстрел");
                game.addObject(bulletUP,X,Y+1,game.gameOptions);
            case RIGHT:
                Bullet bulletRIGHT = new Bullet(X+1, Y, direction,false);
                Point pointRIGHT = new Point(X+1,Y);
                gameOptions.hashmap.put(pointRIGHT.hashCode(),bulletRIGHT);
                LOGGER.info("произошел выстрел");
                game.addObject(bulletRIGHT,X+1,Y,game.gameOptions);
            case LEFT:
                Bullet bulletLEFT = new Bullet(X-1, Y, direction,false);
                Point pointLEFT = new Point(X-1,Y);
                gameOptions.hashmap.put(pointLEFT.hashCode(),bulletLEFT);
                LOGGER.info("произошел выстрел");
                game.addObject(bulletLEFT,X-1,Y,game.gameOptions);
        }


    }

    public double getDistance(double X, double Y) {
        double dx=X-this.X,
                dy=Y-this.Y;
        return Math.sqrt(dx*dx+dy*dy);
    }

    @Override
    public String toString() {
        return "Tank{" +
                "idTeam=" + idTeam +
                ", damage=" + damage +
                ", step=" + step +
                ", health=" + health +
                ", direction=" + direction +
                '}';
    }
}
