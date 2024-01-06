package SpyderCore.Listener;

import SpyderCore.DataLogSystem.DataLoggerManager;
import SpyderCore.DataLogSystem.Types.MinecraftDataLog;
import SpyderCore.PluginLoader;
import SpyderCore.Tool.Utils;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.inventory.InventoryPickupItemEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.player.PlayerLocallyInitializedEvent;
import cn.nukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    private MinecraftDataLog dataLog = (MinecraftDataLog) DataLoggerManager.getDataLogger("遊戲行為");
    @EventHandler(priority = EventPriority.LOWEST)
    public void initPlayer(PlayerLocallyInitializedEvent event){
        Player player = event.getPlayer();
        DataLoggerManager.initPlayerLog(player.getUniqueId().toString());
    }
    @EventHandler
    public void loadPlayer(PlayerLocallyInitializedEvent event){
        Player player = event.getPlayer();
        dataLog.addPlayerData(player.getName(), PluginLoader.getLocalTime()+"登入了伺服器");
    }
    @EventHandler
    public void savePlayer(PlayerQuitEvent event){
        Player player = event.getPlayer();
        dataLog.addPlayerData(player.getName(), PluginLoader.getLocalTime()+"離開了伺服器");
    }
    @EventHandler
    public void onPickUpItem(InventoryPickupItemEvent event){
        dataLog.addPlayerData(event.getViewers()[0].getName(), "撿起了物品 ("+ Utils.itemToString(event.getItem().getItem())+")");
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        dataLog.addPlayerData(player.getName(), "執行了指令：" + event.getMessage());
    }
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        dataLog.addPlayerData(player.getName(), PluginLoader.getLocalTime()+"破壞了"+event.getBlock().getName()+"在位置"+event.getBlock().getLocation().toString());
    }
}
