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
    private final int fpsMin = 25;
    private final int fpsMax = 45;

    @Inject(method = "getViewDistance()Lnet/minecraft/client/option/SimpleOption;", at = @At("RETURN"), cancellable = true)
    private void getViewDistanceM(CallbackInfoReturnable<SimpleOption<Integer>> cir) {
        SimpleOption<Integer> ret = cir.getReturnValue();
        int initView = ret.getValue();
        int fps = AvgFps.getFps();
        if (fps < 1) ret.setValue(2);
        else if (fps < fpsMin) ret.setValue(Math.max(2, ret.getValue() - 1));
        else if (fps > fpsMax) ret.setValue(ret.getValue() + 1);
        if (initView == ret.getValue()) cir.setReturnValue(cir.getReturnValue());
        AvgFps.delay();
        System.out.println(ret.getValue() + " Chunks at " + fps + " FPS");
        cir.setReturnValue(ret);
    }

    @Inject(method = "getClampedViewDistance()I", at = @At("HEAD"), cancellable = true)
    private void getClampedViewDistanceM(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(((GameOptionsAM) this).getServerViewDistance() > 0 ? Math.min(((GameOptions) (Object) this).getViewDistance().getValue(), ((GameOptionsAM) this).getServerViewDistance()) : ((GameOptions) (Object) this).getViewDistance().getValue());
    }

    // Probably doesn't work because GameOptions#viewDistance is private
    //@Redirect(method = "getClampedViewDistance()I", at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;viewDistance:Lnet/minecraft/client/option/SimpleOption", opcode = Opcodes.GETFIELD))
    //private SimpleOption<Integer> getViewDistance(GameOptions self) {
    //    return self.getViewDistance();
    //}
}
