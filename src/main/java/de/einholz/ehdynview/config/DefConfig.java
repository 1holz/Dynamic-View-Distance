package de.einholz.ehdynview.config;

import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonPrimitive;
import net.minecraft.text.Text;

public final class DefConfig {
    public static final int FPS_MAX_VALUE = 60;
    public static final Text FPS_MAX_COMMENT = Text.translatable("config.ehdynview.fps_max.comment");
    public static final int FPS_MIN_VALUE = 30;
    public static final Text FPS_MIN_COMMENT = Text.translatable("config.ehdynview.fps_min.comment");
    public static final int BUFFER_SIZE_VALUE = 10;
    public static final Text BUFFER_SIZE_COMMENT = Text.translatable("config.ehdynview.buffer_size.comment");
    public static final long SAMPLING_PERIOD_VALUE = 2000000000; // in nanoseconds here: 2s
    public static final Text SAMPLING_PERIOD_COMMENT = Text.translatable("config.ehdynview.sampling_period.comment");

    private DefConfig() {}

    public static JsonObject setDefaults(final JsonObject json) {
        // Defaults
        json.putDefault("fpsMax", new JsonPrimitive((Integer) FPS_MAX_VALUE), null);
        json.putDefault("fpsMin", new JsonPrimitive((Integer) FPS_MIN_VALUE), null);
        json.putDefault("bufferSize", new JsonPrimitive((Integer) BUFFER_SIZE_VALUE), null);
        json.putDefault("samplingPeriod", new JsonPrimitive((Long) SAMPLING_PERIOD_VALUE), null);
        return json;
    }
}
