package de.einholz.ehtemp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;

public class TemplateMod implements ModInitializer, ClientModInitializer {
	@Override
	public void onInitialize() {
		
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void onInitializeClient() {
		
	}
}
