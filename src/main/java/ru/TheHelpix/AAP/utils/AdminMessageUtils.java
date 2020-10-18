package ru.TheHelpix.AAP.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.TheHelpix.AAP.Main;

import java.net.InetAddress;

public class AdminMessageUtils {
    private static final Main plugin = Main.getInstance();

    public static void joinAdmin(Player p) {
        for (Player pp : Bukkit.getOnlinePlayers()) {
            if (Main.getInstance().login.get(pp) != 0) {
                pp.sendMessage(Color.parseColors("&6[AAP] &aАдминистратор &c"+p.getName()+"&a зашёл не сервер!"));
            }
        }
    }

    public static void noAdmin(Player p) {
        for (Player pp : Bukkit.getOnlinePlayers()) {
            if (pp.hasPermission("litebans.mute") || pp.isOp()) {
                InetAddress ip = p.getAddress().getAddress();
                pp.sendMessage(Color.parseColors("&6[AAP] &cПопытка входа на сервер &4"+p.getName()+"&c ip: &4"+ip));
            }
        }
    }

    public static void noAdminCode(Player p) {
        for (Player pp : Bukkit.getOnlinePlayers()) {
            if (Main.getInstance().login.get(pp) != 0) {
                InetAddress ip = p.getAddress().getAddress();
                pp.sendMessage(Color.parseColors("&6[AAP] &cАдминистратор &4"+p.getName()+"&c ввёл не правильно код ip: &4"+ip));
            }
        }
    }

    public static void loginAdmin(Player p) {
        for (Player pp : Bukkit.getOnlinePlayers()) {
            if (Main.getInstance().login.get(pp) != 0) {
                pp.sendMessage(Color.parseColors("&6[AAP] &aАдминистратор &2"+p.getName()+"&a авторизовался!"));
            }
        }
    }

    public static void kickRemoveAdmin(String p) {
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (players.getName().equals(p)) {
                Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        players.kickPlayer(Color.parseColors("&6[AAP]\n&cВас убрали из Протекта!"));
                    }
                });
            }
        }
    }
}
