package metida.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import metida.CommandsTank.TankCommands;
import metida.JsonObject.*;
import metida.data.Data;
import metida.data.UserConfig;
import metida.factory.TankFactory;
import metida.interfacable.IUserStrategy;
import metida.object.*;
import org.joor.Reflect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;

@RestController
@Component
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private static Game game;
    private QueueMethods<TankCommands> queue =new QueueMethods<TankCommands>();
    Random random=new Random();

    @Autowired
    private TankFactory factory;

    @Value("${runner.url}")
    private String url;

    /*@Value("${runner.url1}")
    private String url1;*/

    protected GameOptions gameOptions;

    private static int idWall=1;

    public void addWall(Data data){
        int x=random.nextInt(data.getLengthX());
        int y=random.nextInt(data.getLengthY());
        Point point = new Point(x, y);
        if(game.gameOptions.hashmap.get(point.hashCode()) == null){
            Wall wall=new Wall(x, y);
            game.addObjectWall(wall, x, y, game.gameOptions);
            LOGGER.info("стена добавлена"+" "+idWall);
            idWall++;
        }
        else{
            addWall(data);
        }
    }


    @RequestMapping(value = "/start")
    public ResponseEntity run(@RequestBody List<UserConfig> userConfigs) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Data> responseEntity = restTemplate.getForEntity(
                url+"/config",
                Data.class
                );
        Data data=responseEntity.getBody();
        LOGGER.info(data.toString());
       // game=Game.Initialize(data);
/*
        ResponseEntity<ListConfig> responseConfig = restTemplate.getForEntity(
                url1+"/getpath",
                ListConfig.class
        );

        ListConfig listConfig=responseConfig.getBody();

        for (int i = 0;  i < listConfig.getList().size(); i++) {
            searchStrategy(listConfig.getList().get(i).id, listConfig.getList().get(i).strategyPaths);
            LOGGER.info(""+listConfig.getList().get(i).strategyPaths);
        }
*/

