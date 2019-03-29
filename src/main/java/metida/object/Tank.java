package metida.object;

import metida.interfacable.*;

import static metida.interfacable.Direction.DOWN;
import static metida.object.GameOptions.getByRange;

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

    public int[][] checkAround(int vis) {
        vis=gameOptions.getVision();
        int[][] map=getByRange(gameOptions.getMap(),
                super.X-vis,super.X+vis,
                super.Y-vis,super.Y+vis);
        //ToDo: как отличать объекты, сделать ли отдельные методы для всего,

        return map;
    }

    public boolean checkForward(Direction direction) {
        int map[][];
        map=checkAround(gameOptions.getVision());
        switch(direction){
            case UP:
                if (map[super.X][super.Y+1]==0){
                    return true;
                }
                else{
                    return false;
                }
            case DOWN:
                if (map[super.X][super.Y-1]==0){
                    return true;
                }
                else{
                    return false;
                }
            case LEFT:
                if (map[super.X-1][super.Y]==0){
                    return true;
                }
                else{
                    return false;
                }
            case RIGHT:
                if (map[super.X+1][super.Y]==0){
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
}
