package metida.object;

import metida.CommandsTank.TankCommands;
import metida.factory.CommandFactory;
import metida.interfacable.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Random;

public class Tank extends BaseObject implements Movable, Activable, Checkable, Shootable {


    private static int idTank=1;

    private int id;

    private int idTeam;
    private int damage;
    protected int step;
    private  int health;
    private  boolean isFlag = false;
    private  boolean living= true;
    TypeObjects type;
    private  String color;

    private Direction direction;

    protected GameOptions gameOptions;

    private static Logger LOGGER = LoggerFactory.getLogger(Tank.class);

    private CommandFactory factory=new CommandFactory();

    private QueueMethods<TankCommands> queueMethods =new QueueMethods<TankCommands>();

    private QueueMethods<TankCommands> queueMethodsDuplicate =new QueueMethods<TankCommands>();

    public Tank(int idTeam) {
        this.type=TypeObjects.TANK;
        this.idTeam = idTeam;
        this.damage = 1;
        this.step = 1;
        this.health=20;
        this.direction=Direction.UP;
        this.id=idTank;
        idTank++;
    }

    public Tank() {
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    public QueueMethods<TankCommands> getQueueMethodsDuplicate() {
        return queueMethodsDuplicate;
    }

    public void setQueueMethodsDuplicate(QueueMethods<TankCommands> queueMethodsDuplicate) {
        this.queueMethodsDuplicate = queueMethodsDuplicate;
    }

    @Override
    public int getIdTeam() {
        return idTeam;
    }

    @Override
    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

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



    public QueueMethods<TankCommands> getQueueMethods() {
       /* if(queueMethods.isEmpty())
        {
            queueMethods=queueMethodsDuplicate;
        }*/
        return queueMethods;
    }

    public void setQueueMethods(QueueMethods<TankCommands> queueMethods) {
        this.queueMethods = queueMethods;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void action() {

    }

    @Override
    public TypeObjects getType() {
        return type;
    }

    @Override
    public void setType(TypeObjects type) {
        this.type = type;
    }

    @Override
    public void moveUp() {
        queueMethods.offer(factory.getMoveUpCommand(this));
    }

    @Override
    public void moveDown() {
        queueMethods.offer(factory.getMoveDownCommand(this));
    }

    @Override
    public void moveRight() {
        queueMethods.offer(factory.getMoveRightCommand(this));
    }

    @Override
    public void moveLeft() {
        queueMethods.offer(factory.getMoveLeftCommand(this));
    }

    public void turn(Direction direction) {
        queueMethods.offer(factory.getTurnCommand(this,direction));
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
        //Map<Integer,BaseObject> map = checkAround(gameOptions.getVision());
        switch(direction){
            case UP:
                if(Y+1<gameOptions.getHeight()){
                    Point pointUP=new Point(this.X,this.Y+1);
                    if (gameOptions.hashmap.get(pointUP.hashCode()) == null){
                        return true;
                    }
                    else{
                        LOGGER.info("Движение невозможно в координату: "+(X)+" "+(Y+1));
                        return false;
                    }
                }
                return false;
            case DOWN:
                if(Y-1>=0){
                    Point pointDOWN=new Point(this.X,this.Y-1);
                    if (gameOptions.hashmap.get(pointDOWN.hashCode()) == null){
                        return true;
                    }
                    else{
                        LOGGER.info("Движение невозможно в координату: "+(X)+" "+(Y-1));
                        return false;
                    }
                }
                return false;
            case LEFT:
                if(X-1>=0) {
                    Point pointLEFT=new Point(this.X-1,this.Y);
                    //LOGGER.info("hash"+ pointLEFT.hashCode());
                    if (gameOptions.hashmap.get(pointLEFT.hashCode()) == null){
                        return true;
                    }
                    else{
                        LOGGER.info("Движение невозможно в координату: "+(X-1)+" "+(Y));
                        return false;
                    }
                }
                return false;
            case RIGHT:
                if(X+1<gameOptions.getWidth()) {
                    Point pointRIGHT=new Point(this.X+1,this.Y);
                    if (gameOptions.hashmap.get(pointRIGHT.hashCode()) == null){
                        return true;
                    }
                    else{
                        LOGGER.info("Движение невозможно в координату: "+(X+1)+" "+(Y));
                        return false;
                    }
                }
                return false;
        }
        return false;
    }

    Random random = new Random();

    /**
     * Уворот от снарядов в зоне видимости
     *
     * */

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

                                    queueMethods.offer(factory.getMoveDownCommand(this));
                                }
                                else{
                                    //factory.getMoveUpCommand(tank);

                                    queueMethods.offer(factory.getMoveUpCommand(this));
                                }
                            }
                        case RIGHT:
                            if (direction==Direction.LEFT){
                                int c=random.nextInt(2);
                                if(c==1){
                                    //factory.getMoveDownCommand(tank);

                                    queueMethods.offer(factory.getMoveDownCommand(this));
                                }
                                else{
                                    //factory.getMoveUpCommand(tank);

                                    queueMethods.offer(factory.getMoveUpCommand(this));
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

                                    queueMethods.offer(factory.getMoveRightCommand(this));
                                }
                                else{
                                    //factory.getMoveLeftCommand(tank);

                                    queueMethods.offer(factory.getMoveLeftCommand(this));
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

                                    queueMethods.offer(factory.getMoveRightCommand(this));
                                }
                                else{
                                    //factory.getMoveLeftCommand(tank);
                                    queueMethods.offer(factory.getMoveLeftCommand(this));
                                }
                            }
                    }
                }
            }
        });
    }


    public void shoot(Direction direction) {
        queueMethods.offer(factory.getShootCommand(this, direction));
    }


    //сама реализация методов танка
    public void moveLeftExecute(){
        if(X-step>=0) {
            setDirection(Direction.LEFT);
            LOGGER.info("Начало проверки");
            if(checkForward(Direction.LEFT)){
                LOGGER.info("Начало движения");
                Point oldPoint = new Point(this.X,this.Y);
                LOGGER.info("Первоначальные координаты " + X+" "+Y);
                X=X-1;
                Point newpoint = new Point(this.X,this.Y);
                LOGGER.info("Объект, который должен сдвинуться " + gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(newpoint.hashCode(), gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(oldPoint.hashCode(),null);
                LOGGER.info("Объект, который сдвинулся " + game.findObject(X,Y)+" "+X+" "+Y);
                LOGGER.info("Конец движения");
            }
        }
    }

    public void moveRightExecute(){
        if(X+step<gameOptions.getWidth()) {
            setDirection(Direction.RIGHT);
            LOGGER.info("Начало проверки");
            if(checkForward(Direction.RIGHT)){
                LOGGER.info("Начало движения");
                Point oldPoint = new Point(this.X,this.Y);
                LOGGER.info("Первоначальные координаты " + this.X+" "+this.Y);
                this.X=this.X+1;
                Point newpoint = new Point(this.X,this.Y);
                LOGGER.info("Объект, который должен сдвинуться "+gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(newpoint.hashCode(), gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(oldPoint.hashCode(),null);
                LOGGER.info("Объект, который сдвинулся " + game.findObject(this.X,this.Y)+" "+this.X+" "+this.Y);
                LOGGER.info("Конец движения");
            }
        }
    }

    public void moveDownExecute(){
        if(Y-step>=0) {
            setDirection(Direction.DOWN);
            LOGGER.info("Начало проверки");
            if(checkForward(Direction.DOWN)){
                LOGGER.info("Начало движения");
                Point oldPoint = new Point(this.X,this.Y);
                LOGGER.info("Первоначальные координаты " + X+" "+Y);
                Y=Y-1;

                Point newpoint = new Point(this.X,this.Y);
                LOGGER.info("Объект, который должен сдвинуться "
                        +gameOptions.hashmap.get(oldPoint.hashCode())+" "
                        +gameOptions.hashmap.get(oldPoint.hashCode()).Y);
                gameOptions.hashmap.put(newpoint.hashCode(), gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(oldPoint.hashCode(),null);
                LOGGER.info("Объект, который сдвинулся " + game.findObject(X,Y)+" "+X+" "+Y);
                LOGGER.info("Конец движения");
            }
        }
    }

    public void moveUpExecute(){
        if(Y+step<gameOptions.getHeight()) {
            setDirection(Direction.UP);
            LOGGER.info("Начало проверки");
            if(checkForward(Direction.UP)){
                LOGGER.info("Начало движения");
                Point oldPoint = new Point(this.X,this.Y);
                LOGGER.info("Первоначальные координаты " + X+" "+Y);
                Y=Y+1;

                Point newpoint = new Point(this.X,this.Y);
                LOGGER.info("Объект, который должен сдвинуться " + gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(newpoint.hashCode(), gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(oldPoint.hashCode(),null);
                LOGGER.info("Объект, который сдвинулся " + game.findObject(X,Y)+" "+X+" "+Y);
                LOGGER.info("Конец движения");
            }
        }
    }

    public void turnExecute(Direction direction){
        setDirection(direction);
        LOGGER.info("Танк повернулся на " +direction);
    }

    public void shootExecute(Direction direction){
        switch (direction){
            case DOWN:
                if(Y-1>=0){
                    setDirection(Direction.DOWN);
                    Bullet bulletDOWN = new Bullet(X, Y-1, direction,false);
                    Point pointDOWN = new Point(X,Y-1);
                    if (gameOptions.hashmap.get(pointDOWN.hashCode()) == null){
                        gameOptions.hashmap.put(pointDOWN.hashCode(),bulletDOWN);
                        LOGGER.info("произошел выстрел вниз");
                        //выставляем флаг что первое появление пули
                        //gameOptions.hashmap.get(pointDOWN.hashCode()).setFirstSnapshot(true);
                        bulletDOWN.setFirstSnapshot(true);
                        game.addObject(bulletDOWN,X,Y-1,game.gameOptions);

                    }

                    break;
                }
                break;
            case UP:
                if(Y+1<gameOptions.getHeight()){
                    setDirection(Direction.UP);
                    Bullet bulletUP = new Bullet(X, Y+1, direction,false);
                    Point pointUP = new Point(X,Y+1);
                    if(gameOptions.hashmap.get(pointUP.hashCode()) == null){
                        gameOptions.hashmap.put(pointUP.hashCode(),bulletUP);
                        LOGGER.info("произошел выстрел вверх");
                        //выставляем флаг что первое появление пули
                        //gameOptions.hashmap.get(pointUP.hashCode()).setFirstSnapshot(true);
                        bulletUP.setFirstSnapshot(true);
                        game.addObject(bulletUP,X,Y+1,game.gameOptions);
                    }

                    break;
                }
               break;
            case RIGHT:
                if(X+1<gameOptions.getWidth()) {
                    setDirection(Direction.RIGHT);
                    Bullet bulletRIGHT = new Bullet(X+1, Y, direction,false);
                    Point pointRIGHT = new Point(X+1,Y);
                    if(gameOptions.hashmap.get(pointRIGHT.hashCode()) == null){
                        gameOptions.hashmap.put(pointRIGHT.hashCode(),bulletRIGHT);
                        LOGGER.info("произошел выстрел");
                        //выставляем флаг что первое появление пули
                        //gameOptions.hashmap.get(pointRIGHT.hashCode()).setFirstSnapshot(true);
                        bulletRIGHT.setFirstSnapshot(true);
                        game.addObject(bulletRIGHT,X+1,Y,game.gameOptions);
                    }

                    break;
                }
                break;
            case LEFT:
                if(X-1>=0) {
                    setDirection(Direction.LEFT);
                    Bullet bulletLEFT = new Bullet(X-1, Y, direction,false);
                    Point pointLEFT = new Point(X-1,Y);
                    if(gameOptions.hashmap.get(pointLEFT.hashCode()) == null){
                        gameOptions.hashmap.put(pointLEFT.hashCode(),bulletLEFT);
                        LOGGER.info("произошел выстрел");
                        //выставляем флаг что первое появление пули
                        bulletLEFT.setFirstSnapshot(true);
                        game.addObject(bulletLEFT,X-1,Y,game.gameOptions);
                    }

                    break;
                }
                break;
        }
    }

    //не используется
    public double getDistance(double X, double Y) {
        double dx=X-this.X,
                dy=Y-this.Y;
        return Math.sqrt(dx*dx+dy*dy);
    }

    @Override
    public String toString() {
        return "Tank{" +
                "id=" + id +
                ", idTeam=" + idTeam +
                ", step=" + step +
                ", health=" + health +
                ", isFlag=" + isFlag +
                ", living=" + living +
                ", direction=" + direction +
                '}';
    }
}
