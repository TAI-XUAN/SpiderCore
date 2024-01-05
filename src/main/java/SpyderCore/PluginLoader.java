package SpyderCore;

import SpyderCore.DataLogSystem.DataLoggerManager;
import SpyderCore.Listener.PlayerListener;
import cn.nukkit.plugin.PluginBase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PluginLoader extends PluginBase {
    private static PluginLoader instance;
    @Override
    public void onLoad() {
        instance = this;
        super.onLoad();
    }

    @Override
    public void onEnable() {
        DataLoggerManager.registHooks();
        DataLoggerManager.initHooks();
        DataLoggerManager.enableWriteFileTask();
        this.getServer().getPluginManager().registerEvents(new PlayerListener(),this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static PluginLoader getInstance() {
        return instance;
    }
    public static String getLocalTime(){
        // 獲取當前的日期和時間
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 設定日期時間的顯示格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 將日期時間格式化為指定格式
        return currentDateTime.format(formatter);
    }
}
