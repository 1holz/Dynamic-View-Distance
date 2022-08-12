package de.einholz.ehdynview.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public final class ConfigScreen implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("config.ehdynview.titel"))
                .setSavingRunnable(ConfigMgr::save);
            ConfigCategory general = builder.getOrCreateCategory(Text.translatable("config.ehdynview.category.general"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            general.addEntry(entryBuilder
                .startIntField(Text.translatable("config.ehdynview.fps_max"), ConfigMgr.getInstance().getFpsMax())
                .setDefaultValue(DefConfig.FPS_MAX_VALUE)
                .setTooltip(DefConfig.FPS_MAX_COMMENT)
                .setSaveConsumer(v -> ConfigMgr.getInstance().setFpsMax(v))
                .build());
            general.addEntry(entryBuilder
                .startIntField(Text.translatable("config.ehdynview.fps_min"), ConfigMgr.getInstance().getFpsMin())
                .setDefaultValue(DefConfig.FPS_MIN_VALUE)
                .setTooltip(DefConfig.FPS_MIN_COMMENT)
                .setSaveConsumer(v -> ConfigMgr.getInstance().setFpsMin(v))
                .build());
            general.addEntry(entryBuilder
                .startIntField(Text.translatable("config.ehdynview.buffer_size"), ConfigMgr.getInstance().getBufferSize())
                .setDefaultValue(DefConfig.BUFFER_SIZE_VALUE)
                .setTooltip(DefConfig.BUFFER_SIZE_COMMENT)
                .setSaveConsumer(v -> ConfigMgr.getInstance().setBufferSize(v))
                .build());
            general.addEntry(entryBuilder
                .startIntField(Text.translatable("config.ehdynview.sampling_period"), ConfigMgr.getInstance().getSamplingPeriod())
                .setDefaultValue(DefConfig.SAMPLING_PERIOD_VALUE)
                .setTooltip(DefConfig.SAMPLING_PERIOD_COMMENT)
                .setSaveConsumer(v -> ConfigMgr.getInstance().setSamplingPeriod(v))
                .build());
            return builder.build();
        };
    }
}
