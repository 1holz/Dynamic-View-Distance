package de.einholz.ehdynview.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import de.einholz.ehdynview.client.AvgFps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;

@Environment(EnvType.CLIENT)
@Mixin(GameOptions.class)
public abstract class GameOptionsM {
    private final int fpsMin = 15;
    private final int fpsMax = 30;

    @Inject(method = "getViewDistance()Lnet/minecraft/client/option/SimpleOption;", at = @At("RETURN"), cancellable = true)
    private void getViewDistanceM(CallbackInfoReturnable<SimpleOption<Integer>> cir) {
        SimpleOption<Integer> ret = cir.getReturnValue();
        if (AvgFps.getFps() < 1) {
            ret.setValue(2);
            System.out.println("ViewDistance forced to " + ret.getValue() + " due to only having " + AvgFps.getFps());
        } else if (AvgFps.getFps() < fpsMin) {
            ret.setValue(Math.max(2, ret.getValue() - 1));
            System.out.println("ViewDistance reduced to " + ret.getValue() + " due to only having " + AvgFps.getFps());
        } else if (AvgFps.getFps() > fpsMax) {
            ret.setValue(ret.getValue() + 1);
            System.out.println("ViewDistance increaced to " + ret.getValue() + " due to having " + AvgFps.getFps());
        }
        cir.setReturnValue(ret);
    }
}
