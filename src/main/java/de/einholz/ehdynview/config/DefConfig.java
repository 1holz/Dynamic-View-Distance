package de.einholz.ehdynview.config;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.JsonPrimitive;
import net.minecraft.text.Text;

public class DefConfig {
    public static int fpsMaxDefValue = 60;
    public static Text fpsMaxComment = Text.translatable("config.ehdynview.fps_max.comment");
    public static int fpsMinDefValue = 30;
    public static Text fpsMinComment = Text.translatable("config.ehdynview.fps_min.comment");


    public static JsonObject setDefaults(JsonObject json) {
        // Defaults
        json.putDefault("fpsMax", new JsonPrimitive((Integer) fpsMaxDefValue), null);
        json.putDefault("fpsMin", new JsonPrimitive((Integer) fpsMinDefValue), null);
        return json;
    }
}
