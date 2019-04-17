package metida.object;

import java.util.LinkedList;
import java.util.List;

/**
 * Хранитель методов из стратегии
 *
 * **/
public class ListCommandsStrategy {
    //ToDo: другую коллекцию для хранения
    private final List<TankCommands> steps = new LinkedList<>();
    private final List<TankCommands> path = new LinkedList<>();

    public ListCommandsStrategy registerStep(TankCommands step) {
        steps.add(step);
        return this;
    }

    public void go() {
        for(TankCommands step : steps) {
            step.execute();
            ((LinkedList)path).addFirst(step);
        }
        //Todo: Наверно не надо удалять
        steps.clear();
    }
    //ToDo: взял просто их примера, хз понадобится ли
    public void goBack() {
        for(TankCommands step : path) {
            step.execute();
        }
        path.clear();
    }
}
