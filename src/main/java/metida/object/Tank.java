package metida.object;

import metida.factory.CommandFactory;
import metida.interfacable.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Tank extends BaseObject implements Movable, Activable, Checkable, Shootable {

    private int idTeam;
    private int damage;
    protected int step;
    private  int health;
    private  boolean isFlag = false;
    private  boolean living;
    TypeObjects type;

    private Direction direction;

    protected GameOptions gameOptions;

    private static Logger LOGGER = LoggerFactory.getLogger(Tank.class);

    private CommandFactory factory=new CommandFactory();

    //private PlayerTank tank = new PlayerTank();

    private QueueMethods<TankCommands> queueMethods =new QueueMethods<TankCommands>();

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

    public GameOptions getGameOptions() {
        return gameOptions;
    }

    public void setGameOptions(GameOptions gameOptions) {
        this.gameOptions = gameOptions;
    }

    public Tank(int idTeam) {
        this.type=TypeObjects.TANK;
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

    public QueueMethods<TankCommands> getQueueMethods() {
        return queueMethods;
    }

    public void setQueueMethods(QueueMethods<TankCommands> queueMethods) {
        this.queueMethods = queueMethods;
    }

    public void action() {

    }

    @Override
    public void moveUp() {
        //factory.getMoveUpCommand(tank);
        //todo: save command
        //queueMethods.offer(factory.getMoveUpCommand(tank));

    }

    @Override
    public void moveDown() {
        //factory.getMoveDownCommand(tank);
        //todo: save command
       // queueMethods.offer(factory.getMoveDownCommand(tank));
    }

    @Override
    public void moveRight() {

        //todo: save command
        queueMethods.offer(factory.getMoveRightCommand(tank));
    }

    @Override
    public void moveLeft() {

        //todo: save command
      //  queueMethods.offer(factory.getMoveLeftCommand(tank));
    }

    public void turn(Direction direction) {

        //todo: save command
       // queueMethods.offer(factory.getTurnCommand(tank,direction));
        //queueMethods.offer(new MoveUpCommand(new PlayerTank()));
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

    Random random = new Random();

    /**
     * Уворот от снарядов в зоне видимости
     *
     * */

    //ToDo: дублировать действия на тестовую карту hashmap, для просчитывания информации окружения,
    // то есть реализация будет дублироваться внутри Tank."Any"Command
    // ***скорее всего не надо, буду просчитывать мир сразу
    @Override
    public void checkShoot(Direction direction) {
        Map<Integer,BaseObject> map = checkAround(gameOptions.getVision());
        map.forEach((id,object) -> {
            if(object.getType()==TypeObjects.BULLET){
                if(object.getX()==X){
                    switch(object.getDirection()){
                        case LEFT:
                            if (direction==Direction.LEFT){
                                break;
                            }
                            if (direction==Direction.RIGHT){
                                int c=random.nextInt(2);
                                if(c==1){
                                    //factory.getMoveDownCommand(tank);
                                    //todo: save command
                                }
                                else{
                                    //factory.getMoveUpCommand(tank);
                                    //todo: save command
                                }
                            }
                        case RIGHT:
                            if (direction==Direction.LEFT){
                                int c=random.nextInt(2);
                                if(c==1){
                                    //factory.getMoveDownCommand(tank);
                                    //todo: save command
                                }
                                else{
                                    //factory.getMoveUpCommand(tank);
                                    //todo: save command
                                }
                            }
                            if (direction==Direction.RIGHT){
                                break;
                            }
                    }

                }
                if(object.getY()==Y){
                    switch(object.getDirection()){
                        case UP:
                            if (direction==Direction.UP){
                                break;
                            }
                            if (direction==Direction.DOWN){
                                int c=random.nextInt(2);
                                if(c==1){
                                    //factory.getMoveRightCommand(tank);
                                    //todo: save command
                                }
                                else{
                                    //factory.getMoveLeftCommand(tank);
                                    //todo: save command
                                }
                            }
                        case DOWN:
                            if (direction==Direction.DOWN){
                                break;
                            }
                            if (direction==Direction.UP){
                                int c=random.nextInt(2);
                                if(c==1){
                                    //factory.getMoveRightCommand(tank);
                                    //todo: save command
                                }
                                else{
                                    //factory.getMoveLeftCommand(tank);
                                    //todo: save command
                                }
                            }
                    }

                }
            }
        });
    }


    public void shoot(Direction direction) {

        //todo: save command
        //queueMethods.offer(factory.getShootCommand(tank,direction));
    }

    //todo: не понадобится
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
