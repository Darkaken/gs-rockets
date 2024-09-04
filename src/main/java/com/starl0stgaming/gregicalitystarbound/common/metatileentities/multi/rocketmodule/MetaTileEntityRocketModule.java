package com.starl0stgaming.gregicalitystarbound.common.metatileentities.multi.rocketmodule;

import javax.annotation.Nonnull;

import com.starl0stgaming.gregicalitystarbound.util.BlockStructure;
import com.starl0stgaming.gregicalitystarbound.util.Pair;
import com.google.gson.Gson;
import java.io.FileReader;
import java.util.List;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.pattern.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.starl0stgaming.gregicalitystarbound.api.recipes.GCSBRecipeMaps;
import com.starl0stgaming.gregicalitystarbound.api.telemetry.network.connection.endpoint.IEndpointSerializable;

import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneVariantBlock;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;

import static gregtech.api.util.RelativeDirection.*;

public class MetaTileEntityRocketModule extends RecipeMapMultiblockController {

    // Class not ready

    public int id;
    public String type;
    public int tier;
    public boolean required;
    public boolean canEdit;
    public List<List<String>> structure;

    private static final Map<Integer, String> MODULE_PATH_MAP = Map.of(

            // move this to another file or implement an auto file searcher
            // in case of having a static map, make sure you update it when adding or moving JSONs

            1, "json_module/test_engine.json",
            2, "json_module/test_control.json",
            3, "json_module/test_cockpit.json",
            4, "json_module/test_custom.json"
    );

    public MetaTileEntityRocketModule(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, null); // check if we can do this
        resetTileAbilities();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityRocketModule(this.metaTileEntityId);
    }

    private void loadFromJson(Integer moduleId) {
        Gson gson = new Gson();

        String jsonFilePath = MODULE_PATH_MAP.get(moduleId);

        if (jsonFilePath == null) {
            System.err.println("No JSON file mapped for module ID: " + moduleId);
            return;
        }

        try (FileReader reader = new FileReader(jsonFilePath)) {
            MetaTileEntityRocketModule module = gson.fromJson(reader, MetaTileEntityRocketModule.class);

            this.id = module.id;
            this.type = module.type;
            this.tier = module.tier;
            this.required = module.required;
            this.canEdit = module.canEdit;
            this.structure = module.structure;

        } catch (Exception e) {
            e.printStackTrace(); // replace with better logging
        }
    }

    public void resetTileAbilities() {
    }

    @Nonnull
    protected BlockPattern createStructurePattern() {

        FactoryBlockPattern patternBuilder = FactoryBlockPattern.start(RIGHT, FRONT, UP);

        for (List<String> aisle : structure) {
            String[] layers = aisle.toArray(new String[0]);
            patternBuilder.aisle(layers);  // Add each aisle dynamically
        }

        return patternBuilder
                .where('C', selfPredicate())
                .build();
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
    }
}