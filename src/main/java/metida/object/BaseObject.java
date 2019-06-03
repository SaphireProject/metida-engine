package metida.object;

import metida.interfacable.Direction;

public abstract class BaseObject  {
    TypeObjects type;
    int X;
    int Y;
    boolean living=true;
    int health;
    boolean isFlag=false;
    Direction direction;
    boolean isLastSnapshot;
    boolean isFirstSnapshot;
    int id;
    String color;
    int idTeam;

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFlagAction(){
        if(isFlag==false){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isLiving() {
        if(health>0) {
            return true;
        }
        return false;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public TypeObjects getType() {
        return type;
    }

    public void setType(TypeObjects type) {
        this.type = type;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

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
//        obj.setHealth(obj.getHealth()-1);
    }

    public boolean isLastSnapshot() {
        return isLastSnapshot;
    }

    public void setLastSnapshot(boolean lastSnapshot) {
        isLastSnapshot = lastSnapshot;
    }

    public boolean isFirstSnapshot() {
        return isFirstSnapshot;
    }

    public void setFirstSnapshot(boolean firstSnapshot) {
        isFirstSnapshot = firstSnapshot;
    }

    void action() {

    }

    @Override
    public String toString() {
        return "BaseObject{" +
                "type=" + type +
                ", X=" + X +
                ", Y=" + Y +
                ", living=" + living +
                ", health=" + health +
                ", isFlag=" + isFlag +
                ", direction=" + direction +
                ", isLastSnapshot=" + isLastSnapshot +
                ", isFirstSnapshot=" + isFirstSnapshot +
                '}';
    }
}
