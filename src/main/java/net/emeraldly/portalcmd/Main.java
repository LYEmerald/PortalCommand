package net.emeraldly.portalcmd;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerTeleportEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.util.List;

public class Main extends PluginBase implements Listener {
    @Override
    public void onEnable(){
        this.getLogger().info("§aPortalCommand Plugin Enabled!");
        this.getServer().getPluginManager().registerEvents(this,this);
        this.getDataFolder().mkdirs();
        this.saveDefaultConfig();
        Config config = this.getConfig();
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        String level = event.getPlayer().getLevel().getName();
        List<String> list = this.getConfig().getStringList("allow_worlds");
        if(list.contains(level)){
            if(event.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL){
                event.setCancelled(true);
                for(String command : this.getConfig().getStringList("commands")){
                    player.getServer().dispatchCommand(event.getPlayer(),command);
                }
            }
        }
    }
}
