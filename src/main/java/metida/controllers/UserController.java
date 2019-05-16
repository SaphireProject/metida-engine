package metida.controllers;

import metida.StrategyCheck;
import metida.data.Config;
import metida.data.UserConfig;
import metida.factory.TankFactory;
import metida.interfacable.IUserStrategy;
import metida.object.Game;
import metida.object.ThreadStrategy;
import org.joor.Reflect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TankFactory factory;

    @RequestMapping(value = "/start")
    public ResponseEntity run(@RequestBody List<UserConfig> userConfigs) {
        //todo: сделать чтоб игра создавалась по адресу конфига из боди
        //String pathGame = "test.json";
        //game = new Game(pathGame);
        String path="E:/project/metida/test.json";
        LOGGER.info(""+userConfigs.size());
        game=Game.Initialize(path);
        for (int i = 0;  i < userConfigs.size(); i++) {
            searchStrategy(userConfigs.get(i).id, userConfigs.get(i).strategyPaths);
            LOGGER.info(""+userConfigs.get(i).strategyPaths);
        }

        return ResponseEntity.ok().build();
    }

    //fixme: разобраться в логах, придумать как верно крутить мир
    @RequestMapping(value = "/start1")
    public ResponseEntity run1(@RequestBody List<UserConfig> userConfigs) {
        for(int i=0;i<2;i++){
            factory.getObjectsTank().forEach((id, object) ->{
                try{
                    object.getQueueMethods().poll().execute();
                }
                catch (Exception e) {
                    LOGGER.info("ошибка вызова команды");
                }
            });
            game.action();
            game.action();
            game.action();
        }

        return ResponseEntity.ok().build();
    }

    static private void searchStrategy(String id,String path) {
        File folder = new File(path);
        if (path.endsWith(".java")) {
            String strategy = fileInput(path);
            strategy = "package com.example;\n" +
                    "    import metida.factory.TankFactory;\n" +
                    "    import metida.object.Tank;\n" +
                    "    import metida.interfacable.Direction;\n" +
                    "    import metida.providers.TankFactoryProvider;\n" +
                    "    class "+ id/*CompileTest*/ +" implements metida.interfacable.IUserStrategy { " +
                    strategy +
                    "}";


            IUserStrategy userStrategy;
            userStrategy = Reflect.compile(
                    "com.example."+id
                    /*"com.example.CompileTest"*/,
                    strategy).create().get();
            LOGGER.info("скомпилировалось");
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