package SpyderCore.DataLogSystem;

import SpyderCore.DataLogSystem.Types.MinecraftDataLog;
import SpyderCore.PluginLoader;

import java.util.HashMap;

public class DataLoggerManager {
    private static HashMap<String,IDataLogger> hooks = new HashMap();
    public static void hangHook(String id,IDataLogger dataLogger){
        hooks.put(id,dataLogger);
    }
    public static void initHooks(){
        hooks.forEach((k,v)->{
            v.initDataLogger();
        });
    }
    public static void enableWriteFileTask(){
        PluginLoader.getInstance().getServer().getScheduler().scheduleRepeatingTask(PluginLoader.getInstance(),new QueueFileWriteTask(),5);
    }
    public static IDataLogger getDataLogger(String id){
        return hooks.get(id);
    }
    public static void initPlayerLog(String name){
        hooks.forEach((k,v)->{
            v.initPlayerData(name);
        });
    }
    public static void registHooks(){
        hangHook("遊戲行為",new MinecraftDataLog());
    }
    public static HashMap<String, IDataLogger> getHooks() {
        return hooks;
    }
}
