package me.mortaldev.skutils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    static Main instance;
    static String label = "SkUtils";

    @Override
    public void onEnable() {

        instance = this;

        // DATA FOLDER

        if (!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

//         DEPENDENCIES

        if (Bukkit.getPluginManager().getPlugin("Skript") == null){
            getLogger().warning("Could not find Skript! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // CONFIGS
//        WildConfig.loadConfig(true);
//        MainConfig.loadConfig(true);


        // Events
//        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
//        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);


        // COMMANDS

//        new CafeBaseCommand();
//        new WildCommand();
//        new SpawnCommand();

        // SKRIPT REGISTER

        Register.expressions();

        getLogger().info(label + " Enabled");

    }

    @Override
    public void onDisable() {
        getLogger().info(label + " Disabled");
    }

    public static Main getInstance() {
        return instance;
    }

    public static String getLabel() {
        return label;
    }
}