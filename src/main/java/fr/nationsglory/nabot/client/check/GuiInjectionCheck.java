package fr.nationsglory.nabot.client.check;

import fr.nationsglory.nabot.client.checker.Check;
import fr.nationsglory.nabot.client.checker.CheckManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.EventBus;
import net.minecraftforge.event.ForgeSubscribe;

import java.net.URL;

public class GuiInjectionCheck extends Check {

    @Override
    public void onPostInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @ForgeSubscribe
    public void onGui(GuiOpenEvent guiOpenEvent) {
        if (guiOpenEvent.gui == null) return;
        URL url  = guiOpenEvent.getClass().getProtectionDomain().getCodeSource().getLocation();
        if (url != null) return;
        this.problem(CheckManager.EnumReason.GUIINJECTION, guiOpenEvent.getClass().getSimpleName());
    }

    @Override
    public void onPreInit() {

    }
}
