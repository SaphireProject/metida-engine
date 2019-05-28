package metida.object;

import metida.interfacable.Direction;
import metida.interfacable.Gameable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;



public class GameOptions implements Gameable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameOptions.class);
    private int width;
    private int height;
    private int vision;
    public Map<Integer, BaseObject> hashmap = new HashMap<>();



    public GameOptions() {
    }

    public Map<Integer, BaseObject> getHashmap() {
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

    public GameOptions(int width , int height) {
        this.width = width;
        this.height = height;
        //this.vision = vision;
        this.hashmap = new HashMap<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Point point = new Point(i , j);
                hashmap.put(point.hashCode() , null);
            }
        }

        //добавляем стены
        //System.out.println(Collections.singletonList(hashmap));
    }

    public void setHashmap(Map<Integer, BaseObject> hashmap) {
        this.hashmap = hashmap;
    }

    public Map<Integer, BaseObject> getByRange(Map<Integer, BaseObject> map ,
                                               int xFrom , int xTo ,
                                               int yFrom , int yTo) {
        Map<Integer, BaseObject> newmap = new HashMap<>();
        for (int i = xFrom; i <=xTo; i++) {
            for (int j = yFrom; j <= yTo; j++) {
                Point point = new Point(i - xFrom , j - yFrom);
                Point pointOriginal = new Point(i , j);
                newmap.put(point.hashCode() , map.get(pointOriginal.hashCode()));
            }
        }
        return newmap;
    }

    public Map<Integer, BaseObject> getByForward(Map<Integer, BaseObject> map,
                                                     Direction direction, int x, int y) {
        Map<Integer, BaseObject> newmap = new HashMap<>();
        switch (direction) {
            case UP:
                for(int i=y+1;i<height;i++){
                    Point point=new Point(x,i);
                    //Point pointNew=new Point(x,y);
                    newmap.put(point.hashCode(),map.get(point.hashCode()));
                }
                return newmap;
            case DOWN:
                for(int i=y-1;i>0;i--){
                    Point point=new Point(x,i);
                    newmap.put(point.hashCode(),map.get(point.hashCode()));
                }
                return newmap;
            case LEFT:
                for(int i=x-1;i>0;i--){
                    Point point=new Point(i,y);
                    //Point pointNew=new Point(x,y);
                    newmap.put(point.hashCode(),map.get(point.hashCode()));
                }
                return newmap;
            case RIGHT:
                for(int i=x+1;i<width;i++){
                    Point point=new Point(i,y);
                    newmap.put(point.hashCode(),map.get(point.hashCode()));
                }
                return newmap;
        }
        return  null;
    }


}
