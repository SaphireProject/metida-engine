package metida.object;

public class Bullet {
    private int speed=3;
    private int X;
    private int Y;
    private int idTeam;

    public Bullet(int X, int Y,int idTeam) {
        this.speed = 3;
        this.X=X;
        this.Y=Y;
        this.idTeam=idTeam;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }



    public int getSpeed() {
        return speed;
    }




}
