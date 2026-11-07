package com.example.emeraldtools;

import java.util.function.Function;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;

public final class ModItems {
	// Blocks in this tag will NOT drop when mined with emerald tools (see data/.../tags/block).
	public static final TagKey<Block> INCORRECT_FOR_EMERALD_TOOL = TagKey.create(
			Registries.BLOCK, Identifier.fromNamespaceAndPath(EmeraldTools.MOD_ID, "incorrect_for_emerald_tool"));

	// Items in this tag repair emerald tools in an anvil (see data/.../tags/item).
	public static final TagKey<Item> REPAIRS_EMERALD_TOOLS = TagKey.create(
			Registries.ITEM, Identifier.fromNamespaceAndPath(EmeraldTools.MOD_ID, "repairs_emerald_tools"));

	// Between iron and diamond.
	public static final ToolMaterial EMERALD_TOOL_MATERIAL = new ToolMaterial(
			INCORRECT_FOR_EMERALD_TOOL, // blocks that won't drop
			1000, // durability (iron 250, diamond 1561)
			7.0F, // mining speed (iron 6, diamond 8)
			2.5F, // attack damage bonus (iron 2, diamond 3)
			18, // enchantability
			REPAIRS_EMERALD_TOOLS // repair items
	);

	public static final Item EMERALD_SWORD = register("emerald_sword", Item::new,
			new Item.Properties().sword(EMERALD_TOOL_MATERIAL, 3.0F, -2.4F));

	public static final Item EMERALD_PICKAXE = register("emerald_pickaxe", Item::new,
			new Item.Properties().pickaxe(EMERALD_TOOL_MATERIAL, 1.0F, -2.8F));

	public static final Item EMERALD_AXE = register("emerald_axe",
			props -> new AxeItem(EMERALD_TOOL_MATERIAL, 5.0F, -3.0F, props),
			new Item.Properties());

	public static final Item EMERALD_SHOVEL = register("emerald_shovel",
			props -> new ShovelItem(EMERALD_TOOL_MATERIAL, 1.5F, -3.0F, props),
			new Item.Properties());

	public static final Item EMERALD_HOE = register("emerald_hoe",
			props -> new HoeItem(EMERALD_TOOL_MATERIAL, -2.5F, 0.0F, props),
			new Item.Properties());

	private static Item register(String name, Function<Item.Properties, Item> factory, Item.Properties props) {
		ResourceKey<Item> key = ResourceKey.create(
				Registries.ITEM, Identifier.fromNamespaceAndPath(EmeraldTools.MOD_ID, name));
		Item item = factory.apply(props.setId(key));
		Registry.register(BuiltInRegistries.ITEM, key, item);
		return item;
	}

	public static void initialize() {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
				.register(tab -> tab.accept(EMERALD_SWORD));

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
				.register(tab -> {
					tab.accept(EMERALD_SHOVEL);
					tab.accept(EMERALD_PICKAXE);
					tab.accept(EMERALD_AXE);
					tab.accept(EMERALD_HOE);
				});
	}
}
