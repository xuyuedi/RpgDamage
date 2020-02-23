package com.xyd.health;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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

import java.util.List;

public class Main extends JavaPlugin implements Listener {

    String ProGramme = this.getConfig().getString("ProGramme");
    Plugin Hd = Bukkit.getServer().getPluginManager().getPlugin("HolographicDisplays");
    String 一 = this.getConfig().getString("Material.1");
    String 二 = this.getConfig().getString("Material.2");
    String 三 = this.getConfig().getString("Material.3");
    String 四 = this.getConfig().getString("Material.4");
    String 五 = this.getConfig().getString("Material.5");
    String 六 = this.getConfig().getString("Material.6");
    String 七 = this.getConfig().getString("Material.7");
    String 八 = this.getConfig().getString("Material.8");
    String 九 = this.getConfig().getString("Material.9");
    String 零 = this.getConfig().getString("Material.0");
    public static Main plugin;


    public void onEnable() {
        plugin=this;
        Metrics metrics = new Metrics(this,	6571);
        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getConsoleSender().sendMessage("======== §eRpgDamage开始启动 §r========");
        Bukkit.getConsoleSender().sendMessage("§e版本:2.0 作者:玄月/安生");
        Bukkit.getConsoleSender().sendMessage("§f功能:");
        Bukkit.getConsoleSender().sendMessage("§f范围伤害提示√");
        Bukkit.getConsoleSender().sendMessage("§fVexview.tag显示√");
        Bukkit.getConsoleSender().sendMessage("§f自定义材质√");
        if(Bukkit.getPluginManager().getPlugin("VexView") != null){
            Bukkit.getConsoleSender().sendMessage("§fVexview兼容成功");
            Bukkit.getConsoleSender().sendMessage("======== §eRpgDamage启动完成 §r========");
            Vexview.loadtag();
            Mobname.loadname();
            load();
        }else {
            Bukkit.getConsoleSender().sendMessage("§fVexview兼容失败");
            Bukkit.getConsoleSender().sendMessage("======== §eRpgDamage启动完成 §r========");
            Mobname.loadname();
            this.load();
        }

    }
    public static boolean vexViewIsExist(){
        return Bukkit.getPluginManager().getPlugin("VexView") != null;
    }
    public void onDisable() {
        this.getLogger().info("§aRpgDamage成功卸载");
    }

