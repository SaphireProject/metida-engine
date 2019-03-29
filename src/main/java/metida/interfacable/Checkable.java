package metida.interfacable;

public interface Checkable {
    //ToDo: смотря что чекать, изменить формат
    int[][] checkAround(int vision);//ToDo: можно один метод для вснх типов объектов, или же разные методы
    boolean checkForward(Direction direction);

}
