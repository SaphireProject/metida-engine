package metida.service;

import com.google.gson.Gson;
import metida.data.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileService {

    public static Data getConfigTest(String path){

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Data json = gson.fromJson(bufferedReader, Data.class);
        return json;
    }
}
