package de.einholz.ehdynview.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.api.SyntaxError;
import net.fabricmc.loader.api.FabricLoader;

public class ConfigMgr {
    public static Config INSTANCE = new Config();
    //TODO: make modid a field
    private static String path = Paths.get(FabricLoader.getInstance().getConfigDir().toString(), "ehdynview.json").toString();
    private static Jankson jankson = Jankson.builder().build();

    public static void load() {
        JsonObject json = new JsonObject();
        try {
            json = jankson.load(path);
        } catch (SyntaxError e) {
            e.printStackTrace();
        }
        INSTANCE = jankson.fromJson(DefConfig.setDefaults(json), Config.class);
    }

    public static void save() {
        String result = jankson.toJson(INSTANCE).toJson(true, true);
        File file = new File(path.toString());
        try {
            if(!file.exists()) file.createNewFile();
            FileOutputStream out = new FileOutputStream(file, false);
            out.write(result.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
