package metida.interfacable;

import metida.object.BaseObject;

import java.util.HashMap;
import java.util.Map;

public interface Checkable {
    Map<Integer, BaseObject> checkAround(int vision);//ToDo: можно один метод для вснх типов объектов, или же разные методы
    boolean checkForward(Direction direction);

}
