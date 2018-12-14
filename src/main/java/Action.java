import org.json.JSONArray;

import java.util.Random;

public class Action {

    int lengthMapX;
    int lengthMapY;

    public void action(Tank tank, Tank enemy, int lengthMapX, int lengthMapY) {

        this.lengthMapX = lengthMapX + 1;
        this.lengthMapY = lengthMapY + 1;

        Random random = new Random();
        int line = random.nextInt(tank.getLengthStrategy());

        org.json.JSONObject obj = new org.json.JSONObject(tank.getStrategy());
        JSONArray array = obj.getJSONArray("strategy");
        org.json.JSONObject getObj = array.getJSONObject(line);
        String str = getObj.toString();

        //движение направо
        if (str.toLowerCase().contains("left_move".toLowerCase())) {
            int getMove = getObj.getInt("left_move");
            int getX = tank.getX();
            int getY = tank.getY();

            int[] res = moving(270, getX, getY, getMove, enemy);

            getX = res[0];
            tank.setX(getX);

            getY = res[1];
            tank.setY(getY);

            tank.setDeg(270);
        }

        //движение налево
        if (str.toLowerCase().contains("right_move".toLowerCase())) {
            int getMove = getObj.getInt("right_move");
            int getX = tank.getX();
            int getY = tank.getY();

            int[] res = moving(90, getX, getY, getMove, enemy);

            getX = res[0];
            tank.setX(getX);

            getY = res[1];
            tank.setY(getY);

            tank.setDeg(90);

        }

        // движение вперед
        if (str.toLowerCase().contains("up_move".toLowerCase())) {
            int getMove = getObj.getInt("up_move");
            int getX = tank.getX();
            int getY = tank.getY();

            int[] res = moving(0, getX, getY, getMove, enemy);

            getX = res[0];
            tank.setX(getX);

            getY = res[1];
            tank.setY(getY);

            tank.setDeg(0);

        }

        //движение назад
        if (str.toLowerCase().contains("down_move".toLowerCase())) {
            int getMove = getObj.getInt("down_move");
            int getX = tank.getX();
            int getY = tank.getY();

            int[] res = moving(180, getX, getY, getMove, enemy);

            getX = res[0];
            tank.setX(getX);

            getY = res[1];
            tank.setY(getY);

            tank.setDeg(180);

        }

    }

    private int[] moving(int deg, int x, int y, int move, Tank enemy) {
        int[] res = new int[2];
        res[0] = x;
        res[1] = y;

        switch (deg) {
            case 0:
                if (x - move > 0 && (x - move != enemy.getX() || y != enemy.getY())) {
                    res[0] = x - move;
                    res[1] = y;
                }
                break;
            case 90:
                if (y + move < lengthMapY && (y + move != enemy.getY() || x != enemy.getX())) {
                    res[0] = x;
                    res[1] = y + move;
                }
                break;
            case 180:
                if (x + move < lengthMapX && (x + move != enemy.getX() || y != enemy.getY())) {
                    res[0] = x + move;
                    res[1] = y;
                }
                break;
            case 270:
                if (y - move > 0 && (y - move != enemy.getY() || x != enemy.getX())) {
                    res[0] = x;
                    res[1] = y - move;
                }
                break;
        }

        return res;
    }

    public int[][] shoot(Tank firstTank, Tank secondTank, int lengthX, int lengthY) {
        int[][] see = new int[lengthX][lengthY];
        for (int x = 1; x < lengthX; x++) {
            for (int y = 1; y < lengthY; y++) {
                see[x][y] = 0;
            }
        }

        if (firstTank.getX() == secondTank.getX() || firstTank.getY() == secondTank.getY()) {
            if (firstTank.getX() < secondTank.getX() && firstTank.getDeg() == 180) {
                for (int i = firstTank.getX(); i <= secondTank.getX(); i++) {
                    see[i][firstTank.getY()] = 1;
                }
                secondTank.setHealth(secondTank.getHealth() - firstTank.getDamage());
            } else if (firstTank.getX() > secondTank.getX() && firstTank.getDeg() == 0) {
                for (int i = firstTank.getX(); i >= secondTank.getX(); i--) {
                    see[i][firstTank.getY()] = 1;
                }
                secondTank.setHealth(secondTank.getHealth() - firstTank.getDamage());
            } else if (firstTank.getY() < secondTank.getY() && firstTank.getDeg() == 90) {
                for (int i = firstTank.getY(); i <= secondTank.getY(); i++) {
                    see[firstTank.getX()][i] = 1;
                }
                secondTank.setHealth(secondTank.getHealth() - firstTank.getDamage());
            } else if (firstTank.getY() > secondTank.getY() && firstTank.getDeg() == 270) {
                for (int i = firstTank.getY(); i >= secondTank.getY(); i--) {
                    see[firstTank.getX()][i] = 1;
                }
                secondTank.setHealth(secondTank.getHealth() - firstTank.getDamage());
            }
        }

        if (secondTank.getX() == firstTank.getX() || secondTank.getY() == firstTank.getY()) {
            if (secondTank.getX() < firstTank.getX() && secondTank.getDeg() == 180) {
                for (int i = secondTank.getX(); i <= firstTank.getX(); i++) {
                    see[i][secondTank.getY()] = 2;
                }
                firstTank.setHealth(firstTank.getHealth() - secondTank.getDamage());
            } else if (secondTank.getX() > firstTank.getX() && secondTank.getDeg() == 0) {
                for (int i = secondTank.getX(); i >= firstTank.getX(); i--) {
                    see[i][secondTank.getY()] = 2;
                }
                firstTank.setHealth(firstTank.getHealth() - secondTank.getDamage());
            } else if (secondTank.getY() < firstTank.getY() && secondTank.getDeg() == 90) {
                for (int i = secondTank.getY(); i <= firstTank.getY(); i++) {
                    see[secondTank.getX()][i] = 2;
                }
                firstTank.setHealth(firstTank.getHealth() - secondTank.getDamage());
            } else if (secondTank.getY() > firstTank.getY() && secondTank.getDeg() == 270) {
                for (int i = secondTank.getY(); i >= firstTank.getY(); i--) {
                    see[secondTank.getX()][i] = 2;
                }
                firstTank.setHealth(firstTank.getHealth() - secondTank.getDamage());
            }
        }

        return see;
    }
}
