package com.lsktp.Lsktp;

import java.util.Objects;
import java.util.function.Consumer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Lsktp extends JavaPlugin implements Listener, CommandExecutor {



    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        if (getCommand("lsktp") != null) getCommand("lsktp").setExecutor(this);
            saveDefaultConfig();
            getLogger().info("Lsktp 已启用");
            if (getConfig().getBoolean("enable_permission", false)) {
                    getLogger().info(String.valueOf("Lsktp-已开启权限检查,请用权限管理插件添加\"lsktp.command.tp\"至需要传送的玩家"));
            
            }

    }

    @Override
    public void onDisable() {
            getLogger().info("Lsktp 已禁用");

    }


    private boolean __mp_isNumber(String str) {
        return str != null && str.matches("-?\\d+(\\.\\d+)?");

    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("lsktp")) return __cmd_lsktp(sender, cmd, label, args);
        return false;
    }

    private boolean __cmd_lsktp(CommandSender sender, Command cmd, String label, String[] args) {
            if ((sender instanceof org.bukkit.command.ConsoleCommandSender)) {
                    getLogger().warning("请使用客户端执行/lsktp <x> <y> <z>");
                    return true;
            
            } else {
                    if ((((sender instanceof org.bukkit.entity.Player ? (org.bukkit.entity.Player) sender : null) != null && (sender instanceof org.bukkit.entity.Player ? (org.bukkit.entity.Player) sender : null).hasPermission("lsktp.command.tp"))) || (!(getConfig().getBoolean("enable_permission", false)))) {
                            if (Objects.equals(String.valueOf(args.length), "3")) {
                                    if ((__mp_isNumber((args.length > (int)(0) ? args[(int)(0)] : ""))) && ((__mp_isNumber((args.length > (int)(1) ? args[(int)(1)] : ""))) && (__mp_isNumber((args.length > (int)(2) ? args[(int)(2)] : ""))))) {
                                            if ((sender instanceof org.bukkit.entity.Player ? (org.bukkit.entity.Player) sender : null) != null) { Entity __entity = (Entity) (sender instanceof org.bukkit.entity.Player ? (org.bukkit.entity.Player) sender : null); __entity.teleport(new Location(__entity.getWorld(), __num(__num((args.length > (int)(0) ? args[(int)(0)] : ""))), __num(__num((args.length > (int)(1) ? args[(int)(1)] : ""))), __num(__num((args.length > (int)(2) ? args[(int)(2)] : ""))), __entity.getLocation().getYaw(), __entity.getLocation().getPitch())); }
                                            if ((sender instanceof org.bukkit.entity.Player ? (org.bukkit.entity.Player) sender : null) != null) { (sender instanceof org.bukkit.entity.Player ? (org.bukkit.entity.Player) sender : null).sendMessage("传送成功"); }
                                    
                                    } else {
                                            if ((sender instanceof org.bukkit.entity.Player ? (org.bukkit.entity.Player) sender : null) != null) { (sender instanceof org.bukkit.entity.Player ? (org.bukkit.entity.Player) sender : null).sendMessage("输入参数错误,请检查输入的参数是否为数字"); }
                                    
                                    }
                            
                            } else {
                                    if ((sender instanceof org.bukkit.entity.Player ? (org.bukkit.entity.Player) sender : null) != null) { (sender instanceof org.bukkit.entity.Player ? (org.bukkit.entity.Player) sender : null).sendMessage("输入参数错误,请检查是否完整的输入了3个数字参数"); }
                            
                            }
                    
                    } else {
                            if ((sender instanceof org.bukkit.entity.Player ? (org.bukkit.entity.Player) sender : null) != null) { (sender instanceof org.bukkit.entity.Player ? (org.bukkit.entity.Player) sender : null).sendMessage(String.valueOf((ChatColor.RED + String.valueOf("你没有权限使用/lsktp,请联系服务器管理员为你添加\"lsktp.command.tp\"权限")))); }
                    
                    }
            
            }

        return true;
    }


    private static double __num(Object o) {
        if (o == null) return 0.0;
        if (o instanceof Number) return ((Number) o).doubleValue();
        if (o instanceof Boolean) return ((Boolean) o) ? 1.0 : 0.0;
        try { return Double.parseDouble(String.valueOf(o)); } catch (Exception e) { return 0.0; }
    }

}
