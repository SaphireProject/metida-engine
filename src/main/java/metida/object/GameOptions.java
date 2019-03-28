package metida.object;

import metida.interfacable.Gameable;

public class GameOptions implements Gameable {

    private int width;
    private int height;
    private int vision;
    private int[] map;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }

    public int[] getMap() {
        return map;
    }

    public void setMap(int[] map) {
        this.map = map;
    }

    public BaseObject findObject(int x , int y) {
        return null;
    }
}
