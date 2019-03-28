package metida.controllers;


import metida.data.Config;
import metida.data.Data;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//ToDo: сделать модель для данных и ловить ее рестом
public class GetConfiguration {

    @RequestMapping(value="config", method = RequestMethod.GET)
    public Config getData(@RequestBody Config config){

        return config;

    }
}
