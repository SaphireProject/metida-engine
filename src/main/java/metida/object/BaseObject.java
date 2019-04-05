package metida.object;

public abstract class BaseObject  {
    int X;
    int Y;
    //boolean living;
    int health;
/*
    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }
*/

    GameOptions gameOptions;

    public void setGameOptions(GameOptions gameOptions) {
        this.gameOptions = gameOptions;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }

    void getHit(BaseObject obj){
        obj.setHealth(obj.getHealth()-1);
    }

    void action() {

    }

    @Override
    public String toString() {
        return "BaseObject{" +
                "X=" + X +
                ", Y=" + Y +
                ", health=" + health +
                '}';
    }
}
