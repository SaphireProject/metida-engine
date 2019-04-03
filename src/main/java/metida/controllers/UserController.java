package metida.controllers;

import metida.StrategyCreate;
import metida.data.UserConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FilenameFilter;
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
        LOGGER.info(path);
        File folder = new File(path);
        String[] files = folder.list(new FilenameFilter() {
            @Override
            public boolean accept(File folder, String name) {
                return name.endsWith(".java");
            }
        });

        StrategyCreate strategyCreate = new StrategyCreate();
        try {
            for (String fileName : files) {
                strategyCreate.strCreate(fileName);
            }
        } catch (Exception e) {
            strategyCreate.strCreate(String.valueOf(e));
        }
    }

}