package fr.nationsglory.nabot.client.checker;

import fr.nationsglory.nabot.client.Utils;
import fr.nationsglory.nabot.utils.Consts;

import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CheckManager {

    public enum EnumReason {

        GUIINJECTION("ㆮ䨳ᎸI䨨ㆣ篊䨥ㆽ䨯ᎸN"),
        INJECTION("ㆠ䨨ᎸE䨥ㆽ篆䨩ㆧ"),
        TWEAKINJECTION("ㆽ䨱ᎸA䨭ㆠ篁䨬ㆬ䨥ᎸI䨩ㆧ"),
        REFLECTIONFAILED("ㆻ䨣ᎸL䨣ㆪ篛䨯ㆦ䨨ᎸA䨯ㆥ篊䨢"),
        URLINJECTION("ㆼ䨴ᎸI䨨ㆣ篊䨥ㆽ䨯ᎸN"),
        CLASSINJECTION("ㆪ䨪ᎸS䨵ㆠ篁䨬ㆬ䨥ᎸI䨩ㆧ"),
        INVALIDCLASSINJECTION("ጢ䨨ᎸA䨪ㆠ篋䨥ጢ䨧ᎸS䨯ㆧ篅䨣ጢ䨲ᎸO䨨"),
        TRYTORELOGGING("ㆽ䨴ᎸT䨩ㆻ篊䨪ㆦ䨡ᎸI䨨ㆮ"),
        TRYTOLOGGINGWITHOUTTOKENORUSERNAME("ㆽ䨴ᎸT䨩ㆥ節䨡ㆮ䨯ᎸG䨱ㆠ篛䨮ㆦ䨳ᎸT䨩ㆢ篊䨨ㆦ䨴ᎸS䨣ㆻ篁䨧ㆤ䨣"),
        TWEAKERINJECTION("ㆽ䨱ᎸA䨭ㆬ篝䨯ㆧ䨬ᎸC䨲ㆠ節䨨");

        private String reason;

        public String getReason() {
            return this.reason;
        }

        private EnumReason(String reason) {
            this.reason = reason;
        }

    }

    private List<Check> handles;

    public void onPostInit() {
        Iterator<Check> it = handles.iterator();
        Consts.TweakCheck.getCallSite(
                MethodType.methodType(Void.TYPE),
                1, "ଧବ଻ବୣଡବଣପୣ଎ଡବାା", "௖௔௅௿ௐ௜௔", "ₜ₝⃸⃞⃕⃂⃕ₛ⃘⃚⃓⃕ₛ⃧⃀⃆⃝⃚⃓₏"
        );
        while (it.hasNext()) {
            Check check = it.next();
            check.onPostInit();
        }
        System.out.println("Checking Completed!");
    }

    public CheckManager() {
        handles = new ArrayList<Check>();
        onPostInit();
    }

    public void refresh() {

    }

    public void onPreInit() {
        Iterator<Check> it = handles.iterator();
        while (it.hasNext()) {
            Check check = it.next();
            check.onPreInit();
        }
    }

}
