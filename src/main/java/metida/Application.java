package metida;

import metida.factory.TankFactory;
import metida.object.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

    public static void main(String[] args) throws IOException {
        //String path="E:/project/metida/test.json";
        //Data json = FileService.getConfigTest(path);
        //System.out.println(json.getClass());
        //System.out.println(json);
        //String first=json.get("countPlayers").getAsString();
        //System.out.println(json.get("Players").getAsJsonArray());

        SpringApplication.run(Application.class, args);
    }

}