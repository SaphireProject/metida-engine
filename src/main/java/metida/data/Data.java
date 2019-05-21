package metida.data;

import java.util.ArrayList;
import java.util.List;

public class Data {
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

    public boolean isDubaff() {
        return debuff;
    }

    public void setDebuff(boolean debaff) {
        this.debuff = debaff;
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

    public void setPlayerList(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Data{" +
                "lengthX=" + lengthX +
                ", lengthY=" + lengthY +
                ", obstacles=" + obstacles +
                ", countPlayers=" + countPlayers +
                ", buff=" + buff +
                ", debuff=" + debuff +
                ", maxTanksOnPlayer=" + maxTanksOnPlayer +
                ", vision=" + vision +
                ", playerList=" + players +
                '}';
    }

    public Data() {
    }

    public Data(int lengthX , int lengthY , boolean obstacles , int countPlayers ,
                boolean buff , boolean debuff , int maxTanksOnPlayer , int vision , ArrayList<Player> players) {
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        this.obstacles = obstacles;
        this.countPlayers = countPlayers;
        this.buff = buff;
        this.debuff = debuff;
        this.maxTanksOnPlayer = maxTanksOnPlayer;
        this.vision = vision;
        this.players = players;
    }

}
