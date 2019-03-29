package metida.object;



public class GoldTank extends Tank {
    private int idTeam;
    private int damage=3;
    private int step=1;
    private int hp=10;

    public GoldTank(int idTeam) {
        this.idTeam = idTeam;
        this.damage = 3;
        this.step = 1;
        this.hp = 10;
    }
}
