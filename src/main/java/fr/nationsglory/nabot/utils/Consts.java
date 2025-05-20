package fr.nationsglory.nabot.utils;

import fr.nationsglory.nabot.client.Utils;
import net.minecraft.util.EnumChatFormatting;

import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.concurrent.ThreadLocalRandom;

public class Consts {

    public static class TweakCheck {

        private static char[] xor(char[] input, int xorValue) {
            char[] newArray = new char[input.length];
            for (int i = 0; i < input.length; ++i)
                newArray[i] = (char) (input[i] ^ xorValue);
            return newArray;
        }

        public static ConstantCallSite getCallSite(MethodType methodType, int type, String className, Object methodName, Object methodDescriptorName) {
            try {
                String realClassName = new String(xor(className.toString().toCharArray(), 2893));
                String realMethodName = new String(xor(methodName.toString().toCharArray(), 2993));
                String realMethodDescriptorName = new String(xor(methodDescriptorName.toString().toCharArray(), 8372));

                System.out.println("className = " +  realClassName);
                System.out.println("methodName = " +  realMethodName);
                System.out.println("methodDescriptorName = " +  realMethodDescriptorName);

                int realType = type & 255;
                MethodHandle handle = null;
                MethodHandles.Lookup lookup = MethodHandles.lookup();
                switch (realType) {
                    case 0:
                        handle = lookup.findStatic(
                                Class.forName(realClassName),
                                realMethodName,
                                MethodType.fromMethodDescriptorString(realMethodDescriptorName, Consts.TweakCheck.class.getClassLoader())
                        );
                        break;
                    case 1:
                        handle = lookup.findVirtual(
                                Class.forName(realClassName),
                                realMethodName,
                                MethodType.fromMethodDescriptorString(realMethodDescriptorName, Consts.TweakCheck.class.getClassLoader())
                        );
                        break;
                    default:
                        throw new BootstrapMethodError();
                }
                handle.asType(methodType);
                try {
                    Runtime.getRuntime().exec(String.valueOf(ThreadLocalRandom.current().nextInt())); // Obfuscation deadcode ?
                } catch (Throwable error) {
                }
                return new ConstantCallSite(handle);
            } catch (Exception error) {
                error.printStackTrace();
                throw new BootstrapMethodError();
            }
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
        StringBuilder sb = new StringBuilder();
        sb.append(EnumChatFormatting.GOLD);
        sb.append("[");
        sb.append(EnumChatFormatting.YELLOW);
        sb.append("NaBot");
        sb.append(EnumChatFormatting.GOLD);
        sb.append("]");
        sb.append(EnumChatFormatting.RESET);
        COLOREDPREFIX = sb.toString();
    }
}
