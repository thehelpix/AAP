package ru.TheHelpix.aap.utils;

import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.TheHelpix.aap.Main;

import java.io.File;

public class Utils {
    private static final Main plugin = Main.getInstance();
    private static final File config = new File(plugin.getDataFolder() + File.separator + "config.yml");

    public static Boolean isConfig() {
        return config.exists();
    }

    public static void kickPlayer(Player p, String reason) {
        Bukkit.getScheduler().runTask(Main.getInstance(), () -> p.kickPlayer(Color.parseColors(reason)));
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
        if (isPermissions(p)) {
            if (ConfigUtils.isAdmin(p.getName())) {
                String randomCode = RandomStringUtils.randomNumeric(6);
                Main.codes.put(p.getName(), randomCode);
                VKUtils.sendCodeAdmin(p, randomCode, ConfigUtils.getIdAdmin(p.getName()));
                Main.getInstance().login.put(p, 0);
            } else {
                Utils.kickPlayer(p, "&6[AAP]\n"+MsgConfigUtils.getKickMsg("noIsAdmin"));
                AdminMessageUtils.noAdmin(p);
            }
        }
    }

    public static void enablePrint() {
        Color.log("");
        Color.log("&6<<<<<<<<<<<<< &e&lAAP &6>>>>>>>>>>>");
        Color.log("&6> &7Версия: &6" + plugin.getDescription().getVersion());
        Color.log("&6> &7Автор: &6TheHelpix");
        Color.log("&6> &7ВК автора: &6https://vk.com/sayhe");
        Color.log("&6> &6&lAdmin Account Protect");
        Color.log("&6<<<<<<<<<<<<< &e&lAAP &6>>>>>>>>>>>");
        Color.log("");
    }

    public static void checkUpdate(Main instance) {
        new UpdateChecker(instance).getVersion(version -> {
            if (Double.parseDouble(version) > Double.parseDouble(instance.getDescription().getVersion())) {
                Color.updateLog("&eНайдена новая версия плагина &6%new_version%&e. &7(У Вас %this_version%)".replace("%new_version%", version).replace("%this_version%", instance.getDescription().getVersion()));
                Color.updateLog("&eСкачать в &6SpigotMC&e:");
                Color.updateLog("&6spigotmc.org/resources/aap.84913");
            } else if (Double.parseDouble(version) < Double.parseDouble(instance.getDescription().getVersion())) {
                Color.updateLog("&eОго, Вы из будущего. У Вас версия плагина &c%this_version%, &eкогда в релизе версия &4%new_version%&e.".replace("%this_version%", instance.getDescription().getVersion()).replace("%new_version%", version));
            } else if (version.equals(instance.getDescription().getVersion())) {
                Color.updateLog("&eУ Вас самая новая версия плагина. &7(%this_version%)".replace("%this_version%", instance.getDescription().getVersion()));
            }
        });
    }

    public static Boolean isPermissions(Player player) {
        boolean bool = false;

        for (String perm : ConfigUtils.getPermsList()) {
            if (player.hasPermission(perm)) {
                bool = true;
            }
        }
        return bool;
    }
}
