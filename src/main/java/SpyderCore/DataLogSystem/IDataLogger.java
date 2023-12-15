package SpyderCore.DataLogSystem;


import SpyderCore.PluginLoader;
import SpyderCore.Tool.Pair;

import java.io.File;
import java.io.IOException;

public abstract class IDataLogger {
    //項目名稱
    private String name;
    public IDataLogger(String name){
        this.name = name;
    }
     public void initDataLogger(){
        PluginLoader.getInstance().getLogger().info("正在讀取"+name+"類別的玩家行為紀錄檔");
         File file = new File(PluginLoader.getInstance().getDataFolder()+"/玩家行為紀錄/"+name);
         if (!file.exists()){
             PluginLoader.getInstance().getLogger().warning("未找到"+name+"類別的玩家行為紀錄檔正在進行創建....");
             file.mkdir();
             PluginLoader.getInstance().getLogger().info(name+"類別的玩家行為紀錄檔創建完成");
         }
         PluginLoader.getInstance().getLogger().info("成功讀取"+name+"類別的玩家行為紀錄檔");
     }
    public void initPlayerData(String name){
        File file = new File(PluginLoader.getInstance().getDataFolder()+"/玩家行為紀錄/"+this.name+"/"+name+".txt");
        if (!file.exists()){
            PluginLoader.getInstance().getLogger().warning("未找到"+ name+"玩家的"+this.name+"類別的行為紀錄檔正在進行創建....");
            try {
                file.createNewFile();
                PluginLoader.getInstance().getLogger().info(name+"玩家的"+this.name+"類別行為紀錄檔創建完成");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void addPlayerData(String name,String message){
        QueueFileWriter.getQueue().add(new Pair<>(message,new File(PluginLoader.getInstance().getDataFolder()+"/玩家行為紀錄/"+this.name+"/"+name+".txt")));
    }

    public String getName() {
        return name;
    }
}
