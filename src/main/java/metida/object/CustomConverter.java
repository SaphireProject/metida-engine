package metida.object;

import com.google.gson.*;

import java.lang.reflect.Type;

public class CustomConverter implements JsonSerializer<Tank>, JsonDeserializer<Tank> {

    //не нужен наверно, не придеся считывать
    @Override
    public Tank deserialize(JsonElement jsonElement , Type type , JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(Tank tank , Type type , JsonSerializationContext jsonSerializationContext) {
        return null;
    }
}
