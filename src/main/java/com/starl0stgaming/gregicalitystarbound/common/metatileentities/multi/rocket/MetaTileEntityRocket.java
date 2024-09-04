package com.starl0stgaming.gregicalitystarbound.common.metatileentities.multi.rocket;

import static gregtech.api.util.RelativeDirection.*;

import javax.annotation.Nonnull;

import com.starl0stgaming.gregicalitystarbound.common.metatileentities.multi.rocketmodule.MetaTileEntityRocketModule;
import com.starl0stgaming.gregicalitystarbound.util.BlockStructure;
import com.starl0stgaming.gregicalitystarbound.util.Pair;
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

public class MetaTileEntityRocket {

    private List<MetaTileEntityRocketModule> rocketModules;

    // Constructor to initialize the rocket with an empty list of modules
    public Rocket() {
        this.rocketModules = new ArrayList<>();
    }

}
