package metida.interfacable;

public interface Checkable {
    //ToDo: смотря что чекать, изменить формат
    boolean checkAround();//ToDo: можно один метод для вснх типов объектов, или же разные методы
    boolean checkForward(Direction direction);

}
