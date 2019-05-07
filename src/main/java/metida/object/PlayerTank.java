package metida.object;

import metida.factory.CommandFactory;
import metida.interfacable.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerTank extends Tank {

    private static Logger LOGGER = LoggerFactory.getLogger(PlayerTank.class);

    public PlayerTank() {
        LOGGER.info("Вызван конструктор");
    }
    //CommandFactory factory=new CommandFactory();

    public void turn(Direction direction){
        setDirection(direction);
        LOGGER.info("Танк повернулся на " +direction);
    }

    public void moveLeft(){
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

    public void moveRight(){
        if(X+step<=gameOptions.getWidth()) {
            setDirection(Direction.RIGHT);
            if(checkForward(Direction.RIGHT)){
                LOGGER.info("Начало движения");
                Point oldPoint = new Point(X,Y);
                X=X+1;

                Point newpoint = new Point(X,Y);
                LOGGER.info("Объект, который должен сдвинуться "+gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(newpoint.hashCode(), gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(oldPoint.hashCode(),null);
            }
        }
    }

    public void moveUp(){
        if(Y+step<=gameOptions.getHeight()) {
            setDirection(Direction.UP);
            if(checkForward(Direction.UP)){
                LOGGER.info("Начало движения");
                Point oldPoint = new Point(X,Y);
                Y=Y+1;

                Point newpoint = new Point(X,Y);
                LOGGER.info("Объект, который должен сдвинуться " + gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(newpoint.hashCode(), gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(oldPoint.hashCode(),null);
                LOGGER.info("Объект, который сдвинулся " + game.findObject(X,Y));
            }
        }
    }

    public void moveDown(){
        if(Y-step>=0) {
            setDirection(Direction.DOWN);
            if(checkForward(Direction.DOWN)){
                LOGGER.info("Начало движения");
                Point oldPoint = new Point(X,Y);
                Y=Y-1;

                Point newpoint = new Point(X,Y);
                LOGGER.info("Объект, который должен сдвинуться "
                        +gameOptions.hashmap.get(oldPoint.hashCode())+" "
                        +gameOptions.hashmap.get(oldPoint.hashCode()).Y);
                gameOptions.hashmap.put(newpoint.hashCode(), gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(oldPoint.hashCode(),null);
            }
        }
    }

    public void shoot(Direction direction){

        switch (direction){
            case DOWN:
                Bullet bulletDOWN = new Bullet(X, Y-1, direction,false);
                Point pointDOWN = new Point(X,Y-1);
                gameOptions.hashmap.put(pointDOWN.hashCode(),bulletDOWN);
                LOGGER.info("произошел выстрел вниз");
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

/*@Override
    public void moveUp() {
        if(Y+step<=gameOptions.getHeight()) {
            setDirection(Direction.UP);
            if(checkForward(Direction.UP)){
                LOGGER.info("Начало движения");
                Point oldPoint = new Point(X,Y);
                Y=Y+1;

                Point newpoint = new Point(X,Y);
                LOGGER.info("Объект, который должен сдвинуться " + gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(newpoint.hashCode(), gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(oldPoint.hashCode(),null);
                LOGGER.info("Объект, который сдвинулся " + game.findObject(X,Y));
            }
        }
    }

    @Override
    public void moveDown() {
        if(Y-step>=0) {
            setDirection(Direction.DOWN);
            if(checkForward(Direction.DOWN)){
                LOGGER.info("Начало движения");
                Point oldPoint = new Point(X,Y);
                Y=Y-1;

                Point newpoint = new Point(X,Y);
                LOGGER.info("Объект, который должен сдвинуться "
                        +gameOptions.hashmap.get(oldPoint.hashCode())+" "
                        +gameOptions.hashmap.get(oldPoint.hashCode()).Y);
                gameOptions.hashmap.put(newpoint.hashCode(), gameOptions.hashmap.get(oldPoint.hashCode()));
                gameOptions.hashmap.put(oldPoint.hashCode(),null);
            }
        }
    }

    @Override
    public void moveRight() {
        if(X+step<=gameOptions.getWidth()) {
            setDirection(Direction.RIGHT);
            if(checkForward(Direction.RIGHT)){
                LOGGER.info("Начало движения");
                Point oldPoint = new Point(X,Y);
                X=X+1;

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
        switch (direction){
            case DOWN:
                Bullet bulletDOWN = new Bullet(X, Y-1, direction,false);
                Point pointDOWN = new Point(X,Y-1);
                gameOptions.hashmap.put(pointDOWN.hashCode(),bulletDOWN);
                LOGGER.info("произошел выстрел вниз");
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


    }*/
}
