package net.kyrptonaught.orevariants;

import net.kyrptonaught.orevariants.blocks.OreVariantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.Arrays;

public class OreVariant {
    private Block baseBlock;
    private Block[] ores;
    private OreVariantBlock[] oreVariantBlocks;

    public OreVariant(Block baseBlock, Block[] ores) {
        this.baseBlock = baseBlock;
        this.ores = ores;
        oreVariantBlocks = Arrays.stream(ores).map(ore -> new OreVariantBlock(baseBlock, ore)).toArray(OreVariantBlock[]::new);
    }

    public static Block[] ALL_ORES = new Block[]{Blocks.COAL_ORE, Blocks.DIAMOND_ORE, Blocks.GOLD_ORE, Blocks.IRON_ORE, Blocks.EMERALD_ORE, Blocks.REDSTONE_ORE, Blocks.LAPIS_ORE};
}
