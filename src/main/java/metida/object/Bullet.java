package metida.object;


import metida.interfacable.Activable;
import metida.interfacable.Checkable;
import metida.interfacable.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;



public class Bullet extends BaseObject implements Activable, Checkable {
    private int speed;
    private int X;
    private int Y;
    private  int health;
    private boolean isLastSnapshot;
    private boolean isFirstSnapshot;

    private  boolean isFlag;
    private  boolean living;

    Direction direction;

    TypeObjects type;
    public Map<Integer, BaseObject> hashmap = new HashMap<>();
    private static Logger LOGGER = LoggerFactory.getLogger(Bullet.class);


    public Bullet(int X, int Y, Direction direction) {
        this.type=TypeObjects.BULLET;
        this.speed = 3;
        this.X=X;
        this.Y=Y;
        this.health=1;
        this.direction=direction;
    }

    public Bullet(int X , int Y , Direction direction, boolean isFlag) {
        this.type=TypeObjects.BULLET;
        this.speed = 3;
        this.X=X;
        this.Y=Y;
        this.health=1;
        this.direction=direction;
        this.isFlag = isFlag;
    }

    @Override
    public boolean isLastSnapshot() {
        return isLastSnapshot;
    }

    @Override
    public void setLastSnapshot(boolean lastSnapshot) {
        isLastSnapshot = lastSnapshot;
    }

    @Override
    public boolean isFirstSnapshot() {
        return isFirstSnapshot;
    }

