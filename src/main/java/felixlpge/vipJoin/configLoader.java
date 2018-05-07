package felixlpge.vipJoin;

import com.google.gson.Gson;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class configLoader {
    private final File vips = new File("config/vipJoin/vips.json");
    private final File configFile = new File("config/vipJoin/config.cfg");
    private HashMap<String, String> config;
    private Gson gson;
    private String[] uuids;
    public configLoader() throws IOException {
        config = new HashMap<>();
        gson = new Gson();
        if (!configFile.getParentFile().exists()) configFile.getParentFile().mkdirs();
        if (vips.exists()){
            uuids = gson.fromJson(new FileReader(vips), String[].class);
        }else{
            uuids = new String[]{"01c5ce3e-bf81-4a44-97ae-ebe9d7dd668f", "78a049a7-e802-4b26-a8d7-8021052e637f"};
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

    public String[] getUuids() {
        return uuids;
    }

    public void addUserToVips(String vip) throws IOException {
        List<String> list = Arrays.asList(uuids);
        list.add(vip);
        uuids = (String[]) list.toArray();
        saveFile();
    }

    public boolean containsUser(String uuid){
        List<String> list = Arrays.asList(uuids);
        return list.contains(uuid);
    }

    private void saveFile() throws IOException {
        if (vips.exists()) vips.delete();
        vips.createNewFile();
        String json = gson.toJson(uuids);
        Writer writer = new FileWriter(vips);
        writer.write(json);
        writer.close();
    }

    private void createConfig() throws IOException {
        if (!configFile.exists()){
            configFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
            writer.write("players: 20");
            writer.close();
        }
    }



}
