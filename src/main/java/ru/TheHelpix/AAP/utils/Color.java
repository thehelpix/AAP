package ru.TheHelpix.AAP.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Color {

    public static String parseColors(String str){
        return str.replace('&', '§');
    }

    public static void info(String text) {
        Bukkit.getConsoleSender().sendMessage(parseColors("&6[AAP] "+text));
    }

    public static void log(String text) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    }
}
