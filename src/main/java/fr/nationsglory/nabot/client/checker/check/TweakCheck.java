package fr.nationsglory.nabot.client.checker.check;

import fr.nationsglory.nabot.client.checker.Check;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

public class TweakCheck extends Check {

    public void check() {}

    @Override
    public void onPostInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onPreInit() {

    }

    @ForgeSubscribe
    public void onGui(GuiOpenEvent event) {

    }
}