/*
* for (int i = 0;  i < userConfigs.size(); i++) {
            searchStrategy(userConfigs.get(i).id, userConfigs.get(i).strategyPaths);
            LOGGER.info(""+userConfigs.get(i).strategyPaths);
        }
        */
        int countWall=(int)(data.getLengthX()*data.getLengthY()*0.003);
        LOGGER.info(""+countWall);
        while(countWall>0){
            addWall(data);
            countWall--;
        }

        List<String> preloadBlocks=new LinkedList<>();
        for (int j = 0; j < data.getLengthY(); j++) {
            String row="";
            for (int i = 0; i < data.getLengthX(); i++) {

                Point point = new Point(i , j);
                if(game.gameOptions.hashmap.get(point.hashCode())==null){
                    row=row+"0";

                }
                else if(game.gameOptions.hashmap.get(point.hashCode()).getType()==TypeObjects.WALL){
                    row=row+"1";
                }
                else{
                    row=row+"0";
                }
            }
            preloadBlocks.add(row);
            //row="";
        }

        LOGGER.info(preloadBlocks.toString());
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        PreloadJson preloadJson=new PreloadJson(preloadBlocks);
        PreloadFinalJson preloadFinalJson=new PreloadFinalJson(preloadJson);

        String jsonPreload = null;
        try {
            jsonPreload = mapper.writeValueAsString(preloadFinalJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //отправка данных на раннер, модель карты
        //restTemplate.postForEntity("http://localhost:8085/game/preload", preloadFinalJson, PreloadFinalJson.class);

        LOGGER.info(jsonPreload);

        factory.getObjectsTank().forEach((id, object) ->{
            try{
                object.setQueueMethodsDuplicate(object.getQueueMethods());
                LOGGER.info(""+object.getQueueMethodsDuplicate());
                LOGGER.info(""+object.getQueueMethods());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/start1")
    public ResponseEntity run1(@RequestBody List<UserConfig> userConfigs) {
        int count=0;
        //while(count<500||)
        for(int i=0;i<10;i++){


            factory.getObjectsTank().forEach((id, object) ->{
                try{
                    if(object.getQueueMethods().isEmpty()){
                       object.setQueueMethods(object.getQueueMethodsDuplicate());
                       object.getQueueMethods().poll().execute();
                    }
                    else{
                        object.getQueueMethods().poll().execute();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            });
            Map<Integer, BaseObject> objBulletDelete=game.getObjectsDeleteOld();
            List<BulletJson> bullets=new LinkedList<>();
            game.action();
            objBulletDelete.forEach((idBullet, objectBullet) ->  {
                if(objectBullet.getX()==0||objectBullet.getY()==0){
                    BulletJson bullet=new BulletJson(objectBullet.getX(),objectBullet.getY(),
                            ""+objectBullet.getDirection(),
                            objectBullet.isFirstSnapshot(),
                            objectBullet.isLastSnapshot());
                    bullets.add(bullet);
                }
            });
            game.action();
            objBulletDelete.forEach((idBullet, objectBullet) ->  {
                if(objectBullet.getX()==0||objectBullet.getY()==0){
                    BulletJson bullet=new BulletJson(objectBullet.getX(),objectBullet.getY(),
                            ""+objectBullet.getDirection(),
                            objectBullet.isFirstSnapshot(),
                            objectBullet.isLastSnapshot());
                    bullets.add(bullet);
                }
            });
            game.action();
            objBulletDelete.forEach((idBullet, objectBullet) ->  {
                if(objectBullet.getX()==0||objectBullet.getY()==0){
                    BulletJson bullet=new BulletJson(objectBullet.getX(),objectBullet.getY(),
                            ""+objectBullet.getDirection(),
                            objectBullet.isFirstSnapshot(),
                            objectBullet.isLastSnapshot());
                    bullets.add(bullet);
                }
            });


            Map<Integer, BaseObject> obj = game.getObjects();
            System.out.println(obj);

            RestTemplate restTemplate = new RestTemplate();


            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            List<TankJson> tanks=new LinkedList<>();
            Map<Integer, Integer> endOfGame=new HashMap<>();
            List<Integer> idWinner=new LinkedList<>();
            List<Winner> listWinner=new LinkedList<>();

            obj.forEach((id, object) ->  {
                //LOGGER.info("Старт создания snapshot");
                if(object.getType()== TypeObjects.TANK){
                    LOGGER.info("Создание объекта танк");
                    TankJson tankJson = new TankJson(""+obj.get(id).getId(),
                            object.getX(),
                            object.getY(),
                            "tank_green",
                            object.isLiving());
                    tanks.add(tankJson);

                    /*if(endOfGame.containsValue(factory.objectsTank.get(id).getIdTeam())){
                        endOfGame.put(id, factory.objectsTank.get(id).getIdTeam());
                        idWinner.add(id);
                        listWinner.add(new Winner(id));
                    }

                    if(endOfGame.size()==1){
                        //создать модель окончания игры
                        Winner winner=new Winner(idWinner.get(0));
                        EndGame endGame=new EndGame(TypeEnd.win, listWinner);
                        //сюда вот вписать новую модель окончания
                    }*/


                }
                if(object.getType()== TypeObjects.BULLET){
                    BulletJson bullet=new BulletJson(object.getX(),object.getY(),
                            ""+object.getDirection(),
                            object.isFirstSnapshot(),
                            object.isLastSnapshot());
                    bullets.add(bullet);
                }

                objBulletDelete.forEach((idBullet, objectBullet) ->  {
                    BulletJson bullet=new BulletJson(objectBullet.getX(),objectBullet.getY(),
                            ""+objectBullet.getDirection(),
                            objectBullet.isFirstSnapshot(),
                            objectBullet.isLastSnapshot());
                    bullets.add(bullet);
                });


            });
            AnimationJson animationJson=new AnimationJson(tanks, bullets);
            FrameJson frameJson = new FrameJson(animationJson);
            //restTemplate.postForEntity("http://localhost:8085/game/animation", frameJson, FrameJson.class);

            if(endOfGame.size()==1){
                //создать модель окончания игры
               // Winner winner=new Winner(idWinner.get(0));
                EndGame endGame=new EndGame(TypeEnd.win, listWinner);
                //todo:отправка модели
            }
            try {

                String jsonFrame = mapper.writeValueAsString(frameJson);

                LOGGER.info(jsonFrame);
                //todo:отправить модель snapshot
               // ResponseEntity<AnimationJson> result
               //        = restTemplate.postForObject("http://localhost:8904/list", frameJson, ResponseEntity.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
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

/*
        List<String> preloadBlocks=new LinkedList<>();
        for (int j = 0; j < data.getLengthY(); j++) {
            String row="";
            for (int i = 0; i < data.getLengthX(); i++) {

                Point point = new Point(i , j);
                game.gameOptions.hashmap.get(point.hashCode()).getType()==TypeObjects.WALL
                if(gameOptions.hashmap.get(point.hashCode()).getType()==TypeObjects.WALL){
                        row=row+"1";
                        }
                        else{
                        row=row+"0";
                        }
                        }
                        preloadBlocks.add(row);
                        row="";
                        }

                        LOGGER.info(preloadBlocks.toString());*/