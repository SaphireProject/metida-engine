package metida.object;

import metida.interfacable.*;

import java.util.HashMap;

public class Tank extends BaseObject implements Movable, Activable, Checkable,Shootable {

    private int idTeam;
    private int damage=1;
    private int step=1;
    private int hp=5;

    private GameOptions gameOptions;

    public Tank(int idTeam) {
        this.idTeam = idTeam;
        this.damage = 1;
        this.step = 1;
        this.hp = 5;
    }

    public Tank() {
    }

    public int getHp() {
        return hp;
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
        if(Y-step>=0) {
            if(checkForward(Direction.UP)){
                super.Y=Y+1;
            }

        }

    }

    @Override
    public void moveDown() {
        super.Y=Y-1;
    }

    @Override
    public void moveRight() {
        super.X=X+1;
    }

    @Override
    public void moveLeft() {
        super.X=X-1;
    }

    public void turn() {

    }

    public HashMap<Integer,BaseObject> checkAround(int vis) {
        vis=gameOptions.getVision();
        HashMap<Integer,BaseObject> map = gameOptions.getByRange(gameOptions.getHashmap(),
                super.X-vis,super.X+vis,
                super.Y-vis,super.Y+vis);
        return map;
    }

    public boolean checkForward(Direction direction) {
        HashMap<Integer,BaseObject> map = checkAround(gameOptions.getVision());
        switch(direction){
            case UP:
                Point pointUP=new Point(super.X,super.Y+1);
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
            case LEFT:
                Point pointLEFT=new Point(super.X-1,super.Y);
                if (map.get(pointLEFT.hashCode())==null){
                    return true;
                }
                else{
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

    public void shoot() {
        Bullet bullet=new Bullet(super.X,super.Y, idTeam);

    }

    public double getDistance(double X, double Y) {
        double dx=X-this.X,
                dy=Y-this.Y;
        return Math.sqrt(dx*dx+dy*dy);
    }
    //map for танков юзеров, для пуль,
}
