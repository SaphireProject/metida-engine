package metida;


import metida.JsonObject.TankJson;
import metida.data.Data;
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

public class App {

    private static Logger LOGGER = LoggerFactory.getLogger(App.class);

    private  static  final  String url="https://e0624559-e804-426c-a190-6932281dcb01.mock.pstmn.io";


    public static void main(String[] args) throws IOException {

       String q="0000000000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        LOGGER.info(""+q.length());


    }
}
