package net.kyrptonaught.orevariants;

import com.swordglowsblue.artifice.api.Artifice;
import com.swordglowsblue.artifice.api.resource.TemplateResource;
import net.kyrptonaught.orevariants.config.ConfigOreBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ArtificeHelper {
    static void generateAssets() {
        String stateTemplate = readTemplateFile("blockstate_oreblock");
        String modelTemplate = readTemplateFile("model_oreblock");
        String modelInvTemplate = readTemplateFile("model_oreblock_inventory");
        Artifice.registerAssets(OreVariantsMod.MOD_ID + ":oreblocks", pack -> {
            for (ConfigOreBlocks.ConfigOreVariant oreVariant : OreVariantsMod.config.getConfig().oreVariants) {
                for (Block ore : oreVariant.getOres()) {
                    String baseID = new Identifier(oreVariant.baseBlockID).getPath();
                    String oreID = Registry.BLOCK.getId(ore).getPath();
                    String builtName = (baseID + oreID).replaceAll("[^a-z]", "");
                    pack.add(new Identifier(OreVariantsMod.MOD_ID, "blockstates/" + builtName + ".json"), new TemplateResource(stateTemplate)
                            .expand("baseblock", "block/" + baseID)
                            .expand("oretype", builtName));
                    pack.add(new Identifier(OreVariantsMod.MOD_ID, "models/block/" + builtName + ".json"), new TemplateResource(modelTemplate)
                            .expand("oretype", new Identifier(OreVariantsMod.MOD_ID, "blocks/vanilla/" + oreID + "_overlay").toString()));
                    pack.add(new Identifier(OreVariantsMod.MOD_ID, "models/block/" + builtName + "_inventory.json"), new TemplateResource(modelInvTemplate)
                            .expand("oretype", new Identifier(OreVariantsMod.MOD_ID, "blocks/vanilla/" + oreID + "_overlay").toString())
                            .expand("baseblock", "block/" + baseID));
                    pack.addItemModel(new Identifier(OreVariantsMod.MOD_ID, builtName), model -> {
                        model.parent(new Identifier(OreVariantsMod.MOD_ID, "block/" + builtName + "_inventory"));
                    });
                }
            }
        });
    }

    private static String readTemplateFile(String templateFile) {
        String file = "/template/" + templateFile + ".json";
        InputStream in = OreVariantsMod.class.getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.lines().collect(Collectors.joining());
    }
}