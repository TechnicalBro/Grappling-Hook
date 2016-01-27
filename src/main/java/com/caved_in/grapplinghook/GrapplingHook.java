package com.caved_in.grapplinghook;

import com.caved_in.commons.plugin.BukkitPlugin;
import com.caved_in.grapplinghook.command.GrapplingHookCommand;
import com.caved_in.grapplinghook.config.PluginConfig;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;

public class GrapplingHook extends BukkitPlugin {
    private static GrapplingHook instance;

    private static String configLocation = "plugins/GrapplingHook/Config.xml";

    private static PluginConfig pluginConfig;

    public static PluginConfig getPluginConfig() {
        return pluginConfig;
    }

    public static GrapplingHook getInstance() {
        return instance;
    }

    @Override
    public void startup() {
        instance = this;
        registerListeners(new GrapplingListener());

        registerCommands(new GrapplingHookCommand());
    }

    @Override
    public void shutdown() {

    }

    @Override
    public String getAuthor() {
        return "Brandon Curtis";
    }

    @Override
    public void initConfig() {
        Serializer serializer = new Persister();
        File configFile = new File(configLocation);
        if (!configFile.exists()) {
            pluginConfig = new PluginConfig();
            try {
                serializer.write(pluginConfig, configFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            debug("Created default plugin config");
        } else {
            try {
                pluginConfig = serializer.read(PluginConfig.class, configFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            debug("Loaded config for plugin");
        }
    }
}