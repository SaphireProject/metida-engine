package metida.data;

import java.util.List;

public class UserConfig {
    public String id;
    //public List<String> strategyPaths;
    public String strategyPaths;
}
/*class MoveUpCommand{
- TypeCommand type
+MoveUpCommand(Tank tank)
+execute()
}
class ShootCommand{
-Direction direction
- TypeCommand type
+ShootCommand(Tank tank, Direction direction)
+execute()
}
class MoveRightCommand{
- TypeCommand type
+MoveRightCommand(Tank tank)
+execute()
}
class MoveLeftCommand{
- TypeCommand type
+MoveLeftCommand(Tank tank)
+execute()
}
class TurnCommand{
-Direction direction
- TypeCommand type
+TurnCommand(Tank tank, Direction direction)
+execute()
}
CommandFactory->MoveRightCommand:создает
CommandFactory->MoveUpCommand:создает
CommandFactory->MoveLeftCommand:создает
CommandFactory->TurnCommand:создает
CommandFactory->ShootCommand:создает
MoveRightCommand-|>TankCommands
MoveUpCommand-|>TankCommands
MoveLeftCommand-|>TankCommands
TurnCommand-|>TankCommands
ShootCommand-|>TankCommands
*/