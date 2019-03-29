package metida;

import com.google.gson.JsonObject;
import metida.data.Data;
import metida.service.FileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
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

        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        StrategyCreate strategyCreate = new StrategyCreate();
        strategyCreate.strCreate();
    }

}
