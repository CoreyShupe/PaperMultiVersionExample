package io.github.coreyshupe.multiversion;

import io.github.coreyshupe.multiversion.common.NmsHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        NmsHandler nmsHandler = VersionSupport.getCurrentVersionSupport(this.getLogger());

        this.getServer().getPluginManager().registerEvents(new ExamplePluginListener(nmsHandler), this);
    }

    @SuppressWarnings("ClassCanBeRecord")
    public static class ExamplePluginListener implements Listener {
        private final NmsHandler nmsHandler;

        public ExamplePluginListener(NmsHandler nmsHandler) {
            this.nmsHandler = nmsHandler;
        }

        @EventHandler
        public void onJoin(PlayerJoinEvent event) {
            event.getPlayer().sendMessage(nmsHandler.getMessage(event.getPlayer()));
        }
    }
}
