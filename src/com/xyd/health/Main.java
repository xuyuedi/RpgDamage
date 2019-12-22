package com.xyd.health;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.Plugin;

public class Main extends JavaPlugin implements Listener {

    String ProGramme = this.getConfig().getString("ProGramme");
    Plugin Hd = Bukkit.getServer().getPluginManager().getPlugin("HolographicDisplays");

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getLogger().info("§aRpgHealth正在加载中...");
        this.load();
    }

    public void onDisable() {
        this.getLogger().info("§aRpgHealth成功卸载");
    }

    public void load() {
        this.saveDefaultConfig();
        this.reloadConfig();
        ProGramme = this.getConfig().getString("ProGramme");
        Hd = Bukkit.getServer().getPluginManager().getPlugin("HolographicDisplays");
        this.getLogger().info("§bRpgHealth加载完成");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("rpghealth")) {
            if (args.length == 0) {
                sender.sendMessage(("&6&l&m一一一一一&r " +
                        "&c&lRpgHealth" + " &6&l&m一一一一一\n&a&L/" + "rpghealth" + " reload " +
                        "重载配置文件" + "\n&a&L/" + "rpghealth" + " font " +
                        "查看方案字体" + "\n&6&l&m一一一一一&r " +
                        "&c&lRpgHealth" + " &6&l&m一一一一一").replace("&","§"));
                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (!sender.hasPermission("rpghealth.admin")) {
                        sender.sendMessage("§c你没有权限");
                        return true;
                    }
                    this.load();
                    sender.sendMessage("§eRpgHealth重载成功!");
                }
                if (args[0].equalsIgnoreCase("help")) {
                    if (!sender.hasPermission("rpghealth.admin")) {
                        sender.sendMessage("§c你没有权限");
                        return true;
                    }
                    sender.sendMessage(("&6&l&m一一一一一&r " +
                            "&c&lRpgHealth" + " &6&l&m一一一一一\n&a&L/" + "rpghealth" + " reload " +
                            "重载配置文件" + "\n&a&L/" + "rpghealth" + " font " +
                            "查看方案字体" + "\n&6&l&m一一一一一&r " +
                            "&c&lRpgHealth" + " &6&l&m一一一一一").replace("&","§"));
                }
                if (args[0].equalsIgnoreCase("font")) {
                    if (!sender.hasPermission("rpghealth.admin")) {
                        sender.sendMessage("§c你没有权限");
                        return true;
                    }
                    sender.sendMessage(("&6&l&m一一一一一&r " +
                            "&c&l方案字体格式" + " &6&l&m一一一一一\n&b&l方案一:&r" + "䀁 䀂 䀃 䀄 䀅 䀆 䀇 䀈 䀉 䀊" +
                            "\n&a&b&l方案二" + "鴁 鴂 鴃 鴄 鴅 鴆 鴇 鴈 鴉 鴊" +
                            "\n&a&b&l方案三" + "䀑 䀒 䀓 䀔 䀕 䀖 䀗 䀘 䀙 䀚" +
                            "\n&6&l&m一一一一一&r " +
                            "&c&lRpgHealth" + " &6&l&m一一一一一").replace("&","§"));
                    }
                }
            }
        return true;
    }


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player player = ((Player) e.getDamager()).getPlayer();
            Double damage = Double.valueOf((double) Math.round(e.getFinalDamage()));
            String Damage = String.valueOf(damage);
            long DamageInt = Math.round(damage);
            long MaxHealth = Math.round(((LivingEntity) e.getEntity()).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            long Health = Math.round(((LivingEntity) e.getEntity()).getHealth()) - DamageInt;
            String Name = e.getEntity().getCustomName();
            if (e.getEntity().getCustomName() != null) {
                Name = e.getEntity().getCustomName();
            } else {
                EntityType Type = e.getEntityType();
                if (Type == EntityType.BAT) {
                    Name = "蝙蝠";
                }
                if (Type == EntityType.ENDERMITE) {
                    Name = "末影螨";
                }
                if (Type == EntityType.BAT) {
                    Name = "蝙蝠";
                }
                if (Type == EntityType.BLAZE) {
                    Name = "烈焰人";
                }
                if (Type == EntityType.CAVE_SPIDER) {
                    Name = "洞穴蜘蛛";
                }
                if (Type == EntityType.CHICKEN) {
                    Name = "鸡";
                }
                if (Type == EntityType.COW) {
                    Name = "牛";
                }
                if (Type == EntityType.CREEPER) {
                    Name = "苦力怕";
                }
                if (Type == EntityType.ENDERMAN) {
                    Name = "末影人";
                }
                if (Type == EntityType.GHAST) {
                    Name = "恶魂";
                }
                if (Type == EntityType.GIANT) {
                    Name = "巨人";
                }
                if (Type == EntityType.IRON_GOLEM) {
                    Name = "铁傀儡";
                }
                if (Type == EntityType.HORSE) {
                    Name = "马";
                }
                if (Type == EntityType.HUSK) {
                    Name = "尸壳";
                }
                if (Type == EntityType.LLAMA) {
                    Name = "羊驼";
                }
                if (Type == EntityType.MUSHROOM_COW) {
                    Name = "哞菇";
                }
                if (Type == EntityType.OCELOT) {
                    Name = "豹猫";
                }
                if (Type == EntityType.PIG) {
                    Name = "猪";
                }
                if (Type == EntityType.PIG_ZOMBIE) {
                    Name = "僵尸猪人";
                }
                if (Type == EntityType.SHEEP) {
                    Name = "羊";
                }
                if (Type == EntityType.RABBIT) {
                    Name = "兔子";
                }
                if (Type == EntityType.SILVERFISH) {
                    Name = "蠢虫";
                }
                if (Type == EntityType.SKELETON) {
                    Name = "骷髅";
                }
                if (Type == EntityType.SLIME) {
                    Name = "史莱姆";
                }
                if (Type == EntityType.SNOWMAN) {
                    Name = "雪人";
                }
                if (Type == EntityType.SPIDER) {
                    Name = "蜘蛛";
                }
                if (Type == EntityType.SQUID) {
                    Name = "鱿鱼";
                }
                if (Type == EntityType.VILLAGER) {
                    Name = "村民";
                }
                if (Type == EntityType.WITCH) {
                    Name = "女巫";
                }
                if (Type == EntityType.WITHER) {
                    Name = "凋零";
                }
                if (Type == EntityType.WOLF) {
                    Name = "狼";
                }
                if (Type == EntityType.ZOMBIE) {
                    Name = "僵尸";
                }
                if (Type == EntityType.ZOMBIE_HORSE) {
                    Name = "僵尸马";
                }
                if (Type == EntityType.SKELETON_HORSE) {
                    Name = "骷髅马";
                }
                if (Type == EntityType.ZOMBIE_VILLAGER) {
                    Name = "僵尸村民";
                }
                if (Type == EntityType.POLAR_BEAR) {
                    Name = "北极熊";
                }
                if (Type == EntityType.PARROT) {
                    Name = "鹦鹉";
                }
                if (Type == EntityType.GUARDIAN) {
                    Name = "守卫者";
                }
                if (Type == EntityType.PLAYER) {
                    Name = e.getEntity().getName();
                }if(Health <= 0){
                    Health = 0;
                }
            }
            if (this.ProGramme.equalsIgnoreCase("方案一")) {
                if (this.getConfig().getBoolean("Message")) {
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        player.sendMessage(this.getConfig().getString("DamageMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", "䀁").replaceAll("2", "䀂").replaceAll("3", "䀃").replaceAll("4", "䀄").replaceAll("5", "䀅").replaceAll("6", "䀆").replaceAll("7", "䀇").replaceAll("8", "䀈").replaceAll("9", "䀉").replaceAll("0", "䀊"));
                    }else{
                        player.sendMessage(this.getConfig().getString("DamageMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", "䀁").replaceAll("2", "䀂").replaceAll("3", "䀃").replaceAll("4", "䀄").replaceAll("5", "䀅").replaceAll("6", "䀆").replaceAll("7", "䀇").replaceAll("8", "䀈").replaceAll("9", "䀉").replaceAll("0", "䀊"));
                    }
                }
                if (this.getConfig().getBoolean("Title")) {
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        TitleAPI.sendTitle(player, Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(5), " ", this.getConfig().getString("TitleMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", "䀁").replaceAll("2", "䀂").replaceAll("3", "䀃").replaceAll("4", "䀄").replaceAll("5", "䀅").replaceAll("6", "䀆").replaceAll("7", "䀇").replaceAll("8", "䀈").replaceAll("9", "䀉").replaceAll("0", "䀊"));
                    }else{
                        TitleAPI.sendTitle(player, Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(5), " ", this.getConfig().getString("TitleMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", "䀁").replaceAll("2", "䀂").replaceAll("3", "䀃").replaceAll("4", "䀄").replaceAll("5", "䀅").replaceAll("6", "䀆").replaceAll("7", "䀇").replaceAll("8", "䀈").replaceAll("9", "䀉").replaceAll("0", "䀊"));
                    }
                }
                if (this.getConfig().getBoolean("Action")) {
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(this.getConfig().getString("ActionMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", "䀁").replaceAll("2", "䀂").replaceAll("3", "䀃").replaceAll("4", "䀄").replaceAll("5", "䀅").replaceAll("6", "䀆").replaceAll("7", "䀇").replaceAll("8", "䀈").replaceAll("9", "䀉").replaceAll("0", "䀊")));
                    }else{
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(this.getConfig().getString("ActionMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", "䀁").replaceAll("2", "䀂").replaceAll("3", "䀃").replaceAll("4", "䀄").replaceAll("5", "䀅").replaceAll("6", "䀆").replaceAll("7", "䀇").replaceAll("8", "䀈").replaceAll("9", "䀉").replaceAll("0", "䀊")));
                    }
                }
                if (this.getConfig().getBoolean("Hologram")) {
                    final Location Loc = ((LivingEntity) e.getEntity()).getEyeLocation().clone().add(0.0, 0.6, 0.0);
                    Loc.setYaw(e.getDamager().getLocation().getYaw() + 90.0f);
                    Loc.add(Loc.getDirection().multiply(0.8));
                    final Hologram hologram = HologramsAPI.createHologram(this.Hd, Loc);
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        String s = hologram.appendTextLine(this.getConfig().getString("HologramMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", "䀁").replaceAll("2", "䀂").replaceAll("3", "䀃").replaceAll("4", "䀄").replaceAll("5", "䀅").replaceAll("6", "䀆").replaceAll("7", "䀇").replaceAll("8", "䀈").replaceAll("9", "䀉").replaceAll("0", "䀊")).toString();
                    }else{
                        String s = hologram.appendTextLine(this.getConfig().getString("HologramMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", "䀁").replaceAll("2", "䀂").replaceAll("3", "䀃").replaceAll("4", "䀄").replaceAll("5", "䀅").replaceAll("6", "䀆").replaceAll("7", "䀇").replaceAll("8", "䀈").replaceAll("9", "䀉").replaceAll("0", "䀊")).toString();
                    }
                    try {
                        (new BukkitRunnable() {
                            public void run() {
                                hologram.delete();
                            }
                        }).runTaskLater(this, 20L);
                    } catch (Exception hd) {
                    }
                }
            }
            if (this.ProGramme.equalsIgnoreCase("方案二")) {
                if (this.getConfig().getBoolean("Message")) {
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        player.sendMessage(this.getConfig().getString("DamageMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", "鴁").replaceAll("2", "鴂").replaceAll("3", "鴃").replaceAll("4", "鴄").replaceAll("5", "鴅").replaceAll("6", "鴆").replaceAll("7", "鴇").replaceAll("8", "鴈").replaceAll("9", "鴉").replaceAll("0", "鴊"));
                    } else {
                        player.sendMessage(this.getConfig().getString("DamageMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", "鴁").replaceAll("2", "鴂").replaceAll("3", "鴃").replaceAll("4", "鴄").replaceAll("5", "鴅").replaceAll("6", "鴆").replaceAll("7", "鴇").replaceAll("8", "鴈").replaceAll("9", "鴉").replaceAll("0", "鴊"));
                    }
                }
                if (this.getConfig().getBoolean("Title")) {
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        TitleAPI.sendTitle(player, Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(5), " ", this.getConfig().getString("TitleMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", "鴁").replaceAll("2", "鴂").replaceAll("3", "鴃").replaceAll("4", "鴄").replaceAll("5", "鴅").replaceAll("6", "鴆").replaceAll("7", "鴇").replaceAll("8", "鴈").replaceAll("9", "鴉").replaceAll("0", "鴊"));
                    }else{
                        TitleAPI.sendTitle(player, Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(5), " ", this.getConfig().getString("TitleMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", "鴁").replaceAll("2", "鴂").replaceAll("3", "鴃").replaceAll("4", "鴄").replaceAll("5", "鴅").replaceAll("6", "鴆").replaceAll("7", "鴇").replaceAll("8", "鴈").replaceAll("9", "鴉").replaceAll("0", "鴊"));
                    }
                }
                if (this.getConfig().getBoolean("Action")) {
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(this.getConfig().getString("ActionMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", "鴁").replaceAll("2", "鴂").replaceAll("3", "鴃").replaceAll("4", "鴄").replaceAll("5", "鴅").replaceAll("6", "鴆").replaceAll("7", "鴇").replaceAll("8", "鴈").replaceAll("9", "鴉").replaceAll("0", "鴊")));
                    }else{
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(this.getConfig().getString("ActionMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", "鴁").replaceAll("2", "鴂").replaceAll("3", "鴃").replaceAll("4", "鴄").replaceAll("5", "鴅").replaceAll("6", "鴆").replaceAll("7", "鴇").replaceAll("8", "鴈").replaceAll("9", "鴉").replaceAll("0", "鴊")));
                    }
                }
                if (this.getConfig().getBoolean("Hologram")) {
                    final Location Loc = ((LivingEntity) e.getEntity()).getEyeLocation().clone().add(0.0, 0.6, 0.0);
                    Loc.setYaw(e.getDamager().getLocation().getYaw() + 90.0f);
                    Loc.add(Loc.getDirection().multiply(0.8));
                    final Hologram hologram = HologramsAPI.createHologram(this.Hd, Loc);
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        String s = hologram.appendTextLine(this.getConfig().getString("HologramMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", "鴁").replaceAll("2", "鴂").replaceAll("3", "鴃").replaceAll("4", "鴄").replaceAll("5", "鴅").replaceAll("6", "鴆").replaceAll("7", "鴇").replaceAll("8", "鴈").replaceAll("9", "鴉").replaceAll("0", "鴊")).toString();
                    }else {
                        String s = hologram.appendTextLine(this.getConfig().getString("HologramMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", "鴁").replaceAll("2", "鴂").replaceAll("3", "鴃").replaceAll("4", "鴄").replaceAll("5", "鴅").replaceAll("6", "鴆").replaceAll("7", "鴇").replaceAll("8", "鴈").replaceAll("9", "鴉").replaceAll("0", "鴊")).toString();
                    }
                    try {
                        (new BukkitRunnable() {
                            public void run() {
                                hologram.delete();
                            }
                        }).runTaskLater(this, 20L);
                    } catch (Exception hd) {
                    }
                }
            }
            if (this.ProGramme.equalsIgnoreCase("方案三")) {
                if (this.getConfig().getBoolean("Message")) {
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        player.sendMessage(this.getConfig().getString("DamageMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", "䀑").replaceAll("2", "䀒").replaceAll("3", "䀓").replaceAll("4", "䀔").replaceAll("5", "䀕").replaceAll("6", "䀖").replaceAll("7", "䀗").replaceAll("8", "䀘").replaceAll("9", "䀙").replaceAll("0", "䀚"));
                    } else {
                        player.sendMessage(this.getConfig().getString("DamageMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", "䀑").replaceAll("2", "䀒").replaceAll("3", "䀓").replaceAll("4", "䀔").replaceAll("5", "䀕").replaceAll("6", "䀖").replaceAll("7", "䀗").replaceAll("8", "䀘").replaceAll("9", "䀙").replaceAll("0", "䀚"));
                    }
                }
                if (this.getConfig().getBoolean("Title")) {
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        TitleAPI.sendTitle(player, Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(5), " ", this.getConfig().getString("TitleMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", "䀑").replaceAll("2", "䀒").replaceAll("3", "䀓").replaceAll("4", "䀔").replaceAll("5", "䀕").replaceAll("6", "䀖").replaceAll("7", "䀗").replaceAll("8", "䀘").replaceAll("9", "䀙").replaceAll("0", "䀚"));
                    }else{
                        TitleAPI.sendTitle(player, Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(5), " ", this.getConfig().getString("TitleMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", "䀑").replaceAll("2", "䀒").replaceAll("3", "䀓").replaceAll("4", "䀔").replaceAll("5", "䀕").replaceAll("6", "䀖").replaceAll("7", "䀗").replaceAll("8", "䀘").replaceAll("9", "䀙").replaceAll("0", "䀚"));
                    }
                }
                if (this.getConfig().getBoolean("Action")) {
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(this.getConfig().getString("ActionMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", "䀑").replaceAll("2", "䀒").replaceAll("3", "䀓").replaceAll("4", "䀔").replaceAll("5", "䀕").replaceAll("6", "䀖").replaceAll("7", "䀗").replaceAll("8", "䀘").replaceAll("9", "䀙").replaceAll("0", "䀚")));
                    }else{
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(this.getConfig().getString("ActionMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", "䀑").replaceAll("2", "䀒").replaceAll("3", "䀓").replaceAll("4", "䀔").replaceAll("5", "䀕").replaceAll("6", "䀖").replaceAll("7", "䀗").replaceAll("8", "䀘").replaceAll("9", "䀙").replaceAll("0", "䀚")));
                    }
                }
                if (this.getConfig().getBoolean("Hologram")) {
                    final Location Loc = ((LivingEntity) e.getEntity()).getEyeLocation().clone().add(0.0, 0.6, 0.0);
                    Loc.setYaw(e.getDamager().getLocation().getYaw() + 90.0f);
                    Loc.add(Loc.getDirection().multiply(0.8));
                    final Hologram hologram = HologramsAPI.createHologram(this.Hd, Loc);
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        String s = hologram.appendTextLine(this.getConfig().getString("HologramMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", "䀑").replaceAll("2", "䀒").replaceAll("3", "䀓").replaceAll("4", "䀔").replaceAll("5", "䀕").replaceAll("6", "䀖").replaceAll("7", "䀗").replaceAll("8", "䀘").replaceAll("9", "䀙").replaceAll("0", "䀚")).toString();
                    }else {
                        String s = hologram.appendTextLine(this.getConfig().getString("HologramMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", "䀑").replaceAll("2", "䀒").replaceAll("3", "䀓").replaceAll("4", "䀔").replaceAll("5", "䀕").replaceAll("6", "䀖").replaceAll("7", "䀗").replaceAll("8", "䀘").replaceAll("9", "䀙").replaceAll("0", "䀚")).toString();
                    }
                    try {
                        (new BukkitRunnable() {
                            public void run() {
                                hologram.delete();
                            }
                        }).runTaskLater(this, 20L);
                    } catch (Exception hd) {
                    }
                }
            }
            if (this.ProGramme.equalsIgnoreCase("无")) {
                if (this.getConfig().getBoolean("Message")) {
                    if (this.getConfig().getBoolean("DisplayDecimal")) {

                        player.sendMessage(this.getConfig().getString("DamageMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage));
                    }else{
                        player.sendMessage(this.getConfig().getString("DamageMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt));
                    }
                }
                if (this.getConfig().getBoolean("Title")) {
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        TitleAPI.sendTitle(player, Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(5), " ", this.getConfig().getString("TitleMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage));
                    }else{
                        TitleAPI.sendTitle(player, Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(5), " ", this.getConfig().getString("TitleMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt));
                    }
                }
                if (this.getConfig().getBoolean("Action")) {
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(this.getConfig().getString("ActionMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage)));
                    }else{
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(this.getConfig().getString("ActionMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt)));
                    }
                }
                if (this.getConfig().getBoolean("Hologram")) {
                    final Location Loc = ((LivingEntity) e.getEntity()).getEyeLocation().clone().add(0.0, 0.6, 0.0);
                    Loc.setYaw(e.getDamager().getLocation().getYaw() + 90.0f);
                    Loc.add(Loc.getDirection().multiply(0.8));
                    final Hologram hologram = HologramsAPI.createHologram(this.Hd, Loc);
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        String s = hologram.appendTextLine(this.getConfig().getString("HologramMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage)).toString();
                    }else{
                        String s = hologram.appendTextLine(this.getConfig().getString("HologramMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt)).toString();
                    }
                    try {
                        (new BukkitRunnable() {
                            public void run() {
                                hologram.delete();
                            }
                        }).runTaskLater(this, 20L);
                    } catch (Exception hd) {
                    }
                }
            }
        }
    }
}