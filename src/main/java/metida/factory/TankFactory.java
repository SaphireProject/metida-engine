package metida.factory;


import metida.JsonObject.PostConfig;
import metida.data.Data;
import metida.data.ParameterMetida;
import metida.object.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class TankFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(TankFactory.class);

    private static Game game;

    @Value("${runner.url}")
    private String url;

    public Map<Integer, Tank> objectsTank = new HashMap<>();

    public Map<Integer, Tank> getObjectsTank() {
        return objectsTank;
    }

    public void setObjectsTank(Map<Integer, Tank> objectsTank) {
        this.objectsTank = objectsTank;
    }

    //когда создать игру
    @PostConstruct
    public void init() {
        /*RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Data> responseEntity = restTemplate.getForEntity(
                url+"/config",
                Data.class
        );
        Data data=responseEntity.getBody();
        game = Game.Initialize(data);*/

        RestTemplate restTemplate = new RestTemplate();



        ResponseEntity<ParameterMetida> data=restTemplate.getForEntity(
                url+"/game/parameters", ParameterMetida.class);

        game = Game.Initialize(data.getBody());
    }

    Random random=new Random();

    public Tank getTank(int idTeam) {

        Tank tank = new Tank(idTeam);
        LOGGER.info("Танк создан " + tank.getId());
        try {
            LOGGER.info(""+game.gameOptions.getWidth());
            int x=random.nextInt(game.gameOptions.getWidth());
            int y=random.nextInt(game.gameOptions.getHeight());
            Point point = new Point(x, y);
            if(game.gameOptions.hashmap.get(point.hashCode()) == null){
                //getTank(idTeam);
                game.addObject(tank, x, y, game.gameOptions);
                LOGGER.info("Танк добавлен");
                //можно добавлять в отдельный список танки, а потом по нему пройтись и получить очереди методов
                objectsTank.put(point.hashCode(),tank);
            }
            else{
                getTank(idTeam);
            }
        } catch (Exception e) {
            LOGGER.info("ошибочка");
        }
        return tank;
    }

    public GoldTank getGoldTank(int idTeam) {
        LOGGER.info("Голд танк создан");
        return new GoldTank(idTeam);
    }

}
