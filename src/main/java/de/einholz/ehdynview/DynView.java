package de.einholz.ehdynview;

import de.einholz.ehdynview.config.ConfigMgr;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public final class DynView implements ModInitializer, ClientModInitializer, DedicatedServerModInitializer {
    @Override
    public void onInitialize() {}

    @Environment(EnvType.CLIENT)
    @Override
    public void onInitializeClient() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config")) {
            ConfigMgr.load();
            ConfigMgr.save();
        }
    }

	@Environment(EnvType.SERVER)
	@Override
	public void onInitializeServer() {}
}
