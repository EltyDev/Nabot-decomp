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
        /*Consts.TweakCheck.invokeDynamic(Consts.TweakCheck.getCallSite(
                MethodHandles.lookup(),
                "\u200b\u2006\u200a\u200d\u2009\u2003\u200e\u2002\u2000\u2003",
                MethodType.methodType(Void.TYPE, Object.class, FMLInitializationEvent.class),
                1,
                "ଫିୣଣବହତଢଣାପଡଢି\u0b34ୣଣବଯଢହୣମଢଠଠଢଣୣ\u0b0eଢଠଠଢଣଝିଢଵ\u0b34",
                "\u0bde\u0bdf௸\u0bdf\u0bd8\u0bc5",
                "ₜ\u20f8⃗\u20c4\u20c3ₛ⃙⃛⃐\u20c7ₛ⃒⃙⃘ₛ⃙⃙⃚⃗⃛⃛ₛ⃑\u20c2⃚⃑\u20c0ₛ\u20f2\u20f9\u20f8\u20fd⃚⃝\u20c0⃝⃘⃕⃝\u20ce⃕\u20c0⃝⃚⃛\u20f1\u20c2⃚⃑\u20c0\u208f\u209d⃢"
        ), proxy, event);*/
        proxy.onInit(event);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        /*Consts.TweakCheck.invokeDynamic(Consts.TweakCheck.getCallSite(
                MethodHandles.lookup(),
                "\u2005\u2006\u200d\u2000\u2002\u2003\u200a\u200c\u2005\u200e",
                MethodType.methodType(Void.TYPE, Object.class, FMLPreInitializationEvent.class),
                1,
                "ଫିୣଣବହତଢଣାପଡଢି\u0b34ୣଣବଯଢହୣମଢଠଠଢଣୣ\u0b0eଢଠଠଢଣଝିଢଵ\u0b34",
                "\u0bde\u0bdf\u0be1\u0bc3\u0bd4௸\u0bdf\u0bd8\u0bc5",
                "ₜ\u20f8⃗\u20c4\u20c3ₛ⃙⃛⃐\u20c7ₛ⃒⃙⃘ₛ⃙⃙⃚⃗⃛⃛ₛ⃑\u20c2⃚⃑\u20c0ₛ\u20f2\u20f9\u20f8⃤\u20c6⃑\u20fd⃚⃝\u20c0⃝⃘⃕⃝\u20ce⃕\u20c0⃝⃚⃛\u20f1\u20c2⃚⃑\u20c0\u208f\u209d⃢"
        ), proxy, event);*/
        proxy.onPreInit(event);
    }

    public static NationsAnticheat getNaBot() {
       return instance;
    }

    public static boolean isDevEnv() {
        String value = Utils.Consts.add("䓎蒦㓯訉纣컪䨫ຎ䓎蒾㓰詄约컻䨡ຈ䓭蒥㓵詎纵컠䨪ກ䓍蒥㓷", 1379173518);
        /*Boolean isIn = Consts.TweakCheck.invokeDynamic(Consts.TweakCheck.getCallSite(
                MethodHandles.lookup(),
                "",
                MethodType.methodType(Object.class, Object.class, Object.class),
                1,
                "ଧବ଻ବୣସହତଡୣ଀ବଽ",
                "௖௔௅",
                "ₜ⃸⃞⃕⃂⃕ₛ⃘⃚⃓⃕ₛ⃻⃖⃞⃑⃗⃀₏₝⃸⃞⃕⃂⃕ₛ⃘⃚⃓⃕ₛ⃻⃖⃞⃑⃗⃀₏"

        ), Launch.blackboard, value);
        return Consts.TweakCheck.invokeDynamic(Consts.TweakCheck.getCallSite(
                MethodHandles.lookup(),
                "",
                MethodType.methodType(Boolean.TYPE, Object.class),
                1,
                "ଧବ଻ବୣଡବଣପୣଏଢଢଡନବଣ",
                "௓௞௞௝௔ௐ௟௧ௐ௝௄௔",
                "ₜ₝⃮"
        ), isIn);*/
        Boolean isIn = (Boolean) Launch.blackboard.get(value);
        return isIn.booleanValue();
    }

    private boolean isClient() {
        return true; // return (int)((long)-1438779456 ^ (long)-1438779455) != 0;
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        /*Consts.TweakCheck.invokeDynamic(Consts.TweakCheck.getCallSite(
                MethodHandles.lookup(),
                "\u2005\u2005\u2000\u2006\u2006\u200f\u200b \u2005\u200e",
                MethodType.methodType(Void.TYPE, Object.class, FMLPostInitializationEvent.class),
                1,
                "ଫିୣଣବହତଢଣାପଡଢି\u0b34ୣଣବଯଢହୣମଢଠଠଢଣୣ\u0b0eଢଠଠଢଣଝିଢଵ\u0b34",
                "\u0bde\u0bdf\u0be1\u0bdeூ\u0bc5௸\u0bdf\u0bd8\u0bc5",
                "ₜ\u20f8⃗\u20c4\u20c3ₛ⃙⃛⃐\u20c7ₛ⃒⃙⃘ₛ⃙⃙⃚⃗⃛⃛ₛ⃑\u20c2⃚⃑\u20c0ₛ\u20f2\u20f9\u20f8⃤⃛\u20c7\u20c0\u20fd⃚⃝\u20c0⃝⃘⃕⃝\u20ce⃕\u20c0⃝⃚⃛\u20f1\u20c2⃚⃑\u20c0\u208f\u209d⃢"
        ), proxy, event);*/
        proxy.onPostInit(event);
    }

}
