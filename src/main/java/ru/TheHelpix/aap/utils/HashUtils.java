package ru.TheHelpix.aap.utils;

import org.bukkit.entity.Player;
import ru.TheHelpix.aap.Main;

public class HashUtils {

    public static Boolean isHashAdmin(Player p) {
        return (Main.getInstance().login.containsKey(p) && Main.getInstance().login.get(p) == 0);
    }

    public static Boolean isHashAuthedAdmin(Player p) {
        return (Main.getInstance().login.containsKey(p) && Main.getInstance().login.get(p) != 0);
    }

    public static void removeHashAdmin(Player p) {
        Main.getInstance().login.remove(p);
    }

}
