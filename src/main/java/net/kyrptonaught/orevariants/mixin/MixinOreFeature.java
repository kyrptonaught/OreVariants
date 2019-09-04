package net.kyrptonaught.orevariants.mixin;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Function;

@Mixin(OreFeature.class)
public abstract class MixinOreFeature extends Feature<OreFeatureConfig> {
    public MixinOreFeature(Function<Dynamic<?>, ? extends OreFeatureConfig> function_1) {
        super(function_1);
    }


    @Redirect(method = "generateVeinPart", at = @At(
            value = "INVOKE", target = "Lnet/minecraft/world/IWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    public boolean genOreVariantOre(IWorld iWorld, BlockPos blockPos, BlockState blockState, int i) {
       /* String name = (iWorld.getBlockState(blockPos).getBlock().getName().asString().toLowerCase() + blockState.getBlock().getName().asString()).toLowerCase().replaceAll("[^a-z]", "");
        System.out.println(name);
        BlockState oreVariants = Registry.BLOCK.get(new Identifier(OreVariantsMod.MOD_ID, name)).getDefaultState();
        return iWorld.setBlockState(blockPos, oreVariants, i);
       */
        return false;
    }
}
