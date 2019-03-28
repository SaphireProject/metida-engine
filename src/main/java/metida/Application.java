package metida;

import com.google.gson.JsonObject;
import metida.data.Data;
import metida.service.FileService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {
        String path="E:/project/metida/test.json";
        Data json = FileService.getConfigTest(path);
        System.out.println(json.getClass());
        System.out.println(json);
        //String first=json.get("countPlayers").getAsString();
        //System.out.println(json.get("Players").getAsJsonArray());
    }

}
