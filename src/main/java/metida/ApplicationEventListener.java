package metida;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import metida.JsonObject.*;
import metida.data.ParameterMetida;
import metida.data.Data;
import metida.factory.TankFactory;
import metida.interfacable.Direction;
import metida.interfacable.IUserStrategy;
import metida.object.*;
import metida.service.Strategy1;
import metida.service.Strategy2;
import org.joor.Reflect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;

@Component
public class ApplicationEventListener {
    private static Game game;
    private String t="https://f846e947-48a3-45a2-8fdf-6e2313e1e30f.mock.pstmn.io";
    @Value("${runner.url}")
    private String url;

    @Autowired
    private TankFactory factory;

    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationEventListener.class);

    Random random=new Random();

    private static int idWall=1;
    private static int color=0;

    public void addWall(ParameterMetida data){
        int x=random.nextInt(data.getWidthOfMapForGame()-1);
        int y=random.nextInt(data.getHeightOfMapForGame()-1);
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

static String strategy="";

    @EventListener(ApplicationStartedEvent.class)
    public void applicationStartedEvent() {
        LOGGER.info("Success");

        //fixme:не должно быть post запроса, должен по runner_url получать конфиг
       /* Map<String, Integer> body = new HashMap<>();
        body.put("id", 22);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<Map> entity = new HttpEntity<>(body, headers);

        ResponseEntity<ParameterMetida> data=restTemplate.exchange(
                url+"/game/parameters", HttpMethod.POST, entity, ParameterMetida.class);*/
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ParameterMetida> data=restTemplate.getForEntity(url+
                "/game/parameters", ParameterMetida.class);

        game = Game.Initialize(data.getBody());
        LOGGER.info("Игра создана в листенере" +data.getBody().toString());


        for(int i=0; i<data.getBody().getStrategies().size();i++){
            LOGGER.info("Айди стратегии "+data.getBody().getStrategies().get(i).getId());
            searchStrategy("user"+data.getBody().getStrategies().get(i).getId(), data.getBody().getStrategies().get(i).getDescription());
        }


        //ResponseEntity<String> answer=restTemplate.getForEntity(url+"/game/strategy/1", String.class);


        int countWall=(int)(data.getBody().getWidthOfMapForGame()*data.getBody().getHeightOfMapForGame()*0.05);
        LOGGER.info(""+countWall);
        while(countWall>0){
            addWall(data.getBody());
            countWall--;
        }

        List<String> preloadBlocks=new LinkedList<>();
        for (int j = data.getBody().getHeightOfMapForGame()-1; j >= 0; j--) {
            String row="";
            for (int i = 0; i < data.getBody().getWidthOfMapForGame(); i++) {

                Point point = new Point(i , j);
                if(game.gameOptions.hashmap.get(point.hashCode())==null){
                    row=row+"0";

                }
                else if(game.gameOptions.hashmap.get(point.hashCode()).getType()== TypeObjects.WALL){
                    row=row+"1";
                }
                else{
                    row=row+"0";
                }
            }
            preloadBlocks.add(row);
            //row="";
        }

        LOGGER.info("список стен "+game.objWall.toString());
        LOGGER.info(preloadBlocks.toString());

        //------До сюда работает

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        PreloadJson preloadJson=new PreloadJson(preloadBlocks);
        PreloadFinalJson preloadFinalJson=new PreloadFinalJson(preloadJson);

        LOGGER.info(preloadFinalJson.toString());
        //отправка данных на раннер, модель карты

        Map<String, List<String>> body1 = new HashMap<>();
        body1.put("blocks", preloadBlocks);


        HttpHeaders headers3 = new HttpHeaders();
        headers3.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<Map> entity1 = new HttpEntity<>(body1, headers3);

       /* ResponseEntity<Void> data1=restTemplate.postForEntity(
                url+"/game/preload",  entity1, Void.class);*/


       //--------до сюда

        //отправка первичного расположения
        Map<Integer, BaseObject> objFirst = game.getObjects();
        List<BulletJson> bullets1 = new LinkedList<>();
        List<TankJson> tanks1=new LinkedList<>();
        factory.getObjectsTank().forEach((id , object) -> {
                    LOGGER.info("Создание объекта танк");
                    if(object.getIdTeam()==1){
                        TankJson tankJson = new TankJson(""+objFirst.get(id).getId(),
                                object.getX(),
                                object.getY(),
                                object.getDirection(),
                                "tank_green",
                                object.isLiving());
                        tanks1.add(tankJson);
                    }
                    else{
                        TankJson tankJson = new TankJson(""+objFirst.get(id).getId(),
                                object.getX(),
                                object.getY(),
                                object.getDirection(),
                                "tank_red",
                                object.isLiving());
                        tanks1.add(tankJson);
                    }
        });

        AnimationJson animationJson1=new AnimationJson(tanks1, bullets1);
        FrameJson frameJson1 = new FrameJson(animationJson1);
        try {
            String jsonFrame1 = mapper.writeValueAsString(frameJson1);
            LOGGER.info(jsonFrame1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<FrameJson> entityFirst = new HttpEntity<>(frameJson1, headers1);

            /*ResponseEntity<Void> data2=restTemplate.postForEntity(
                    url+"/game/animation", entityFirst, Void.class);*/

        for(int i=0;i<20;i++) {

            factory.getObjectsTank().forEach((id , object) -> {
                try {
                    object.getQueueMethods().poll().execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            Map<Integer, BaseObject> objBulletDelete = game.getObjectsDeleteOld();
            LOGGER.info("на удаление "+objBulletDelete.toString());
            List<BulletJson> bullets = new LinkedList<>();
            game.action();
            objBulletDelete.forEach((idBullet , objectBullet) -> {
                boolean checkX=(objectBullet.getY()==0 &
                        objectBullet.getX()!=0 &
                        objectBullet.getX()!=(data.getBody().getWidthOfMapForGame()-1)&
                        (objectBullet.getDirection()== Direction.LEFT|objectBullet.getDirection()== Direction.RIGHT));

                boolean checkY=(objectBullet.getX()==0 &
                        objectBullet.getY()!=0 &
                        objectBullet.getY()!=(data.getBody().getHeightOfMapForGame()-1)&
                        (objectBullet.getDirection()== Direction.UP|objectBullet.getDirection()== Direction.DOWN));

                if(objectBullet.isLastSnapshot() |
                        objectBullet.getX()==0 |
                        objectBullet.getY()==0 ){
                    BulletJson bullet = new BulletJson(objectBullet.getId(),objectBullet.getX() , objectBullet.getY() ,
                            "" + objectBullet.getDirection() ,
                            objectBullet.isFirstSnapshot() ,
                            objectBullet.isLastSnapshot());
                    bullets.add(bullet);
                }

            });
            objBulletDelete.clear();
            objBulletDelete = game.getObjectsDeleteOld();
            game.action();

            objBulletDelete.forEach((idBullet , objectBullet) -> {

                boolean checkX=(objectBullet.getY()==0&
                        objectBullet.getX()!=0&
                        objectBullet.getX()!=(data.getBody().getWidthOfMapForGame()-1)&
                        (objectBullet.getDirection()== Direction.LEFT|objectBullet.getDirection()== Direction.RIGHT));

                boolean checkY=(objectBullet.getX()==0&
                        objectBullet.getY()!=0&
                        objectBullet.getY()!=(data.getBody().getHeightOfMapForGame()-1)&
                        (objectBullet.getDirection()== Direction.UP|objectBullet.getDirection()== Direction.DOWN));

                if(objectBullet.isLastSnapshot() |
                        objectBullet.getX()==0 |
                        objectBullet.getY()==0 ){
                    BulletJson bullet = new BulletJson(objectBullet.getId(),objectBullet.getX() , objectBullet.getY() ,
                            "" + objectBullet.getDirection() ,
                            objectBullet.isFirstSnapshot() ,
                            objectBullet.isLastSnapshot());
                    bullets.add(bullet);
                }
            });
            objBulletDelete.clear();
            objBulletDelete = game.getObjectsDeleteOld();
            game.action();
            objBulletDelete.forEach((idBullet , objectBullet) -> {

                boolean checkX=(objectBullet.getY()==0&
                        objectBullet.getX()!=0&
                        objectBullet.getX()!=(data.getBody().getWidthOfMapForGame()-1)&
                        (objectBullet.getDirection()== Direction.LEFT|objectBullet.getDirection()== Direction.RIGHT));

                boolean checkY=(objectBullet.getX()==0&
                        objectBullet.getY()!=0&
                        objectBullet.getY()!=(data.getBody().getHeightOfMapForGame()-1)&
                        (objectBullet.getDirection()== Direction.UP|objectBullet.getDirection() == Direction.DOWN));

                /*if(!checkX|objectBullet.isLastSnapshot()  ){
                    BulletJson bullet = new BulletJson(objectBullet.getX() , objectBullet.getY() ,
                            "" + objectBullet.getDirection() ,
                            objectBullet.isFirstSnapshot() ,
                            objectBullet.isLastSnapshot());
                    bullets.add(bullet);
                }*/
                if(objectBullet.isLastSnapshot() |
                        objectBullet.getX()==0 |
                        objectBullet.getY()==0 ){
                    BulletJson bullet = new BulletJson(objectBullet.getId(),objectBullet.getX() , objectBullet.getY() ,
                            "" + objectBullet.getDirection() ,
                            objectBullet.isFirstSnapshot() ,
                            objectBullet.isLastSnapshot());
                    bullets.add(bullet);
                }
                //else if


            });
            objBulletDelete.clear();

            Map<Integer, BaseObject> obj = game.getObjects();
            System.out.println(obj);


            //------

            List<TankJson> tanks=new LinkedList<>();
            Map<Integer, Integer> endOfGame=new HashMap<>();
            List<Integer> idWinner=new LinkedList<>();
            List<Winner> listWinner=new LinkedList<>();

            obj.forEach((id, object) ->  {
                //LOGGER.info("Старт создания snapshot");
                if(object.getType()== TypeObjects.TANK){


                    LOGGER.info("Создание объекта танк");
                    if(object.getIdTeam()==1){
                        TankJson tankJson = new TankJson(""+objFirst.get(id).getId(),
                                object.getX(),
                                object.getY(),
                                object.getDirection(),
                                "tank_green",
                                object.isLiving());
                        tanks.add(tankJson);
                    }
                    else{
                        TankJson tankJson = new TankJson(""+objFirst.get(id).getId(),
                                object.getX(),
                                object.getY(),
                                object.getDirection(),
                                "tank_red",
                                object.isLiving());
                        tanks.add(tankJson);
                    }
/*
                    if(endOfGame.containsValue(factory.objectsTank.get(id).getIdTeam())){
                        endOfGame.put(id, factory.objectsTank.get(id).getIdTeam());
                        idWinner.add(id);
                        listWinner.add(new Winner(id));
                    }

                    if(endOfGame.size()==1){
                        //создать модель окончания игры
                        Winner winner=new Winner(idWinner.get(0));
                        EndGame endGame=new EndGame(TypeEnd.win, listWinner);
                        //сюда вот вписать новую модель окончания
                    }
*/

                }
                if(object.getType()== TypeObjects.BULLET){
                    BulletJson bullet=new BulletJson(object.getId(),object.getX(),object.getY(),
                            ""+object.getDirection(),
                            object.isFirstSnapshot(),
                            object.isLastSnapshot());
                    object.setFirstSnapshot(false);
                    bullets.add(bullet);
                }
                color=0;

               /* objBulletDelete.forEach((idBullet, objectBullet) ->  {
                    BulletJson bullet=new BulletJson(objectBullet.getX(),objectBullet.getY(),
                            ""+objectBullet.getDirection(),
                            objectBullet.isFirstSnapshot(),
                            objectBullet.isLastSnapshot());
                    bullets.add(bullet);
                });*/


            });
            LOGGER.info(""+tanks.size());
            AnimationJson animationJson=new AnimationJson(tanks, bullets);
            FrameJson frameJson = new FrameJson(animationJson);

            //Map<String, AnimationJson> body2 = new HashMap<>();
            //body2.put("animations", animationJson);


            HttpHeaders headers2 = new HttpHeaders();
            headers2.add("Accept", MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<FrameJson> entity2 = new HttpEntity<>(frameJson, headers2);

            /*ResponseEntity<Void> data2=restTemplate.postForEntity(
                    url+"/game/animation", entity2, Void.class);*/
            try {
                String jsonFrame = mapper.writeValueAsString(frameJson);
                strategy=strategy+jsonFrame+",";
                LOGGER.info(jsonFrame);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }
        //распечатка для димы
        LOGGER.info(strategy);
    }



    static private void searchStrategy(String id, String path) {
        IUserStrategy userStrategy;
        IUserStrategy userStrategy1;
        if (id.equals("user3")){
            LOGGER.info("Выполнение стратегии "+id);
            userStrategy=new Strategy1();
            //userStrategy.init();
            //userStrategy.execute();
            ThreadStrategy threadStrategy = new ThreadStrategy(userStrategy);
            threadStrategy.start();
        }
        else
        if(id.equals("user5")){
            userStrategy1=new Strategy2();
            //userStrategy1.init();
            //userStrategy1.execute();
            ThreadStrategy threadStrategy = new ThreadStrategy(userStrategy1);
            threadStrategy.start();
        }
            /*String strategy = path;
            strategy = "package com.example;\n" +
                    "    import metida.factory.TankFactory;\n" +
                    "    import metida.object.Tank;\n" +
                    "    import metida.interfacable.Direction;\n" +
                    "    import metida.providers.TankFactoryProvider;\n" +
                    "    class "+ id +" implements metida.interfacable.IUserStrategy { " +
                    strategy +
                    "}";*/


            /*IUserStrategy userStrategy1;
            userStrategy1 = Reflect.compile(
                    "metida.factory."+id
                    ,
                    strategy).create().get();
            LOGGER.info("скомпилировалось");
            ThreadStrategy threadStrategy = new ThreadStrategy(userStrategy);
            threadStrategy.start();*/
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
