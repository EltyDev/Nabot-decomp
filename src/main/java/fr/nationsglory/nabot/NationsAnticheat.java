package fr.nationsglory.nabot;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import fr.nationsglory.nabot.client.Utils;
import fr.nationsglory.nabot.common.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import fr.nationsglory.nabot.utils.Consts;
import net.minecraft.launchwrapper.Launch;

import java.io.File;
import java.lang.invoke.*;
import java.util.Map;

@Mod(modid = Consts.MODID, name = Consts.MODNAME, version = Consts.VERSION, acceptedMinecraftVersions = Consts.VERSION)
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class NationsAnticheat
{

    @SidedProxy(clientSide=Consts.CLIENTPROXY, serverSide=Consts.COMMONPROXY)
    public static CommonProxy proxy;

    @Mod.Instance
    private static NationsAnticheat instance;

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        proxy.onInit(event);
        Utils.getMachineID();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.onPreInit(event);
    }

    public static NationsAnticheat getNaBot() {
       return instance;
    }

    public static boolean isDevEnv() {
        Boolean isIn = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
        return isIn.booleanValue();
    }

    private boolean isClient() {
        return true; // return (int)((long)-1438779456 ^ (long)-1438779455) != 0;
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.onPostInit(event);
    }

}
