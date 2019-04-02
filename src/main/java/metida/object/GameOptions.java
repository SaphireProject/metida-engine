package metida.object;

import metida.interfacable.Gameable;

import java.util.HashMap;
import java.util.Map;

public class GameOptions implements Gameable {

    private int width;
    private int height;
    private int vision;
    public HashMap<Integer, BaseObject> hashmap = new HashMap<>();


    public GameOptions() {
    }

    public HashMap<Integer, BaseObject> getHashmap() {
        return hashmap;
    }

    public void setHashmap(HashMap<Integer, BaseObject> hashmap) {
        this.hashmap = hashmap;
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

    public BaseObject findObject(int x , int y) {
        return null;
    }

    public GameOptions(int width , int height , int vision) {
        this.width = width;
        this.height = height;
        this.vision = vision;
        this.hashmap=new HashMap<>();

        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++) {
                Point point=new Point(width,height);
                hashmap.put(point.hashCode(), null);
            }
        }

    }

    public HashMap<Integer, BaseObject> getByRange (HashMap<Integer, BaseObject> map, int xFrom, int xTo, int yFrom, int yTo){
        HashMap<Integer, BaseObject> newmap = new HashMap<>();
        for (int i = xFrom; i < xTo; i++){
            for (int j = yFrom; j < yTo; j++){
                Point point=new Point(i - xFrom,j - yFrom);
                Point pointOriginal=new Point(i,j );
                newmap.put(point.hashCode(), map.get(pointOriginal.hashCode()));
            }
        }
        return newmap;
    }
}
