package metida.object;

import java.lang.reflect.Type;

public abstract class BaseObject  {
/*
    Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
*/
    int X;
    int Y;
    boolean living;
    int health;

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
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

    void getHit(){

    }
}
