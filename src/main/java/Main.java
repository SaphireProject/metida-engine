import java.io.*;
import java.util.function.Supplier;

import org.joor.Reflect;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    static Tank firstTank, secondTank;
    static int nowLine = 0;
    static int lengthMapX = 10;
    static int lengthMapY = 10;

    static String[] subStrStrategy;
    static String[] clazzTankFactory = {"TankFactory.getMyTank().moveAhead()", "TankFactory.getMyTank().moveDown()",
            "TankFactory.getMyTank().turnTowerRight()", "TankFactory.getMyTank().turnTowerLeft()",
            "TankFactory.getMyTank().scanAround()", "TankFactory.getMyTank().getHelth()",
            "TankFactory.getMyTank().getCoordinates()", "TankFactory.getMyTank().getAngle()",
            "TankFactory.getMyTank().recharge()", "TankFactory.getMyTank().getHowManySteps()"};
    static String[] clazzScanFactory = {"ScanFactory.getEnemyTank()", "ScanFactory.getBullet()",
            "ScanFactory.getWall()", "ScanFactory.shot()"};

    public static void main(String[] args) {

        /*
        firstTank = new Tank("java",
                80,
                30,
                String.valueOf(constructorStrategy(1, 1, 1, 1)),
                3,
                3,
                constructorStrategy(90, 90, 3, 4).length(),
                0);

        secondTank = new Tank("c#",
                80,
                30,
                String.valueOf(constructorStrategy(1, 1, 1, 1)),
                3,
                8,
                constructorStrategy(90, 90, 3, 4).length(),
                180);

        System.out.println("STEP 0");
        print();
        game();

        fileOutput();
        fileInput();
        */

        /*Supplier<String> supplier = Reflect.compile(
                "com.example.StrategyTest",
                strategy
        ).create().;

        System.out.println(supplier.get());
        */

        reception();
        if (!check()) {
            System.out.println("Неверный код стратегии. Посмотрите документацию");
            System.exit(0);
        }


    }

    private static void reception() {
        Supplier<String> supplier = Reflect.compile(
                "com.example.CompileTest",
                "package com.example;\n" +
                        "class CompileTest\n" +
                        "implements java.util.function.Supplier<String> {\n" +
                        "  public String get() {\n" +
                        "    return \"private void execute(){" +
                        "        TankFactory.getMyTank().moveAhead();" +
                        "        TankFactory.getMyTank().turnTowerLeft();" +
                        "    }\";\n" +
                        "  }\n" +
                        "}\n"
        ).create().get();

        System.out.println(supplier.get());

        String delimeter = ";";
        subStrStrategy = supplier.get().split(delimeter);

        int index = subStrStrategy[0].indexOf('{');
        subStrStrategy[0] = subStrStrategy[0].substring(index + 1, subStrStrategy[0].length());

        for (int i = 0; i < subStrStrategy.length - 1; i++) {
            subStrStrategy[i] = subStrStrategy[i].replace(" ", "");
            System.out.println(subStrStrategy[i]);
        }

    }

    private static boolean check() {
        int count_check = 0;

        for (int i = 0; subStrStrategy.length - 1 > i; i++) {
            for (int j = 0; clazzScanFactory.length > j; j++) {
                if (subStrStrategy[i].equals(clazzScanFactory[j])) {
                    count_check++;
                }
            }
            for (int j = 0; clazzTankFactory.length > j; j++) {
                if (subStrStrategy[i].equals(clazzTankFactory[j])) {
                    count_check++;
                }
            }
        }

        if (count_check == subStrStrategy.length - 1) {
            return true;
        } else {
            return false;
        }
    }

    static String fileInput() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\test.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 200);
        int i;
        String res = "";
        while ((i = bufferedInputStream.read()) != -1) {
            res += (char) i;
        }

        return res;
    }

    private static JSONObject constructorStrategy(int left_move, int right_move, int up_move, int down_move) {
        JSONArray ar = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("left_move", left_move);
        ar.put(obj);

        obj = new JSONObject();
        obj.put("up_move", up_move);
        ar.put(obj);

        obj = new JSONObject();
        obj.put("down_move", down_move);
        ar.put(obj);

        obj = new JSONObject();
        obj.put("right_move", right_move);
        ar.put(obj);

        obj = new JSONObject();
        obj.put("strategy", ar);

        return obj;
    }

    private static void game() {
        int k = 0;
        boolean game = true;
        while (game) {
            k++;
            System.out.println("STEP " + k);
            System.out.println("1 health " + firstTank.getHealth());
            System.out.println("2 health " + secondTank.getHealth());

            new Action().action(firstTank, secondTank, lengthMapX, lengthMapY);
            new Action().action(secondTank, firstTank, lengthMapX, lengthMapY);

            print();

            if (firstTank.getHealth() <= 0 || secondTank.getHealth() <= 0) {
                game = false;
            }
        }

        int win;
        if (firstTank.getHealth() > 0) {
            win = 1;
        } else {
            win = 2;
        }

        System.out.println("WIN = " + win);
    }

    public static void print() {

        int[][] sh;
        sh = new Action().shoot(firstTank, secondTank, lengthMapX + 1, lengthMapY + 1);

        for (int x = 1; x < lengthMapX + 1; x++) {
            for (int y = 1; y < lengthMapY + 1; y++) {
                if (firstTank.getX() == x && firstTank.getY() == y) {
                    switch (firstTank.getDeg()) {
                        case 0:
                            System.out.print("|▲");
                            break;
                        case 90:
                            System.out.print("|▶");
                            break;
                        case 180:
                            System.out.print("|▼");
                            break;
                        case 270:
                            System.out.print("|◀");
                            break;
                    }
                } else if (secondTank.getX() == x && secondTank.getY() == y) {
                    switch (secondTank.getDeg()) {
                        case 0:
                            System.out.print("|▲");
                            break;
                        case 90:
                            System.out.print("|▶");
                            break;
                        case 180:
                            System.out.print("|▼");
                            break;
                        case 270:
                            System.out.print("|◀");
                            break;
                    }
                } else if (sh[x][y] != 0) {
                    System.out.print("|" + sh[x][y]);
                } else if (y < lengthMapY) {
                    System.out.print("|_");
                } else {
                    System.out.print("|_|");

                }
            }
            System.out.println();
        }
        System.out.println();
    }

}