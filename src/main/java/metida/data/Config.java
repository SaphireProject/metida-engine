package metida.data;

import java.util.List;

public class Config {
    private int lengthX;
    private int lengthY;
    private boolean obstacles;
    private int countPlayers;
    private boolean buff;
    private boolean debuff;
    private int maxTanksOnPlayer;
    private int vision;
    private List<Player> players;

    public int getLengthX() {
        return lengthX;
    }

    public void setLengthX(int lengthX) {
        this.lengthX = lengthX;
    }

    public int getLengthY() {
        return lengthY;
    }

    public void setLengthY(int lengthY) {
        this.lengthY = lengthY;
    }

    public boolean isObstacles() {
        return obstacles;
    }

    public void setObstacles(boolean obstacles) {
        this.obstacles = obstacles;
    }

    public int getCountPlayers() {
        return countPlayers;
    }

    public void setCountPlayers(int countPlayers) {
        this.countPlayers = countPlayers;
    }

    public boolean isBuff() {
        return buff;
    }

    public void setBuff(boolean buff) {
        this.buff = buff;
    }

    public boolean isDebuff() {
        return debuff;
    }

    public void setDebuff(boolean debuff) {
        this.debuff = debuff;
    }

    public int getMaxTanksOnPlayer() {
        return maxTanksOnPlayer;
    }

    public void setMaxTanksOnPlayer(int maxTanksOnPlayer) {
        this.maxTanksOnPlayer = maxTanksOnPlayer;
    }

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
