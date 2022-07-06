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
                .setDefaultValue(DefConfig.FPS_MAX_DEF_VALUE)
                .setTooltip(DefConfig.FPS_MAX_COMMENT)
                .setSaveConsumer(value -> ConfigMgr.getInstance().setFpsMax(value))
                .build());
            general.addEntry(entryBuilder
                .startIntField(Text.translatable("config.ehdynview.fps_min"), ConfigMgr.getInstance().getFpsMin())
                .setDefaultValue(DefConfig.FPS_MIN_DEF_VALUE)
                .setTooltip(DefConfig.FPS_MIN_COMMENT)
                .setSaveConsumer(value -> ConfigMgr.getInstance().setFpsMin(value))
                .build());
            return builder.build();
        };
    }
}
