package fr.nationsglory.nabot.client.checker.check.utils;

import fr.nationsglory.nabot.client.checker.Check;
import fr.nationsglory.nabot.client.checker.CheckManager;

import java.net.URL;
import java.util.ArrayList;

public class LockedArrayList extends ArrayList<URL> {

    private final Check handle;

    public LockedArrayList(ArrayList<URL> arrayList, Check check) {
        super(arrayList);
        this.handle = check;
    }

    @Override
    public boolean add(URL uRL) {
        if (!uRL.toExternalForm().contains("/Flan/")) {
            this.handle.problem(CheckManager.EnumReason.URLINJECTION, uRL.getFile());
            return false;
        }
        super.add(uRL);
        return true;
    }

}
