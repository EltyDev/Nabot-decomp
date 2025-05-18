package fr.nationsglory.nabot.utils;

public class Consts {

    public static class TweakCheck {

        public static <T> T deobfExecute() {
            return null;
        }

    }

    public static final String MODNAME = "NaBot";
    public static final String MCVERSION = "1.6.4";
    public static final String MODID = "nabot";
    public static final String VERSION = "Alpha-0.1";
    public static final String COLOREDPREFIX;
    public static final String PREFIX = "[NaBot] ";
    public static final String CLIENTPROXY = "fr.nationsglory.nabot.client.ClientProxy";
    public static final String BASEURL = "https://apiv2.nationsglory.fr/";
    public static final String COMMONPROXY = "fr.nationsglory.nabot.common.CommonProxy";

    static {
        COLOREDPREFIX = ""; //TODO
    }
}
