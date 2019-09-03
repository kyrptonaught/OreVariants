package net.kyrptonaught.orevariants.config;

import blue.endless.jankson.Comment;
import net.kyrptonaught.orevariants.OreVariant;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigOreBlocks {
    public List<ConfigOreVariant> oreVariants = new ArrayList<>();

    public static class ConfigOreVariant {
        @Comment("Block ID for base block")
        public String baseBlockID;
        @Comment("Should generate all available ores")
        public Boolean allOres = false;
        @Comment("Ore IDs to generate in base")
        public String[] oreBlockIDs;

        public ConfigOreVariant() {
            this.baseBlockID = Registry.BLOCK.getId(Blocks.STONE).toString();
            this.oreBlockIDs = new String[]{Registry.BLOCK.getId(Blocks.IRON_ORE).toString()};
        }

        public ConfigOreVariant(Block base, Block[] ores) {
            this.baseBlockID = Registry.BLOCK.getId(base).toString();
            this.oreBlockIDs = Arrays.stream(ores).map(ore -> Registry.BLOCK.getId(ore).toString()).toArray(String[]::new);
        }

        public Block[] getOres() {
            Block[] ores = Arrays.stream(oreBlockIDs).map(ore -> Registry.BLOCK.get(new Identifier(ore))).toArray(Block[]::new);
            if (allOres)
                return ArrayUtils.addAll(ores, OreVariant.ALL_ORES);
            return ores;
        }

        public OreVariant toOreVariant() {
            if (allOres) return new OreVariant(Registry.BLOCK.get(new Identifier(baseBlockID)), OreVariant.ALL_ORES);
            Block[] ores = Arrays.stream(oreBlockIDs).map(ore -> Registry.BLOCK.get(new Identifier(ore))).toArray(Block[]::new);
            return new OreVariant(Registry.BLOCK.get(new Identifier(baseBlockID)), ores);
        }
    }
}