    public void load() {
        this.saveDefaultConfig();
        this.reloadConfig();
        ProGramme = this.getConfig().getString("ProGramme");
        Hd = Bukkit.getServer().getPluginManager().getPlugin("HolographicDisplays");
        一 = this.getConfig().getString("Material.1");
        二 = this.getConfig().getString("Material.2");
        三 = this.getConfig().getString("Material.3");
        四 = this.getConfig().getString("Material.4");
        五 = this.getConfig().getString("Material.5");
        六 = this.getConfig().getString("Material.6");
        七 = this.getConfig().getString("Material.7");
        八 = this.getConfig().getString("Material.8");
        九 = this.getConfig().getString("Material.9");
        零 = this.getConfig().getString("Material.0");
        Mobname.loadname();
        if(Bukkit.getPluginManager().getPlugin("VexView")!= null){
            Vexview.loadtag();
        }
            Mobname.loadname();
            this.getLogger().info("§bRpgDamage加载完成");
        }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("rpgdamage")) {
            if (args.length == 0) {
                List<String> messages = getConfig().getStringList("help");
                for (String str : messages)
                    sender.sendMessage(str.replace("&","§"));
                    return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (!sender.hasPermission("rpgdamage.admin")) {
                        sender.sendMessage("§c你没有权限");
                        return true;
                    }
                    this.load();
                    sender.sendMessage("§eRpgDamage重载成功!");
                }
                if (args[0].equalsIgnoreCase("help")) {
                    if (!sender.hasPermission("rpgdamage.admin")) {
                        sender.sendMessage("§c你没有权限");
                        return true;
                    }
                    List<String> messages = getConfig().getStringList("help");
                    for (String str : messages)
                        sender.sendMessage(str.replace("&","§"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("font")) {
                    if (!sender.hasPermission("rpgdamage.admin")) {
                        sender.sendMessage("§c你没有权限");
                        return true;
                    }
                    sender.sendMessage(("&6&l&m一一一一一&r " +
                            "&c&l方案字体格式" + " &6&l&m一一一一一\n&b&l方案:&r" + 一+二+三+四+五+六+七+八+九+零 + "\n&6&l&m一一一一一&r " +
                            "&c&l方案字体格式" + " &6&l&m一一一一一 ").replace("&","§"));
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
                if (Type == EntityType.ENDERMITE) {
                    Name = Mobname.ENDERMITE;
                }
                if (Type == EntityType.BAT) {
                    Name = Mobname.BAT;
                }
                if (Type == EntityType.PHANTOM) {
                    Name = Mobname.PHANTOM;
                }
                if (Type == EntityType.BLAZE) {
                    Name = Mobname.BLAZE;
                }
                if (Type == EntityType.CAVE_SPIDER) {
                    Name = Mobname.CAVE_SPIDER;
                }
                if (Type == EntityType.CHICKEN) {
                    Name = Mobname.CHICKEN;
                }
                if (Type == EntityType.COW) {
                    Name = Mobname.COW;
                }
                if (Type == EntityType.CREEPER) {
                    Name = Mobname.CREEPER;
                }
                if (Type == EntityType.ENDERMAN) {
                    Name = Mobname.ENDERMAN;
                }
                if (Type == EntityType.GHAST) {
                    Name = Mobname.GHAST;
                }
                if (Type == EntityType.GIANT) {
                    Name = Mobname.GIANT;
                }
                if (Type == EntityType.IRON_GOLEM) {
                    Name = Mobname.IRON_GOLEM;
                }
                if (Type == EntityType.HORSE) {
                    Name = Mobname.HORSE;
                }
                if (Type == EntityType.HUSK) {
                    Name = Mobname.HUSK;
                }
                if (Type == EntityType.LLAMA) {
                    Name = Mobname.LLAMA;
                }
                if (Type == EntityType.MUSHROOM_COW) {
                    Name = Mobname.MUSHROOM_COW;
                }
                if (Type == EntityType.OCELOT) {
                    Name = Mobname.OCELOT;
                }
                if (Type == EntityType.PIG) {
                    Name = Mobname.PIG;
                }
                if (Type == EntityType.PIG_ZOMBIE) {
                    Name = Mobname.PIG_ZOMBIE;
                }
                if (Type == EntityType.SHEEP) {
                    Name = Mobname.SHEEP;
                }
                if (Type == EntityType.RABBIT) {
                    Name = Mobname.RABBIT;
                }
                if (Type == EntityType.SILVERFISH) {
                    Name = Mobname.SILVERFISH;
                }
                if (Type == EntityType.SKELETON) {
                    Name = Mobname.SKELETON;
                }
                if (Type == EntityType.SLIME) {
                    Name = Mobname.SLIME;
                }
                if (Type == EntityType.SNOWMAN) {
                    Name = Mobname.SNOWMAN;
                }
                if (Type == EntityType.SPIDER) {
                    Name = Mobname.SPIDER;
                }
                if (Type == EntityType.SQUID) {
                    Name = Mobname.SQUID;
                }
                if (Type == EntityType.VILLAGER) {
                    Name = Mobname.VILLAGER;
                }
                if (Type == EntityType.WITCH) {
                    Name = Mobname.WITCH;
                }
                if (Type == EntityType.WITHER) {
                    Name = Mobname.WITHER;
                }
                if (Type == EntityType.WOLF) {
                    Name = Mobname.WOLF;
                }
                if (Type == EntityType.ZOMBIE) {
                    Name = Mobname.ZOMBIE;
                }
                if (Type == EntityType.ZOMBIE_HORSE) {
                    Name = Mobname.ZOMBIE_HORSE;
                }
                if (Type == EntityType.SKELETON_HORSE) {
                    Name = Mobname.SKELETON_HORSE;
                }
                if (Type == EntityType.ZOMBIE_VILLAGER) {
                    Name = Mobname.ZOMBIE_VILLAGER;
                }
                if (Type == EntityType.POLAR_BEAR) {
                    Name = Mobname.POLAR_BEAR;
                }
                if (Type == EntityType.PARROT) {
                    Name = Mobname.PARROT;
                }
                if (Type == EntityType.GUARDIAN) {
                    Name = Mobname.GUARDIAN;
                }
                if (Type == EntityType.PLAYER) {
                    Name = e.getEntity().getName();
                }
                if (Health <= 0) {
                    Health = 0;
                }
            }
            if (!this.getConfig().getBoolean("MaxMessage.enable")) {
                return;
            } else {
                String bigest = null;
                if (this.getConfig().getBoolean("MaxMessage.Message")) {
                    for (String key : getConfig().getConfigurationSection("MaxDamage").getKeys(false)) {
                        if(((bigest == null) || (Integer.parseInt(bigest) < Integer.parseInt(key))) && ((damage > Integer.parseInt(key)) && (damage < getConfig().getInt("MaxDamage."+key+ ".Max")))){
                            bigest = key;
                        }
                    }
                    if (!(bigest ==null)) {
                        player.sendMessage(getConfig().getString("MaxDamage." + bigest + ".Message").replace("&", "§"));
                    }
                }
                if (this.getConfig().getBoolean("MaxMessage.Title")) {
                    for (String key : getConfig().getConfigurationSection("MaxDamage").getKeys(false)) {
                        if(((bigest == null) || (Integer.parseInt(bigest) < Integer.parseInt(key))) && ((damage > Integer.parseInt(key)) && (damage < getConfig().getInt("MaxDamage."+key+ ".Max")))){
                            bigest = key;
                        }
                    }
                    if (!(bigest ==null)) {
                        TitleAPI.sendTitle(player, Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(5), "", this.getConfig().getString("MaxDamage." + bigest + ".Title").replace("&", "§"));
                    }
                }
                if (this.getConfig().getBoolean("MaxMessage.Sound")) {
                    for (String key : getConfig().getConfigurationSection("MaxDamage").getKeys(false)) {
                        if (((bigest == null) || (Integer.parseInt(bigest) < Integer.parseInt(key))) && ((damage > Integer.parseInt(key)) && (damage < getConfig().getInt("MaxDamage." + key + ".Max")))) {
                            bigest = key;
                        }
                    }
                    if (!(bigest == null)) {
                        player.playSound(player.getLocation(), Sound.valueOf(getConfig().getString("MaxDamage." + bigest + ".Sound")), 2, 2);
                    }
                }
                if (this.getConfig().getBoolean("Message")) {
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        player.sendMessage(this.getConfig().getString("DamageMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", 一).replaceAll("2", 二).replaceAll("3", 三).replaceAll("4", 四).replaceAll("5", 五).replaceAll("6", 六).replaceAll("7", 七).replaceAll("8", 八).replaceAll("9", 九).replaceAll("0", 零));
                    } else {
                        player.sendMessage(this.getConfig().getString("DamageMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", 一).replaceAll("2", 二).replaceAll("3", 三).replaceAll("4", 四).replaceAll("5", 五).replaceAll("6", 六).replaceAll("7", 七).replaceAll("8", 八).replaceAll("9", 九).replaceAll("0", 零));
                    }
                }
                if (this.getConfig().getBoolean("Title")) {
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        TitleAPI.sendTitle(player, Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(5), " ", this.getConfig().getString("TitleMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", 一).replaceAll("2", 二).replaceAll("3", 三).replaceAll("4", 四).replaceAll("5", 五).replaceAll("6", 六).replaceAll("7", 七).replaceAll("8", 八).replaceAll("9", 九).replaceAll("0", 零));
                    } else {
                        TitleAPI.sendTitle(player, Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(5), " ", this.getConfig().getString("TitleMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", 一).replaceAll("2", 二).replaceAll("3", 三).replaceAll("4", 四).replaceAll("5", 五).replaceAll("6", 六).replaceAll("7", 七).replaceAll("8", 八).replaceAll("9", 九).replaceAll("0", 零));
                    }
                }
                if (this.getConfig().getBoolean("Action")) {
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(this.getConfig().getString("ActionMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", 一).replaceAll("2", 二).replaceAll("3", 三).replaceAll("4", 四).replaceAll("5", 五).replaceAll("6", 六).replaceAll("7", 七).replaceAll("8", 八).replaceAll("9", 九).replaceAll("0", 零)));
                    } else {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(this.getConfig().getString("ActionMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", 一).replaceAll("2", 二).replaceAll("3", 三).replaceAll("4", 四).replaceAll("5", 五).replaceAll("6", 六).replaceAll("7", 七).replaceAll("8", 八).replaceAll("9", 九).replaceAll("0", 零)));
                    }
                }
                if (this.getConfig().getBoolean("Hologram")) {
                    final Location Loc = ((LivingEntity) e.getEntity()).getEyeLocation().clone().add(0.0, 0.6, 0.0);
                    Loc.setYaw(e.getDamager().getLocation().getYaw() + 90.0f);
                    Loc.add(Loc.getDirection().multiply(0.8));
                    final Hologram hologram = HologramsAPI.createHologram(this.Hd, Loc);
                    if (this.getConfig().getBoolean("DisplayDecimal")) {
                        String s = hologram.appendTextLine(this.getConfig().getString("HologramMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + Damage).replaceAll("1", 一).replaceAll("2", 二).replaceAll("3", 三).replaceAll("4", 四).replaceAll("5", 五).replaceAll("6", 六).replaceAll("7", 七).replaceAll("8", 八).replaceAll("9", 九).replaceAll("0", 零)).toString();
                    } else {
                        String s = hologram.appendTextLine(this.getConfig().getString("HologramMessage").replace("&", "§").replace("{health}", "" + Health).replace("{maxhealth}", "" + MaxHealth).replace("{name}", "" + Name).replace("{damage}", "" + DamageInt).replaceAll("1", 一).replaceAll("2", 二).replaceAll("3", 三).replaceAll("4", 四).replaceAll("5", 五).replaceAll("6", 六).replaceAll("7", 七).replaceAll("8", 八).replaceAll("9", 九).replaceAll("0", 零)).toString();
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
                if (this.getConfig().getBoolean("Sound")) {
                    player.playSound(player.getLocation(),Sound.valueOf(getConfig().getString("SoundSend")),2,2);
                }
                if (this.getConfig().getBoolean("Tagimage.enable")) {
                    for (String key : getConfig().getConfigurationSection("MaxDamage").getKeys(false)) {
                        if (((bigest == null) || (Integer.parseInt(bigest) < Integer.parseInt(key))) && ((damage > Integer.parseInt(key)) && (damage < getConfig().getInt("MaxDamage." + key + ".Max")))) {
                            bigest = key;
                        }
                    }
                    if (!(bigest == null)) {
                        Vexview.tag(player, bigest);
                    }
                }
            }
            }
        }
    }