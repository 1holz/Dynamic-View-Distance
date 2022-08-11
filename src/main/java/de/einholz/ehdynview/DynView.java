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
    public void onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("nochatreports")) {
            System.out.println("===================================");
            System.out.println("You don't have No Chat Reports installed!");
            System.out.println("This way players can unjustifiedly get banned from Minecraft.");
            System.out.println("For more information see https://saveminecraft.org/");
            System.out.println("To prevent this from happening I recommend to install No Chat Reports. It is availabel at:");
            System.out.println("https://www.curseforge.com/minecraft/mc-mods/no-chat-reports");
            System.out.println("===================================");
        }
    }

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
