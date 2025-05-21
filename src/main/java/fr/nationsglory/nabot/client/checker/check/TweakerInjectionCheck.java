package fr.nationsglory.nabot.client.checker.check;

import fr.nationsglory.nabot.client.Utils;
import fr.nationsglory.nabot.client.checker.Check;
import fr.nationsglory.nabot.client.checker.CheckManager;
import fr.nationsglory.nabot.utils.Consts;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.lang.invoke.MethodType;

public class TweakerInjectionCheck extends Check {

    @Override
    public void onPreInit() {}

    private boolean isClassPathCheck() {
        String path = System.getProperty("java.class.path");
        String separator = System.getProperty("path.separator");
        String[] split = path.split(separator);;
        if (split.length != 30)
            return true;
        for (int i = 0; i < split.length; ++i) {
            File file = new File(split[i]);
            String name = file.getName();
            if (name.equals("minecraft.jar")) continue;
            String modifiedPath = split[i].replace("\\", "/");
            StringBuilder sb = new StringBuilder();
            String mcDataDir = Minecraft.getMinecraft().mcDataDir.getPath().replace("\\", "/");
            sb.append(mcDataDir);
            sb.append("/libs/");
            sb.append(name);
            if (!modifiedPath.equals(sb.toString()))
                return false;
            sb = new StringBuilder();
            sb.append(mcDataDir);
            sb.append("/libs/../");
            sb.append(name);
            if (!modifiedPath.equals(sb.toString()))
                return false;
        }
        return true;
    }

    @Override
    public void onPostInit() {
        if (!this.isClassPathCheck())
            this.problem(CheckManager.EnumReason.TWEAKERINJECTION, "");
    }
}
