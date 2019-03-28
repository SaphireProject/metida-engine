package metida.data;

public class Player {
    private String path;
    private int idTeam;
    private boolean goldStatus;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public boolean isGoldStatus() {
        return goldStatus;
    }

    public void setGoldStatus(boolean goldStatus) {
        this.goldStatus = goldStatus;
    }

    @Override
    public String toString() {
        return "Player{" +
                "path='" + path + '\'' +
                ", idTeam=" + idTeam +
                ", goldStatus=" + goldStatus +
                '}';
    }

    public Player(String path , int idTeam , boolean goldStatus) {
        this.path = path;
        this.idTeam = idTeam;
        this.goldStatus = goldStatus;
    }
}
