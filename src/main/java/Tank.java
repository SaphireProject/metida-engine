import org.json.JSONArray;
import org.json.JSONObject;

public class Tank {
    private String name;
    private float health;
    private float damage;
    private String strategy;
    private int x;
    private int y;
    private int length;
    private int deg;

    Tank(String name, float health, float damage, String strategy, int x, int y, int length, int deg) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.strategy = strategy;
        this.x = x;
        this.y = y;
        this.length = length;
        this.deg = deg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public int getMove(String moving) {
        int move;
        JSONObject obj = new JSONObject(this.strategy);
        JSONArray array = obj.getJSONArray("strategy");
        move = Integer.valueOf(String.valueOf(obj.get(moving)));
        return move;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLengthStrategy() {
        JSONObject obj = new JSONObject(this.strategy);
        JSONArray array = obj.getJSONArray("strategy");

        return array.length();
    }

    public void setLengthStrategy(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

}
