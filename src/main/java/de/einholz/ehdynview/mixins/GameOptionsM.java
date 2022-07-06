package de.einholz.ehdynview.mixins;

import java.io.File;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import de.einholz.ehdynview.client.AvgFps;
import de.einholz.ehdynview.config.ConfigMgr;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
@Mixin(GameOptions.class)
public abstract class GameOptionsM {
    @Inject(method = "<init>(Lnet/minecraft/client/MinecraftClient;Ljava/io/File;)V", at = @At("TAIL"))
    private void removeTerrainUpdateCallback(final MinecraftClient client, final File optionsFile, final CallbackInfo ci) {
        boolean is64Bit = client.is64Bit();
        ((GameOptionsAM) this).setViewDistance(new SimpleOption<Integer>("options.renderDistance", SimpleOption.emptyTooltip(), (optionText, value) -> GameOptions.getGenericValueText(optionText, Text.translatable("options.chunks", value)), new SimpleOption.ValidatingIntSliderCallbacks(2, is64Bit && Runtime.getRuntime().maxMemory() >= 1000000000L ? 32 : 16), is64Bit ? 12 : 8, value -> {}));
    }

    @Inject(method = "getViewDistance()Lnet/minecraft/client/option/SimpleOption;", at = @At("RETURN"), cancellable = true)
    private void getViewDistanceM(final CallbackInfoReturnable<SimpleOption<Integer>> cir) {
        SimpleOption<Integer> ret = cir.getReturnValue();
        int initView = ret.getValue();
        int fps = AvgFps.getFps();
        if (fps < 1) ret.setValue(2);
        else if (fps < ConfigMgr.getInstance().getFpsMin()) ret.setValue(Math.max(2, ret.getValue() - 1));
        else if (fps > ConfigMgr.getInstance().getFpsMax()) ret.setValue(Math.min(32, ret.getValue() + 1));
        if (initView == ret.getValue()) cir.setReturnValue(cir.getReturnValue());
        AvgFps.delay();
        System.out.println(ret.getValue() + " Chunks at " + fps + " FPS");
        cir.setReturnValue(ret);
    }

    @Inject(method = "getClampedViewDistance()I", at = @At("HEAD"), cancellable = true)
    private void getClampedViewDistanceM(final CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(((GameOptionsAM) this).getServerViewDistance() > 0 ? Math.min(((GameOptions) (Object) this).getViewDistance().getValue(), ((GameOptionsAM) this).getServerViewDistance()) : ((GameOptions) (Object) this).getViewDistance().getValue());
    }

    // Probably doesn't work because GameOptions#viewDistance is private
    //@Redirect(method = "getClampedViewDistance()I", at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;viewDistance:Lnet/minecraft/client/option/SimpleOption", opcode = Opcodes.GETFIELD))
    //private SimpleOption<Integer> getViewDistance(GameOptions self) {
    //    return self.getViewDistance();
    //}
}
