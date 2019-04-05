package metida.object;


import metida.interfacable.Direction;

public class GoldTank extends Tank {
    private int idTeam;
    private int damage=3;
    private int step=1;
    private int hp=10;
    private Direction direction;

    public GoldTank(int idTeam) {
        this.idTeam = idTeam;
        this.damage = 3;
        this.step = 1;
        this.hp = 10;
        this.direction=Direction.UP;
    }

    @Override
    public String toString() {
        return "GoldTank{" +
                "idTeam=" + idTeam +
                ", damage=" + damage +
                ", step=" + step +
                ", hp=" + hp +
                ", direction=" + direction +
                '}';
    }
}
