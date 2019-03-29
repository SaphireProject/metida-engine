package metida.object;

import metida.interfacable.Gameable;

public class GameOptions implements Gameable {

    private int width;
    private int height;
    private int vision;
    private int[][] map;

    public GameOptions() {
    }


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

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public BaseObject findObject(int x , int y) {
        return null;
    }

    public GameOptions(int width , int height , int vision) {
        this.width = width;
        this.height = height;
        this.vision = vision;
        this.map = new int[width][height];
        for(int i=0;i<width;i++){
            for(int j=0; j<height;j++){
                map[i][j]=0;
            }
        }
    }

    public static int[][] getByRange (int[][] original, int xFrom, int xTo, int yFrom, int yTo){
        int[][] result = new int[xTo - xFrom][yTo - yFrom];
        for (int i = xFrom; i < xTo; i++){
            for (int j = yFrom; j < yTo; j++){
             result[i - xFrom][j - yFrom] = original[i][j];
            }
        }
        return result;
    }
}
