package metida;


import metida.JsonObject.PostConfig;
import metida.JsonObject.TankJson;
import metida.data.Data;
import metida.data.ParameterMetida;
import metida.factory.TankFactory;
import metida.factory.WallFactory;
import metida.interfacable.Direction;
import metida.object.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class App {

    private static Logger LOGGER = LoggerFactory.getLogger(App.class);

    private  static  final  String url="";


    public static void main(String[] args) throws IOException {

        Map<String, Integer> body = new HashMap<>();
        body.put("id", 3);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<Map> entity = new HttpEntity<>(body, headers);

        ResponseEntity<ParameterMetida> data=restTemplate.exchange(
                "http://localhost:8085/game/parameters", HttpMethod.POST, entity, ParameterMetida.class);
        LOGGER.info(""+data.getBody().getStrategies());
    }
}
