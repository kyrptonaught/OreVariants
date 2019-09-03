package net.kyrptonaught.orevariants.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyrptonaught.orevariants.OreVariantsMod;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class OreBlockItem extends BlockItem {
    private OreVariantBlock oreBlock;

    OreBlockItem(OreVariantBlock block_1) {
        super(block_1, new Item.Settings().group(OreVariantsMod.oreblocks));
        oreBlock = block_1;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Text getName() {
        return oreBlock.getName();
    }

    @Override
    public Text getName(ItemStack itemStack_1) {
        return getName();
    }
}
