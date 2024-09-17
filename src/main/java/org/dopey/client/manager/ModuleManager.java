package org.dopey.client.manager;

import org.dopey.client.mods.Module;
import org.dopey.client.mods.movement.*;
import org.dopey.client.mods.pvp.AutoCrystal;
import org.dopey.client.mods.pvp.AutoTotem;
import org.dopey.client.mods.pvp.CrystalAura;
import org.dopey.client.mods.util.NoFall;
import org.dopey.client.mods.util.Velocity;

import java.util.ArrayList;


public class ModuleManager {
    public static ArrayList<Module> list = new ArrayList<>();


    public static void init() {
        list.add(new AutoSprint());
        list.add(new ElytraControl());
        list.add(new Speed());
        list.add(new AutoTotem());
        list.add(new CrystalAura());
        list.add(new CreativeFly());
        list.add(new NoFall());
        list.add(new AutoCrystal());
        list.add(new Velocity());
    }
}
