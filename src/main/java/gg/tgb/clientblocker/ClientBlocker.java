package gg.tgb.clientblocker;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public final class ClientBlocker extends JavaPlugin implements PluginMessageListener {


    FileConfiguration config = getConfig();
    Logger logger = Bukkit.getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic

        config.addDefault("kick-message", "Modified clients are not allowed on this server");

        String[] defaultClients = new String[] { "vanilla" };

        config.addDefault("allowed-clients", Arrays.asList(defaultClients));
        config.options().copyDefaults(true);
        saveConfig();

        Messenger messenger = Bukkit.getMessenger();
        messenger.registerIncomingPluginChannel(this, "minecraft:brand", this);

        logger.info("The following clients will be allowed to connect: " + config.getStringList("allowed-clients"));

    }

    @Override
    public void onPluginMessageReceived(String channel, Player p, byte[] msg) {

        String client = new String(msg, StandardCharsets.UTF_8).substring(1);

        List<String> allowClients = config.getStringList("allowed-clients");

        if(!ClientParser.clientAllowed(allowClients, client)) {
            p.kickPlayer("Modified clients are not allowed on this server");
            logger.info("Kicked " + p.getName() + " for using " + client + " client!");
        }
    }
}
