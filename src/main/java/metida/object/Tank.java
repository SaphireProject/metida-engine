package metida.object;

import metida.interfacable.*;

public class Tank extends BaseObject implements Movable, Activable, Distancable, Checkable,Shootable {

    private int idTeam;
    private int damage=1;
    private int step=1;
    private int hp;

    public Tank(int idTeam) {
        this.idTeam = idTeam;
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


    public double GetDistance(double X, double Y) {
        double dx=X-this.X,
                dy=Y-this.Y;
        return Math.sqrt(dx*dx+dy*dy);
    }

    public void action() {

    }

    public void getDistance() {

    }

    public void move() {

    }

    public void turn() {

    }

    public boolean checkAround() {
        return false;
    }

    public boolean checkForward(Direction direction) {
        return false;
    }

    public void shoot() {

    }
}
