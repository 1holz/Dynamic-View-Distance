package de.einholz.ehdynview.config;

import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonPrimitive;
import net.minecraft.text.Text;

public final class DefConfig {
    public static final int FPS_MAX_DEF_VALUE = 60;
    public static final Text FPS_MAX_COMMENT = Text.translatable("config.ehdynview.fps_max.comment");
    public static final int FPS_MIN_DEF_VALUE = 30;
    public static final Text FPS_MIN_COMMENT = Text.translatable("config.ehdynview.fps_min.comment");

    public static JsonObject setDefaults(final JsonObject json) {
        // Defaults
        json.putDefault("fpsMax", new JsonPrimitive((Integer) FPS_MAX_DEF_VALUE), null);
        json.putDefault("fpsMin", new JsonPrimitive((Integer) FPS_MIN_DEF_VALUE), null);
        return json;
    }
}
