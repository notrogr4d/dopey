package org.dopey.client.manager;

import org.dopey.client.mods.Module;
import org.dopey.client.mods.movement.XVelocity;
import org.dopey.client.mods.movement.AutoSprint;
import java.util.ArrayList;


public class ModuleManager {
    public static ArrayList<Module> list = new ArrayList<>();


    public static void init() {
        list.add(new AutoSprint());
        list.add(new XVelocity());
    }
}