    @Override
    public void setFirstSnapshot(boolean firstSnapshot) {
        isFirstSnapshot = firstSnapshot;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public TypeObjects getType() {
        return type;
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
                    Bullet bulletLEFT = new Bullet(this.X-1, this.Y, direction,false);
                    Point pointLEFT = new Point(this.X-1, this.Y);
                    Point oldPointLEFT= new Point(this.X, this.Y);
                    gameOptions.hashmap.put(pointLEFT.hashCode(), bulletLEFT);
                    LOGGER.info("координата Х пули " + bulletLEFT.X);
                    gameOptions.hashmap.put(oldPointLEFT.hashCode(), null);
                    LOGGER.info("переместилась ли пуля(должна исчезнуть) " + game.findObject(X,Y));
                    LOGGER.info("переместилась ли пуля(должна появиться тут) " + game.findObject(X-1,Y));

                    //Добавили новую пулю в отдельную мапу, чтобы потом добавить в основную
                    game.addObjectAdd(bulletLEFT ,X-1, Y, game.gameOptions);

                    //Добавили чтобы удалить старую пулю
                    game.removeObjectOld(game.objects.get(oldPointLEFT.hashCode()),X,Y,game.gameOptions);

                    break;
                case RIGHT:
                    Bullet bulletRIGHT = new Bullet(this.X+1,this.Y,this.direction);
                    Point pointRIGHT = new Point(this.X+1,this.Y);
                    Point oldPointRIGHT= new Point(this.X,this.Y);
                    gameOptions.hashmap.put(pointRIGHT.hashCode(), bulletRIGHT);
                    gameOptions.hashmap.put(oldPointRIGHT.hashCode(),null);
                    LOGGER.info("переместилась ли пуля(должна исчезнуть) " + game.findObject(X,Y));
                    LOGGER.info("переместилась ли пуля(должна появиться тут) " + game.findObject(X+1,Y));

                    //Добавили новую пулю в отдельную мапу, чтобы потом добавить в основную
                    game.addObjectAdd(bulletRIGHT ,X+1, Y, game.gameOptions);

                    //Добавили чтобы удалить старую пулю
                    game.removeObjectOld(game.objects.get(oldPointRIGHT.hashCode()),X,Y,game.gameOptions);
                    break;
                case UP:
                    Bullet bulletUP = new Bullet(this.X,this.Y+1, this.direction);
                    Point pointUP = new Point(this.X,this.Y+1);
                    Point oldPointUP= new Point(this.X,this.Y);

                    gameOptions.hashmap.put(pointUP.hashCode(), bulletUP);
                    gameOptions.hashmap.put(oldPointUP.hashCode(),null);

                    LOGGER.info("переместилась ли пуля(должна исчезнуть) " + game.findObject(X,Y));
                    LOGGER.info("переместилась ли пуля(должна появиться тут) " + game.findObject(X,Y+1));

                    //Добавили новую пулю в отдельную мапу, чтобы потом добавить в основную
                    game.addObjectAdd(bulletUP ,X, Y+1, game.gameOptions);

                    //Добавили чтобы удалить старую пулю
                    game.removeObjectOld(game.objects.get(oldPointUP.hashCode()),X,Y,game.gameOptions);
                    break;
                case DOWN:
                    Bullet bulletDOWN = new Bullet(this.X,this.Y-1,this.direction);
                    Point pointDOWN = new Point(this.X,this.Y-1);
                    Point oldPointDOWN= new Point(this.X,this.Y);
                    gameOptions.hashmap.put(pointDOWN.hashCode(), bulletDOWN);
                    gameOptions.hashmap.put(oldPointDOWN.hashCode(),null);

                    LOGGER.info("переместилась ли пуля(должна исчезнуть) " + game.findObject(X,Y));
                    LOGGER.info("переместилась ли пуля(должна появиться тут) " + game.findObject(X,Y-1));

                    //Добавили новую пулю в отдельную мапу, чтобы потом добавить в основную
                    game.addObjectAdd(bulletDOWN ,X, Y-1, game.gameOptions);

                    //Добавили чтобы удалить старую пулю
                    game.removeObjectOld(game.objects.get(oldPointDOWN.hashCode()),X,Y,game.gameOptions);
                    break;
            }
        }
    }

    @Override
    public Map<Integer, BaseObject> checkAround(int vision) {
        return null;
    }

    Random random = new Random();

    @Override
    public boolean checkForward(Direction direction) {
        switch(direction){
            case UP:
                if(Y+1<=gameOptions.getHeight()){
                    Point pointUP=new Point(this.X,this.Y+1);
                    Point pointUPold=new Point(this.X, this.Y);

                    if (gameOptions.hashmap.get(pointUP.hashCode()) == null){
                        return true;
                    }
                    else {
                        LOGGER.info("Движение невозможно в координату: " + (this.X) + " " + (Y+1));
                        //получаем встреченнный объект
                        BaseObject base = gameOptions.hashmap.get(pointUP.hashCode());
                        LOGGER.info("health old " + gameOptions.hashmap.get(pointUP.hashCode()).getHealth());
                        //возможна нужна проверка что если флаг false
                        if(base.getType()==TypeObjects.BULLET & base.isFlag() == true){
                            //чтобы перезаписывалась или встречная пуля, или текущая
                            int c=random.nextInt(1);
                            if(c==1) {
                                LOGGER.info("health new " + gameOptions.hashmap.get(pointUP.hashCode()).getHealth());
                                LOGGER.info("Не удаляем пулю, которая встретила пулю " +  gameOptions.hashmap.get(pointUPold.hashCode()));
                                return true;
                            }
                        }
                        base.getHit(gameOptions.hashmap.get(pointUP.hashCode()));
                        LOGGER.info("health new " +
                                gameOptions.hashmap.get(pointUP.hashCode()).getHealth());
                        LOGGER.info("удаляем пулю, которая встретила препятствие " +
                                gameOptions.hashmap.get(pointUPold.hashCode()));
                        gameOptions.hashmap.put(pointUPold.hashCode(), null);//удаляем пулю с карты
                        //todo: когда буду делать мапу с пулями, надо будет прибавлять эту мапу
                        //выставляем флаг на то что последний кадр пули
                        game.objects.get(pointUPold.hashCode()).setLastSnapshot(true);
                        //добавляем на удаление
                        game.removeObjectOld(game.objects.get(pointUPold.hashCode()), X, Y, game.gameOptions);
                        LOGGER.info("Повредился ли объект: " +
                                gameOptions.hashmap.get(pointUP.hashCode()));
                        return false;
                    }
                }
                Point pointUPoldDel=new Point(this.X, this.Y);
                gameOptions.hashmap.put(pointUPoldDel.hashCode(), null);//удаляем пулю с карты
                //выставляем флаг на то что последний кадр пули
                game.objects.get(pointUPoldDel.hashCode()).setLastSnapshot(true);
                //добавляем на удаление
                game.removeObjectOld(game.objects.get(pointUPoldDel.hashCode()), X, Y, game.gameOptions);
                return false;

            case DOWN:
                if(Y-1>=0){
                    Point pointDOWN=new Point(this.X,this.Y-1);
                    Point pointDOWNold=new Point(this.X,this.Y);
                    LOGGER.info("hash"+ pointDOWN.hashCode());
                    if (gameOptions.hashmap.get(pointDOWN.hashCode())==null){
                        return true;
                    }
                    else {
                        LOGGER.info("Движение невозможно в координату: " + (this.X) + " " + (Y-1));

                        BaseObject base=gameOptions.hashmap.get(pointDOWN.hashCode());
                        LOGGER.info("health old " + gameOptions.hashmap.get(pointDOWN.hashCode()).getHealth());

                        //возможна нужна проверка что если флаг false
                        if(base.getType()==TypeObjects.BULLET & base.isFlag() == true){
                            //чтобы перезаписывалась или встречная пуля, или текущая
                            int c=random.nextInt(1);
                            if(c==1) {
                                LOGGER.info("health new " + gameOptions.hashmap.get(pointDOWN.hashCode()).getHealth());
                                LOGGER.info("Не удаляем пулю, которая встретила пулю " +  gameOptions.hashmap.get(pointDOWNold.hashCode()));
                                return true;
                            }
                        }
                        base.getHit(gameOptions.hashmap.get(pointDOWN.hashCode()));
                        LOGGER.info("health new " + gameOptions.hashmap.get(pointDOWN.hashCode()).getHealth());
                        LOGGER.info("удаляем пулю, которая встретила препятствие " +  gameOptions.hashmap.get(pointDOWNold.hashCode()));
                        gameOptions.hashmap.put(pointDOWNold.hashCode(),null);//удаляем пулю с карты

                        //выставляем флаг на то что последний кадр пули
                        game.objects.get(pointDOWNold.hashCode()).setLastSnapshot(true);

                        game.removeObjectOld(game.objects.get(pointDOWNold.hashCode()),X,Y,game.gameOptions);
                        LOGGER.info("Повредился ли объект: "+gameOptions.hashmap.get(pointDOWN.hashCode()));
                        return false;
                    }
                }
                Point pointDOWNoldDel=new Point(this.X,this.Y);
                gameOptions.hashmap.put(pointDOWNoldDel.hashCode(),null);//удаляем пулю с карты

                //выставляем флаг на то что последний кадр пули
                game.objects.get(pointDOWNoldDel.hashCode()).setLastSnapshot(true);

                game.removeObjectOld(game.objects.get(pointDOWNoldDel.hashCode()),X,Y,game.gameOptions);
                return  false;


            case LEFT:
                if(X-1>=0) {
                    Point pointLEFT=new Point(this.X-1,this.Y);
                    Point pointLEFTold=new Point(this.X,this.Y);
                    LOGGER.info("hash"+ pointLEFT.hashCode());
                    if (gameOptions.hashmap.get(pointLEFT.hashCode())==null){
                        return true;
                    }
                    else {
                        LOGGER.info("Движение невозможно в координату: " + (this.X-1) + " " + (Y));
                        BaseObject base=gameOptions.hashmap.get(pointLEFT.hashCode());
                        LOGGER.info("health old " + gameOptions.hashmap.get(pointLEFT.hashCode()).getHealth());
                        //возможна нужна проверка что если флаг false
                        if(base.getType()==TypeObjects.BULLET & base.isFlag() == true){
                            //чтобы перезаписывалась или встречная пуля, или текущая
                            int c=random.nextInt(1);
                            if(c==1) {
                                LOGGER.info("health new " +
                                        gameOptions.hashmap.get(pointLEFT.hashCode()).getHealth());
                                LOGGER.info("Не удаляем пулю, которая встретила пулю " +
                                        gameOptions.hashmap.get(pointLEFTold.hashCode()));
                                return true;
                            }
                        }

                        base.getHit(gameOptions.hashmap.get(pointLEFT.hashCode()));
                        LOGGER.info("health new " + gameOptions.hashmap.get(pointLEFT.hashCode()).getHealth());
                        LOGGER.info("удаляем пулю, которая встретила препятствие " +  gameOptions.hashmap.get(pointLEFTold.hashCode()));
                        gameOptions.hashmap.put(pointLEFTold.hashCode(),null);//удаляем пулю с карты

                        //выставляем флаг на то что последний кадр пули
                        game.objects.get(pointLEFTold.hashCode()).setLastSnapshot(true);

                        game.removeObjectOld(game.objects.get(pointLEFTold.hashCode()),X,Y,game.gameOptions);
                        LOGGER.info("Повредился ли объект: "+gameOptions.hashmap.get(pointLEFT.hashCode()));
                        return false;
                    }
                }
                Point pointLEFToldDel=new Point(this.X,this.Y);
                gameOptions.hashmap.put(pointLEFToldDel.hashCode(),null);//удаляем пулю с карты

                //выставляем флаг на то что последний кадр пули
                game.objects.get(pointLEFToldDel.hashCode()).setLastSnapshot(true);

                game.removeObjectOld(game.objects.get(pointLEFToldDel.hashCode()),X,Y,game.gameOptions);
                return false;

            case RIGHT:
                if(X+1<=gameOptions.getWidth()) {
                    Point pointRIGHT=new Point(this.X+1,this.Y);
                    Point pointRIGHTold=new Point(this.X,this.Y);
                    LOGGER.info("hash"+ pointRIGHT.hashCode());
                    if (gameOptions.hashmap.get(pointRIGHT.hashCode())==null){
                        return true;
                    }
                    else {
                        LOGGER.info("Движение невозможно в координату: " + (this.X+1) + " " + (Y));
                        BaseObject base=gameOptions.hashmap.get(pointRIGHT.hashCode());
                        LOGGER.info("health old " + gameOptions.hashmap.get(pointRIGHT.hashCode()).getHealth());

                        //возможна нужна проверка что если флаг false
                        if(base.getType()==TypeObjects.BULLET & base.isFlag() == true){
                            //чтобы перезаписывалась или встречная пуля, или текущая
                            int c=random.nextInt(1);
                            if(c==1) {
                                LOGGER.info("health new " +
                                        gameOptions.hashmap.get(pointRIGHT.hashCode()).getHealth());
                                LOGGER.info("Не удаляем пулю, которая встретила пулю " +
                                        gameOptions.hashmap.get(pointRIGHTold.hashCode()));
                                return true;
                            }
                        }

                        base.getHit(gameOptions.hashmap.get(pointRIGHT.hashCode()));
                        LOGGER.info("health new " + gameOptions.hashmap.get(pointRIGHT.hashCode()).getHealth());
                        LOGGER.info("удаляем пулю, которая встретила препятствие " +  gameOptions.hashmap.get(pointRIGHTold.hashCode()));
                        gameOptions.hashmap.put(pointRIGHTold.hashCode(),null);//удаляем пулю с карты

                        //выставляем флаг на то что последний кадр пули
                        game.objects.get(pointRIGHTold.hashCode()).setLastSnapshot(true);


                        game.removeObjectOld(game.objects.get(pointRIGHTold.hashCode()),X,Y,game.gameOptions);
                        LOGGER.info("Повредился ли объект: "+gameOptions.hashmap.get(pointRIGHT.hashCode()));
                        return false;
                    }
                }
                Point pointRIGHToldDel=new Point(this.X,this.Y);
                gameOptions.hashmap.put(pointRIGHToldDel.hashCode(),null);//удаляем пулю с карты

                //выставляем флаг на то что последний кадр пули
                game.objects.get(pointRIGHToldDel.hashCode()).setLastSnapshot(true);


                game.removeObjectOld(game.objects.get(pointRIGHToldDel.hashCode()),X,Y,game.gameOptions);
                return false;

        }
        return false;
    }

    @Override
    public void checkShoot(Direction direction) {

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
