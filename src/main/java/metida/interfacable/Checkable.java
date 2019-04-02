package metida.interfacable;

import metida.object.BaseObject;

import java.util.HashMap;

public interface Checkable {
    //ToDo: смотря что чекать, изменить формат
    HashMap<Integer, BaseObject> checkAround(int vision);//ToDo: можно один метод для вснх типов объектов, или же разные методы
    boolean checkForward(Direction direction);

}
