package fr.nationsglory.nabot.client.checker;

import cpw.mods.fml.relauncher.ReflectionHelper;
import fr.nationsglory.nabot.client.checker.check.GuiInjectionCheck;
import fr.nationsglory.nabot.client.checker.check.SessionChecker;
import fr.nationsglory.nabot.client.checker.check.TweakCheck;
import fr.nationsglory.nabot.client.checker.check.TweakerInjectionCheck;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.net.URL;
import java.util.*;

public class CheckManager {

    public enum EnumReason {

        GUIINJECTION("GUI_INJECTION"),
        INJECTION("MOD_INJECTION"),
        TWEAKINJECTION("TWEAK_INJECTION"),
        REFLECTIONFAILED("LOOKINGFIELD_FAILED"),
        URLINJECTION("URL_INJECTION"),
        CLASSINJECTION("CLASS_INJECTION"),
        INVALIDCLASSINJECTION("INVALID_CLASS_INJECTION"),
        TRYTORELOGGING("TRY_TO_RELOGGING"),
        TRYTOLOGINGWITHOUTTOKENORUSERNAME("TRY_TO_LOGIN_WITHOUT_TOKEN_OR_USERNAME"),
        TWEAKERINJECTION("TWEAKER_INJECTION");

        private String reason;

        public String getReason() {
            return this.reason;
        }

        EnumReason(String reason) {
            this.reason = reason;
        }

    }

    private List<Check> handles;

    public void onPostInit() {
        Iterator<Check> it = handles.iterator();
        while (it.hasNext()) {
            Check check = it.next();
            check.onPostInit();
        }
        System.out.println("Checking Completed!");
    }

    public CheckManager() {
        Launch.classLoader.addTransformerExclusion("fr.nationsglory");
        HashSet<URL> sourcesSet = new HashSet<>(Launch.classLoader.getSources());
        ArrayList<URL> sourcesList = new ArrayList<>(sourcesSet);
        String[] arrays = new String[1];
        arrays[0] = "sources";
        ReflectionHelper.setPrivateValue(LaunchClassLoader.class, Launch.classLoader, sourcesList, arrays);
        System.out.println("Checking...");
        this.handles = new ArrayList<>();
        this.handles.add(new GuiInjectionCheck());
        this.handles.add(new SessionChecker());
        this.handles.add(new TweakCheck());
        this.handles.add(new TweakerInjectionCheck());
        this.onPreInit();
        this.onPostInit();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                CheckManager.this.refresh();
            }
        };
        timer.schedule(task, 5000);
    }

    public void refresh() {
        Iterator<Check> it = handles.iterator();
        while (it.hasNext()) {
            Check check = it.next();
            if (check instanceof TweakCheck)
                ((TweakCheck) check).check();
        }
    }

    public void onPreInit() {
        Iterator<Check> it = handles.iterator();
        while (it.hasNext()) {
            Check check = it.next();
            check.onPreInit();
        }
    }

}
