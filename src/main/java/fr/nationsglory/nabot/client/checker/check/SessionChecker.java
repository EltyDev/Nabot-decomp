package fr.nationsglory.nabot.client.checker.check;

import fr.nationsglory.nabot.client.Utils;
import fr.nationsglory.nabot.client.checker.Check;
import fr.nationsglory.nabot.client.checker.CheckManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

public class SessionChecker extends Check {

    private String session;
    private boolean isInitialized;
    private String username;


    @Override
    public void onPostInit() {
    }

    @ForgeSubscribe
    public void onGuiOpened(GuiOpenEvent guiOpenEvent) {
        if (!this.isInitialized) {
            this.username = Minecraft.getMinecraft().getSession().getUsername();
            this.session = Minecraft.getMinecraft().getSession().getSessionID();
            this.isInitialized = true;
        }
        Session session = Minecraft.getMinecraft().getSession();
        if (this.username != null && this.session != null) {
            if (session.getUsername() == null || session.getSessionID() == null || session.getUsername() == "" || session.getSessionID() == "")
                this.problem(CheckManager.EnumReason.TRYTOLOGINGWITHOUTTOKENORUSERNAME, "");
            if (!session.getUsername().equalsIgnoreCase(this.username) || !session.getSessionID().equalsIgnoreCase(this.session)) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.username);
                sb.append("->");
                sb.append(session.getUsername());
                sb.append("&");
                sb.append(this.session);
                sb.append("->");
                sb.append(session.getSessionID());
                this.problem(CheckManager.EnumReason.TRYTORELOGGING, sb.toString());
            }
        } else
            this.problem(CheckManager.EnumReason.TRYTOLOGINGWITHOUTTOKENORUSERNAME, "");
    }

    @Override
    public void onPreInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
