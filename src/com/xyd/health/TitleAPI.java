package com.xyd.health;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.entity.Player;

import org.bukkit.event.Listener;

import org.bukkit.plugin.java.JavaPlugin;

public class TitleAPI extends JavaPlugin implements Listener {

    //代码出自TitleAPI插件

    @Deprecated
    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
        sendTitle(player, fadeIn, stay, fadeOut, message, (String) null);
    }

    @Deprecated
    public static void sendSubtitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
        sendTitle(player, fadeIn, stay, fadeOut, (String) null, message);
    }

    @Deprecated
    public static void sendFullTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
    }

    public static void sendPacket(Player player, Object packet) {
        try {
            Object e = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
            Object playerConnection = e.getClass().getField("playerConnection").get(e);
            playerConnection.getClass().getMethod("sendPacket", new Class[]{getNMSClass("Packet")}).invoke(playerConnection, new Object[]{packet});
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public static Class getNMSClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        } catch (ClassNotFoundException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        TitleSendEvent titleSendEvent = new TitleSendEvent(player, title, subtitle);
        Bukkit.getPluginManager().callEvent(titleSendEvent);
        if (!titleSendEvent.isCancelled()) {
            try {
                Object var11;
                Constructor subtitleConstructor;
                if (title != null) {
                    title = ChatColor.translateAlternateColorCodes('&', title);
                    title = title.replaceAll("%player%", player.getDisplayName());
                    var11 = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get((Object) null);
                    Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[]{String.class}).invoke((Object) null, new Object[]{"{\"text\":\"" + title + "\"}"});
                    subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[]{getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE});
                    Object titlePacket = subtitleConstructor.newInstance(new Object[]{var11, chatTitle, fadeIn, stay, fadeOut});
                    sendPacket(player, titlePacket);
                    var11 = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get((Object) null);
                    chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[]{String.class}).invoke((Object) null, new Object[]{"{\"text\":\"" + title + "\"}"});
                    subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[]{getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent")});
                    titlePacket = subtitleConstructor.newInstance(new Object[]{var11, chatTitle});
                    sendPacket(player, titlePacket);
                }

                if (subtitle != null) {
                    subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
                    subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
                    var11 = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get((Object) null);
                    Object chatSubtitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[]{String.class}).invoke((Object) null, new Object[]{"{\"text\":\"" + title + "\"}"});
                    subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[]{getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE});
                    Object subtitlePacket = subtitleConstructor.newInstance(new Object[]{var11, chatSubtitle, fadeIn, stay, fadeOut});
                    sendPacket(player, subtitlePacket);
                    var11 = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get((Object) null);
                    chatSubtitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[]{String.class}).invoke((Object) null, new Object[]{"{\"text\":\"" + subtitle + "\"}"});
                    subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[]{getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE});
                    subtitlePacket = subtitleConstructor.newInstance(new Object[]{var11, chatSubtitle, fadeIn, stay, fadeOut});
                    sendPacket(player, subtitlePacket);
                }
            } catch (Exception var13) {
                var13.printStackTrace();
            }
        }
    }

    public static void clearTitle(Player player) {
        sendTitle(player, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), "", "");
    }

    public static void sendTabTitle(Player player, String header, String footer) {
        if (header == null) {
            header = "";
        }

        header = ChatColor.translateAlternateColorCodes('&', header);
        if (footer == null) {
            footer = "";
        }

        footer = ChatColor.translateAlternateColorCodes('&', footer);
        TabTitleSendEvent tabTitleSendEvent = new TabTitleSendEvent(player, header, footer);
        Bukkit.getPluginManager().callEvent(tabTitleSendEvent);
        if (!tabTitleSendEvent.isCancelled()) {
            header = header.replaceAll("%player%", player.getDisplayName());
            footer = footer.replaceAll("%player%", player.getDisplayName());

            try {
                Object ex = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[]{String.class}).invoke((Object) null, new Object[]{"{\"text\":\"" + header + "\"}"});
                Object tabFooter = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[]{String.class}).invoke((Object) null, new Object[]{"{\"text\":\"" + footer + "\"}"});
                Constructor titleConstructor = getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[]{getNMSClass("IChatBaseComponent")});
                Object packet = titleConstructor.newInstance(new Object[]{ex});
                Field field = packet.getClass().getDeclaredField("b");
                field.setAccessible(true);
                field.set(packet, tabFooter);
                sendPacket(player, packet);
            } catch (Exception var9) {
                var9.printStackTrace();
            }
        }
    }
}