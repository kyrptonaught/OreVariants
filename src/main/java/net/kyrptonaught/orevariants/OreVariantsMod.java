package net.kyrptonaught.orevariants;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.kyrptonaught.orevariants.config.ConfigManager;
import net.kyrptonaught.orevariants.config.ConfigOreBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class OreVariantsMod implements ModInitializer, ClientModInitializer {
    public static final String MOD_ID = "orevariants";
    public static ItemGroup oreblocks = FabricItemGroupBuilder.build(new Identifier("orevariants", "oreblocks"), () -> new ItemStack(Items.DIAMOND_PICKAXE));
    public static ConfigManager config = new ConfigManager();
    public static OreVariant[] oreVariants;


    @Override
    public void onInitialize() {
        config.loadConfig();
        oreVariants = config.getConfig().oreVariants.stream().map(ConfigOreBlocks.ConfigOreVariant::toOreVariant).toArray(OreVariant[]::new);
    }


    @Override
    public void onInitializeClient() {
        ArtificeHelper.generateAssets();
    }
}
