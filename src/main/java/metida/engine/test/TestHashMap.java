package metida.engine.test;

import java.util.HashMap;
import java.util.Map;

public class TestHashMap {

    Map<String, Object> fieldMap = new HashMap();

    Map<String, Tank> tankMap = new HashMap();
    Map<String, Bullet> bulletHashMap = new HashMap();

    Tank tank;

    public void test() {

        String key = 1 + " " + 2;
        tank = new Tank();
        fieldMap.put(key, tank);

        Object tmp = fieldMap.get(key);

        if (tmp instanceof Tank) {
            ((Tank) tmp).setName("lol");
            System.out.println(((Tank) tmp).getName());
        } else if (tmp instanceof Bullet) {
            ((Bullet) tmp).setName("kek");
            System.out.println(((Bullet) tmp).getName());
        }
    }
}