package ru.TheHelpix.aap.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Color {

    public static String parseColors(String str){
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static void info(String text) {
        Bukkit.getConsoleSender().sendMessage(parseColors("&6[AAP] "+text));
    }

    public static void log(String text) {
        Bukkit.getConsoleSender().sendMessage(parseColors(text));
    }

    public static void updateLog(String text) {
        Bukkit.getConsoleSender().sendMessage(parseColors("&6[&eAAP Updater&6] &e"+text));
    }
}
