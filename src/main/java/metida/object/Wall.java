package metida.object;

public class Wall extends BaseObject {
    int X;
    int Y;
    int health;

    public Wall(int x , int y) {
        X = x;
        Y = y;
        this.health = 5;
    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public int getY() {
        return Y;
    }

    @Override
    public int getHealth() {
        return health;
    }

}
