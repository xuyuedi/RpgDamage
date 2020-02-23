package com.xyd.health;

import lk.vexview.api.VexViewAPI;
import lk.vexview.tag.TagDirection;
import lk.vexview.tag.components.VexImageTag;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Vexview {
    public static Vexview tag;
    public static int Tagx;
    public static int Tagy;
    public static int Tagz;
    public static int Tagtime;
    public static void loadtag() {
        Tagx = Main.plugin.getConfig().getInt("Tagimage.x");
        Tagy = Main.plugin.getConfig().getInt("Tagimage.y");
        Tagz = Main.plugin.getConfig().getInt("Tagimage.z");
        Tagtime = Main.plugin.getConfig().getInt("Tagimage.time");

    }
    public static void tag(Player player,String s) {
        if (Main.vexViewIsExist()) {
            int time = Vexview.Tagtime * 10;
            TagDirection tag = new TagDirection(0, 0, 0, true, true);
            VexImageTag vit = new VexImageTag("id_1", Vexview.Tagx, Vexview.Tagy, Vexview.Tagz, Main.plugin.getConfig().getString("MaxDamage." + s + ".Tagurl"), 40, 60, 2, 1, tag);
            VexViewAPI.addPlayerTag(player, vit);
            Bukkit.getServer().getScheduler().runTaskLater((Plugin) Main.plugin, new BukkitRunnable() {
                public void run() {
                    VexViewAPI.removePlayerTag(player, "id_1");
                }
            }, time);
        }
    }
    public Vexview getTag(){
        loadtag();
        return Vexview.tag;
    }
}
