import JavaCompilerAPI.CountClassesMethodsFieldsScanner;
import JavaCompilerAPI.CountElementsProcessor;
import JavaCompilerAPI.EmptyTryBlockProcessor;
import JavaCompilerAPI.EmptyTryBlockScanner;
import org.joor.Reflect;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.tools.*;
import java.io.*;
import java.util.Arrays;
import java.util.function.Supplier;

public class Main {

    static Tank firstTank, secondTank;
    static int nowLine = 0;
    static int lengthMapX = 10;
    static int lengthMapY = 10;

    public static void main(String[] args) throws IOException {

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

        //compilerJava();

        Supplier<String> supplier = Reflect.compile(
                "com.example.CompileTest",
                "package com.example;\n" +
                        "class CompileTest\n" +
                        "implements java.util.function.Supplier<String> {\n" +
                        "  public String get() {\n" +
                        "    return \"Hello World!\";\n" +
                        "  }\n" +
                        "}\n"
        ).create().get();

        System.out.println(supplier.get());
    }

    static void fileOutput() throws IOException {
        File file = new File("C:\\test.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        String greetings = "Привет! Добро пожаловать на JavaRush - лучшй сайт для тех, кто хочет стать программистом!";
        fileOutputStream.write(greetings.getBytes());
        fileOutputStream.close();
    }

    static void fileInput() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\test.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 200);
        int i;
        while ((i = bufferedInputStream.read()) != -1) {
            System.out.print((char) i);
        }
    }

    private String str;

    private static class InnerClass {
        private int number;

        public void method() {
            int i = 0;

            try {
                // Some implementation here
            } catch (final Throwable ex) {
                // Some implementation here
            }
        }
    }

    static void compilerJava() throws IOException {
        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        final DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        final StandardJavaFileManager manager = compiler.getStandardFileManager(
                diagnostics, null, null);

        final File file = new File("C:\\Hello.java");

        final Iterable<? extends JavaFileObject> sources =
                manager.getJavaFileObjectsFromFiles(Arrays.asList(file));

        final CountClassesMethodsFieldsScanner scanner = new CountClassesMethodsFieldsScanner();
        final CountElementsProcessor processor = new CountElementsProcessor(scanner);

        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, diagnostics,
                null, null, sources);

        task.setProcessors(Arrays.asList(processor));
        task.call();

        System.out.format("Classes %d, methods/constructors %d, fields %d",
                scanner.getNumberOfClasses(),
                scanner.getNumberOfMethods(),
                scanner.getNumberOfFields());

        manager.close();

        //final EmptyTryBlockScanner scannerTry = new EmptyTryBlockScanner();
        //final EmptyTryBlockProcessor processorTry = new EmptyTryBlockProcessor(scannerTry);


        //task = compiler.getTask( null, manager, diagnostics,
        //        null, null, sources );
        //task.setProcessors( Arrays.asList( processor ) );
        //task.call();

        //System.out.format( "Empty try/catch blocks: %d", scannerTry.getNumberOfEmptyTryBlocks() );
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