package fr.nationsglory.nabot.client.checker;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import fr.nationsglory.nabot.client.Utils;
import fr.nationsglory.nabot.utils.Consts;
import net.minecraft.client.Minecraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodType;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class Check {

    private static boolean isListInit = false;
    private static List<String> whitelistedStaff;

    public void problem(CheckManager.EnumReason enumReason, String string) {
        if (isStaff()) return;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("https://apiv2.nationsglory.fr/mods/bad_cheat_api?username=");
            sb.append(Minecraft.getMinecraft().getSession().getUsername());
            sb.append("&name=");
            sb.append(string.replaceAll(" ", "%20"));
            sb.append("&reason=");
            sb.append(enumReason.getReason());
            sb.append("&hwid=");
            sb.append(Utils.getMachineID());
            URL url = new URL(sb.toString());
            InputStreamReader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            bufferedReader.close();
        } catch (IOException error) {
            error.printStackTrace();
        }

    }

    public abstract void onPostInit();
    public abstract void onPreInit();

    public static boolean isStaff() {
        try {
            if (!isListInit) {
                URL url = new URL("https://apiv2.nationsglory.fr/mods/staff_members");
                Gson gson = new Gson();
                Type type = new TypeToken<List<String>>() {}.getType();
                InputStream inputStream = url.openStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                try {
                    whitelistedStaff = gson.fromJson(reader, type);
                } catch (Exception error) {
                    return false;
                }
                isListInit = true;
            }
            if (whitelistedStaff == null || !whitelistedStaff.contains(Minecraft.getMinecraft().getSession().getUsername()))
                return false;
        } catch (Exception error) {
            return false;
        }
        return true;
    }

}
