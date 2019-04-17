package metida.controllers;

import metida.StrategyCheck;
import metida.data.UserConfig;
import metida.factory.TankFactory;
import metida.interfacable.IUserStrategy;
import metida.object.Game;
import metida.object.ThreadStrategy;
import org.joor.Reflect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private static Game game;

    @RequestMapping(value = "/start")
    public ResponseEntity run(@RequestBody List<UserConfig> userConfigs) {

        String pathGame = "test.json";
        game = new Game(pathGame);
        for (int i = 0; userConfigs.size() > i; i++) {
            List<String> list = userConfigs.get(i).strategyPaths;
            for (int j = 0; list.size() > j; j++) {
                searchStrategy(list.get(j));
            }
        }

        //for (int i = 0; userConfigs.size() > i; i++) {
        //    ThreadStrategy threadStrategy = game.threadStrategyMap.get(1);
        //    threadStrategy.start();
        //}

        return ResponseEntity.ok().build();
    }

    static private void searchStrategy(String path) {
        File folder = new File(path);
        if (path.endsWith(".java")) {
            String strategy = fileInput(path);
            strategy = "package com.example;\n" +
                    "    import metida.factory.TankFactory;\n" +
                    "    import metida.object.Tank;\n" +
                    "    import metida.providers.TankFactoryProvider;\n" +
                    "    class CompileTest implements metida.interfacable.IUserStrategy { " +
                    strategy +
                    "}";

            IUserStrategy userStrategy;
            userStrategy = Reflect.compile(
                    "com.example.CompileTest",
                    strategy).create().get();

            ThreadStrategy threadStrategy = new ThreadStrategy(userStrategy);
            threadStrategy.start();
            //game.threadStrategyMap.put(threadStrategy.id, threadStrategy);
        } else {
            folder.list(new FilenameFilter() {
                @Override
                public boolean accept(File folder, String name) {
                    if (name.endsWith(".java")) {
                        String strategy = fileInput(folder + "\\" + name);
                        strategy = "package com.example;\n" +
                                "    import metida.factory.TankFactory;\n" +
                                "    import metida.object.Tank;\n" +
                                "    import metida.providers.TankFactoryProvider;\n" +
                                "    class CompileTest implements metida.interfacable.IUserStrategy { \n " +
                                strategy +
                                "\n}";

                        IUserStrategy userStrategy;
                        userStrategy = Reflect.compile(
                                "com.example.CompileTest",
                                strategy).create().get();

                        ThreadStrategy threadStrategy = new ThreadStrategy(userStrategy);
                        threadStrategy.start();
                        //game.threadStrategyMap.put(threadStrategy.id, threadStrategy);
                    }
                    return true;
                }
            });
        }
    }

    static String fileInput(String name) {
        String res = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(name);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 200);
            int i;
            while ((i = bufferedInputStream.read()) != -1) {
                res += (char) i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

}