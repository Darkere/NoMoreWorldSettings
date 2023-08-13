package com.darkere.nomoreworldsettings;

import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.forgespi.Environment;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MODID)
public class Main
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "nomoreworldsettings";
    private final ForgeConfigSpec.BooleanValue config;
    // Directly reference a slf4j logger
    ForgeConfigSpec spec;
    public Main()
    {
        var b = new ForgeConfigSpec.Builder();
        config = b.comment("Disable the More World Options Button.").define("buttondisabled",true);
        spec = b.build();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, spec);
        if(Environment.get().getDist() == Dist.CLIENT)
            MinecraftForge.EVENT_BUS.register(this);

    }

    @SubscribeEvent
    public void screeninit(ScreenEvent.Init.Post event)
    {
        if(!config.get())
            return;
        if(event.getScreen() instanceof CreateWorldScreen cwscreen){
            cwscreen.moreOptionsButton.active = false;
        }
    }
}