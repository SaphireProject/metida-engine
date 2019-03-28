package metida.factory;

import metida.object.Wall;

public class WallFactory {

    private static WallFactory instance;

    public static WallFactory Initialize()
    {
        if (instance == null)
            instance = new WallFactory();
        return instance;
    }

    public Wall getWall(int X,int Y){
        return new Wall(X,Y);
    }
}
