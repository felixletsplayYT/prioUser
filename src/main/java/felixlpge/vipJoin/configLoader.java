package felixlpge.vipJoin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class configLoader {
    private final File vips = new File("config/vipJoin/vips.json");
    private final File configFile = new File("config/vipJoin/config.cfg");
    private HashMap<String, String> config;
    private JsonArray uuids;
    public configLoader() throws IOException {
        if (!configFile.getParentFile().exists()) configFile.getParentFile().mkdirs();
        if (vips.exists()){
            JsonElement jElement = new JsonParser().parse(new Scanner(vips).useDelimiter("\\Z").next());
            uuids = jElement.getAsJsonArray();
        }else{
            uuids = new JsonArray();
            uuids.add("Example user");
            saveFile();
        }
        createConfig();
        BufferedReader reader = new BufferedReader(new FileReader(configFile));
        String line;
        while((line = reader.readLine()) != null){
            if (line.contains(": ")){
                config.put(line.split(": ")[0], line.split(": ")[1]);
            }
        }
        reader.close();


    }

    public HashMap<String, String> getConfig() {
        return config;
    }

    public JsonArray getUuids() {
        return uuids;
    }

    public void addUserToVips(String vip) throws IOException {
        uuids.add(vip);
        saveFile();
    }

    public boolean containsUser(String uuid){
        for (JsonElement element : uuids){
            if (element.getAsString().equals(uuid)) return true;
        }
        return false;
    }

    private void saveFile() throws IOException {
        if (vips.exists()) vips.delete();
        vips.createNewFile();
        //new Gson().toJson(uuids, new FileWriter(vips));
        Writer writer = new FileWriter(vips);
        writer.write(uuids.toString());
        writer.close();
    }

    private void createConfig() throws IOException {
        if (!configFile.exists()){
            configFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
            writer.write("vip_slots: 1");
            writer.write("players: 20");
            writer.close();
        }
    }



}
