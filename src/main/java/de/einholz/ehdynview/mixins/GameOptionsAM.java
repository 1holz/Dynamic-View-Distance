package de.einholz.ehdynview.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;

@Environment(EnvType.CLIENT)
@Mixin(GameOptions.class)
public interface GameOptionsAM {
    @Mutable
    @Accessor("viewDistance")
    void setViewDistance(SimpleOption<Integer> option);

    @Accessor("serverViewDistance")
    int getServerViewDistance();
}
