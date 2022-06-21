package de.einholz.ehdynview.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.GameOptions;

@Environment(EnvType.CLIENT)
@Mixin(GameOptions.class)
public interface GameOptionsAM {
    @Accessor("serverViewDistance")
    public int getServerViewDistance();
}
