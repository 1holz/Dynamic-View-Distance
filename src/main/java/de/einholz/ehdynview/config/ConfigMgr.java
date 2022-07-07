package de.einholz.ehdynview.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Jankson;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.api.SyntaxError;
import net.fabricmc.loader.api.FabricLoader;

public class ConfigMgr {
    private static Config instance = new Config();
    //TODO: make modid a field
    private static String path = Paths.get(FabricLoader.getInstance().getConfigDir().toString(), "ehdynview.json").toString();
    private static Jankson jankson = Jankson.builder().build();

    private ConfigMgr() {}

    public static void load() {
        JsonObject json = new JsonObject();
        try {
            json = jankson.load(path);
        } catch (SyntaxError e) {
            e.printStackTrace();
        }
        instance = jankson.fromJson(DefConfig.setDefaults(json), Config.class);
    }

    public static void save() {
        String result = jankson.toJson(getInstance()).toJson(true, true);
        File file = new File(path.toString());
        try {
            if (!file.exists()) file.createNewFile();
            FileOutputStream out = new FileOutputStream(file, false);
            out.write(result.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config getInstance() {
        return instance;
    }
}
