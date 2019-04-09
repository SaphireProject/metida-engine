package metida.controllers;

import metida.StrategyCheck;
import metida.data.UserConfig;
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

    @RequestMapping(value = "/start")
    public ResponseEntity run(@RequestBody List<UserConfig> userConfigs) {

        for (int i = 0; userConfigs.size() > i; i++) {
            List<String> list = userConfigs.get(i).strategyPaths;
            for (int j = 0; list.size() > j; j++) {
                searchStrategy(list.get(j));
            }
        }
        return ResponseEntity.ok().build();
    }

    static private void searchStrategy(String path) {
        StrategyCheck strategyCreate = new StrategyCheck();
        File folder = new File(path);
        if (path.endsWith(".java")) {
            String strategy = fileInput(path);
            boolean bool = strategyCreate.check(strategy);
            if (bool) {
                LOGGER.info("Ваш код ужасен");
            } else {
                LOGGER.info("Ваш код хорош");
            }
        } else {
            folder.list(new FilenameFilter() {
                @Override
                public boolean accept(File folder, String name) {
                    if (name.endsWith(".java")) {
                        String strategy = fileInput(folder + "\\" + name);
                        boolean bool = strategyCreate.check(strategy);
                        if (bool) {
                            LOGGER.info("Ваш код ужасен");
                        } else {
                            LOGGER.info("Ваш код хорош");
                        }
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