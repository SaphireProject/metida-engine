package metida.object;

import metida.factory.CommandFactory;
import metida.interfacable.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Tank extends BaseObject implements Movable, Activable, Checkable, Shootable {

    private int idTeam;
    private int damage;
    protected int step;
    private  int health;
    private  boolean isFlag = false;
    private  boolean living;

    private Direction direction;

    private static Logger LOGGER = LoggerFactory.getLogger(Tank.class);

    private CommandFactory factory=new CommandFactory();

    private PlayerTank tank = new PlayerTank();


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

    protected GameOptions gameOptions;

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
        factory.getMoveUpCommand(tank);
    }

    @Override
    public void moveDown() {
        factory.getMoveDownCommand(tank);
    }

    @Override
    public void moveRight() {
        factory.getMoveRightCommand(tank);
    }

    @Override
    public void moveLeft() {
        factory.getMoveLeftCommand(tank);
    }

    public void turn(Direction direction) {
        factory.getTurnCommand(tank,direction);
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
                if (map.get(pointUP.hashCode()) == null){
                    return true;
                }
                else{
                    LOGGER.info("Движение невозможно в координату: "+(X)+" "+(Y+1));
                    return false;
                }
            case DOWN:
                Point pointDOWN=new Point(super.X,super.Y-1);
                if (map.get(pointDOWN.hashCode()) == null){
                    return true;
                }
                else{
                    LOGGER.info("Движение невозможно в координату: "+(X)+" "+(Y-1));
                    return false;
                }
            case LEFT:
                Point pointLEFT=new Point(super.X-1,super.Y);
                //LOGGER.info("hash"+ pointLEFT.hashCode());
                if (gameOptions.hashmap.get(pointLEFT.hashCode()) == null){
                    return true;
                }
                else{
                    LOGGER.info("Движение невозможно в координату: "+(X-1)+" "+(Y));
                    return false;
                }
            case RIGHT:
                Point pointRIGHT=new Point(super.X+1,super.Y);
                if (map.get(pointRIGHT.hashCode()) == null){
                    return true;
                }
                else{
                    LOGGER.info("Движение невозможно в координату: "+(X+1)+" "+(Y));
                    return false;
                }
        }
        return false;
    }

    public void shoot(Direction direction) {


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
