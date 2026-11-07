package com.example.emeraldtools;

import net.fabricmc.api.ModInitializer;

public class EmeraldTools implements ModInitializer {
	public static final String MOD_ID = "emeraldtools";

	@Override
	public void onInitialize() {
		ModItems.initialize();
	}
}
