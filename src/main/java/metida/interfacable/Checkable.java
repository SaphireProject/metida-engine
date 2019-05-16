package metida.interfacable;

import metida.object.BaseObject;

import java.util.HashMap;
import java.util.Map;

public interface Checkable {
    Map<Integer, BaseObject> checkAround(int vision);
    boolean checkForward(Direction direction);

    void checkShoot(Direction direction);

}
