package de.einholz.ehdynview;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;

public class DynView implements ModInitializer, ClientModInitializer, DedicatedServerModInitializer {
	@Override
	public void onInitialize() {
		
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void onInitializeClient() {
		
	}

	@Environment(EnvType.SERVER)
	@Override
	public void onInitializeServer() {
		
	}
}
