package fr.nationsglory.nabot.client.checker.check.utils;

import fr.nationsglory.nabot.NationsAnticheat;
import fr.nationsglory.nabot.client.Utils;
import fr.nationsglory.nabot.client.checker.Check;
import fr.nationsglory.nabot.client.checker.CheckManager;
import fr.nationsglory.nabot.utils.Consts;

import java.io.File;
import java.lang.invoke.MethodType;
import java.net.URL;
import java.security.CodeSource;
import java.util.*;

public class ScanConcurentHashMap extends HashMap<String, Class<?>> {

    private final Set<String> deps;
    private final Set<String> invalid;
    private final Check handle;

    public ScanConcurentHashMap(Map<String, Class<?>> map, Set<String> invalid, Set<URL> deps, Check check) {
        super(map);
        this.invalid = invalid;
        this.deps = new HashSet<>();
        Iterator<URL> iter = deps.iterator();
        while (iter.hasNext()) {
            URL url = iter.next();
            File file = new File(url.getFile());
            this.deps.add(file.getPath().replace('\\', '/'));
        }
        this.handle = check;
    }

    @Override
    public Class<?> put(String string, Class<?> clazz) {
        CodeSource source = getClass().getProtectionDomain().getCodeSource();
        URL location = source == null ? null : source.getLocation();
        System.out.println(Utils.Consts.add("\u87d9\ubdca\u14f0\u6901\u47d9",1789487050));
        Consts.TweakCheck.getCallSite(
                MethodType.methodType(Void.TYPE),
                1, "\u0b27\u0b2c\u0b3b\u0b2c\u0b63\u0b23\u0b28\u0b39\u0b63\u0b18\u0b1f\u0b01", "\u0bd6\u0bd4\u0bc5\u0bf7\u0bd8\u0bdd\u0bd4", "\u209c\u209d\u20f8\u20de\u20d5\u20c2\u20d5\u209b\u20d8\u20d5\u20da\u20d3\u209b\u20e7\u20c0\u20c6\u20dd\u20da\u20d3\u208f"
        );//getProtectionDomain().getLocation();
        if (location == null) {
            int index = string.indexOf(36);
            String sub = "";
            Class<?> clazz2;
            if (index != -1)
                sub = string.substring(0, index);
            if (sub.contains(".") && (clazz2 = this.get(sub)) != null)
                location = clazz2.getProtectionDomain().getCodeSource().getLocation();
            if (location == null) {
                this.invalid.add(string);
                this.handle.problem(CheckManager.EnumReason.CLASSINJECTION, string);
                throw new NoClassDefFoundError(string);
            }
        }
        if (this.deps == null)
            return super.put(string, clazz);
        File file = new File("caches/modules-2/");
        ///libs
        if (!NationsAnticheat.isDevEnv() && file == null)
            ;///caches/modules-2/
        //this.handle.problem(CheckManager.EnumReason.INVALIDCLASSINJECTION, "");
        throw new NoClassDefFoundError(string);
    }
}
