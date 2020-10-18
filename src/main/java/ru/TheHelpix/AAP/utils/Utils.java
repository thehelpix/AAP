package ru.TheHelpix.AAP.utils;

import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.TheHelpix.AAP.Main;

import java.io.File;

import static org.bukkit.Bukkit.getLogger;

public class Utils {
    private static final Main plugin = Main.getInstance();
    private static final File config = new File(plugin.getDataFolder() + File.separator + "config.yml");

    public static Boolean isConfig() {
        return config.exists();
    }

    public static Boolean isHashAdmin(Player p) {
        return (Main.getInstance().login.containsKey(p) && Main.getInstance().login.get(p) == 0);
    }

    public static void kickPlayer(Player p, String reason) {
        Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                p.kickPlayer(Color.parseColors(reason));
            }
        });
    }

    public static boolean checkCode(Player p, String code) {
        return Main.codes.containsKey(p.getName()) && Main.codes.get(p.getName()).equals(code);
    }

    public static String isOnlineAdmin(String p) {
        for (Player players : Bukkit.getOnlinePlayers()) {
             if (players.getName().equals(p)) {
                 return "&2Да!";
             } else {
                 return "&cНет";
             }
        }
        return "&cНет";
    }

    public static void sendCode(Player p) {
        if (p.isOp() || p.hasPermission("litebans.*")) {
            if (ConfigUtils.isAdmin(p.getName())) {
                String randomCode = RandomStringUtils.randomNumeric(6);
                Main.codes.put(p.getName(), randomCode);
                VKUtils.sendCodeAdmin(p, randomCode, ConfigUtils.getIdAdmin(p.getName()));
                Main.getInstance().login.put(p, 0);
                getLogger().info("idvk: " + ConfigUtils.getIdAdmin(p.getName()) + " ник: " + p.getName());
            } else {
                Utils.kickPlayer(p, "&6[AAP]\n&cПопытка входа.");
                getLogger().info("Попытка входа, ник: " + p.getName());
                AdminMessageUtils.noAdmin(p);
            }
        }
    }

    public static void enablePrint(){
        Color.log("");
        Color.log("&6<<<<<<<<<<<<< &6&lAAP &6>>>>>>>>>>>");
        Color.log("&6> &7Версия: &6" + plugin.getDescription().getVersion());
        Color.log("&6> &7Автор: &6TheHelpix");
        Color.log("&6> &7ВК автора: &6https://vk.com/sayhe");
        Color.log("&6> &6&lAdmin Account Protect");
        Color.log("&6<<<<<<<<<<<<< &6&lAAP &6>>>>>>>>>>>");
        Color.log("");
    }
}
