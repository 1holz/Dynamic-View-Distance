package de.einholz.ehdynview.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import de.einholz.ehdynview.client.AvgFps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.WindowEventHandler;
import net.minecraft.util.thread.ReentrantThreadExecutor;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public interface MinecraftClientM extends /*ReentrantThreadExecutor<Runnable> implements*/ WindowEventHandler, AvgFps {
    //public MinecraftClientM(String string) {
    //    super(string);
    //}

    @Accessor("currentFps")
    public static int getCurrentFps() {
        throw new AssertionError();
    }
}
