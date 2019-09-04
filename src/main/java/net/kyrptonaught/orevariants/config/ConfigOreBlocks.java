package net.kyrptonaught.orevariants.config;

import blue.endless.jankson.Comment;
import net.kyrptonaught.orevariants.OreVariant;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class ConfigOreBlocks {
    public List<ConfigOreVariant> oreVariants = new ArrayList<>();

    public static class ConfigOreVariant {
        @Comment("Block ID for base block")
        public Block baseBlock;
        @Comment("Should generate all available ores")
        public Boolean allOres = false;
        @Comment("Ore IDs to generate in base")
        public Block[] oreBlocks;

        public ConfigOreVariant() {
            this.baseBlock = Blocks.STONE;
            this.oreBlocks = new Block[]{Blocks.IRON_ORE};
        }

        public ConfigOreVariant(Block base, Block[] ores) {
            this.baseBlock = base;
            this.oreBlocks = ores;
        }

        public Block[] getOres() {
            if (allOres) return ArrayUtils.addAll(oreBlocks, OreVariant.ALL_ORES);
            return oreBlocks;
        }

        public OreVariant toOreVariant() {
            if (allOres) return new OreVariant(baseBlock, OreVariant.ALL_ORES);
            return new OreVariant(baseBlock, oreBlocks);
        }
    }
}
