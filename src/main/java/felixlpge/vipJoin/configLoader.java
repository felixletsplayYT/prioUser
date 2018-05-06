package felixlpge.vipJoin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class configLoader {
    private HashMap<String, String> config;
    private JsonArray uuids;
    public configLoader() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("config/vipJoin/config.cfg")));
        String line;
        while((line = reader.readLine()) != null){
            if (line.contains(": ")){
                config.put(line.split(": ")[0], line.split(": ")[1]);
            }
        }
        reader.close();
        JsonElement jElement = new JsonParser().parse(new Scanner(new File("config/vipJoin/vips.json")).useDelimiter("\\Z").next());
        uuids = jElement.getAsJsonArray();

    }

    public HashMap<String, String> getConfig() {
        return config;
    }

    public JsonArray getUuids() {
        return uuids;
    }

    public void addUserToVips(String vip){
        uuids.add(vip);

    }

}
