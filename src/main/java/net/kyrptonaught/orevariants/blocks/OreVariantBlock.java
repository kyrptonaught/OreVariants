package net.kyrptonaught.orevariants.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyrptonaught.orevariants.OreVariantsMod;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;


public class OreVariantBlock extends Block {
    private Block baseBlock;
    private Block baseOre;

    public OreVariantBlock(Block baseBlock, Block baseOre) {
        super(Block.Settings.copy(baseBlock));
        this.baseBlock = baseBlock;
        this.baseOre = baseOre;
        String name = (Registry.BLOCK.getId(baseBlock).getPath() + Registry.BLOCK.getId(baseOre).getPath()).toLowerCase().replaceAll("[^a-z]", "");
        if (Registry.BLOCK.get(new Identifier(OreVariantsMod.MOD_ID, name)) instanceof AirBlock) {
            Registry.register(Registry.BLOCK, new Identifier(OreVariantsMod.MOD_ID, name), this);
            Registry.register(Registry.ITEM, new Identifier(OreVariantsMod.MOD_ID, name), new OreBlockItem(this));
        }
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }


    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
      /*
        try {
            Method meth = Blocks.REDSTONE_ORE.getClass().getDeclaredMethod("appendProperties", stateFactory$Builder_1.getClass());
            meth.setAccessible(true);
            meth.invoke(Blocks.REDSTONE_ORE, stateFactory$Builder_1);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
       */
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState blockState_1, net.minecraft.world.loot.context.LootContext.Builder lootContext$Builder_1) {
        List<ItemStack> drops = baseOre.getDroppedStacks(blockState_1, lootContext$Builder_1);
        for (int i = 0; i < drops.size(); i++) {
            if (drops.get(i).getItem().equals(baseOre.asItem())) {
                drops.set(i, new ItemStack(this, drops.get(i).getCount()));
            }
        }
        return drops;
    }

    @Override
    public Identifier getDropTableId() {
        return baseOre.getDropTableId();
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Text getName() {
        return new TranslatableText("block.orevariants.oreblock", baseBlock.getName(), baseOre.getName());
    }
}
