package fr.nationsglory.nabot.client;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.nationsglory.nabot.client.checker.CheckManager;
import fr.nationsglory.nabot.common.CommonProxy;

public class ClientProxy extends CommonProxy {

    private static CheckManager checkManager;

    public void onPreInit(FMLPreInitializationEvent event) {
        checkManager = new CheckManager();
    }
}