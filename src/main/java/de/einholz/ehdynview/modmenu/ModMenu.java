package de.einholz.ehdynview.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import de.einholz.ehdynview.config.ConfigMgr;
import de.einholz.ehdynview.config.DefConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public class ModMenu implements ModMenuApi {
    

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
                .startIntField(Text.translatable("config.ehdynview.fps_max"), ConfigMgr.INSTANCE.fpsMax)
                .setDefaultValue(DefConfig.fpsMaxDefValue)
                .setTooltip(DefConfig.fpsMaxComment)
                .setSaveConsumer(value -> ConfigMgr.INSTANCE.fpsMax = value)
                .build());
            general.addEntry(entryBuilder
                .startIntField(Text.translatable("config.ehdynview.fps_min"), ConfigMgr.INSTANCE.fpsMin)
                .setDefaultValue(DefConfig.fpsMinDefValue)
                .setTooltip(DefConfig.fpsMinComment)
                .setSaveConsumer(value -> ConfigMgr.INSTANCE.fpsMin = value)
                .build());
            return builder.build();
        };
    }
}
