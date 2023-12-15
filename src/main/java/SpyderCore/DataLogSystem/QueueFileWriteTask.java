package SpyderCore.DataLogSystem;


import SpyderCore.Tool.Pair;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class QueueFileWriteTask implements Runnable {
    @Override
    public void run() {
        if(!QueueFileWriter.getQueue().isEmpty()){
            Pair<String, File> current = QueueFileWriter.getQueue().poll();
            try {
                FileWriter writer = new FileWriter(current.getSecond(),true);
                BufferedWriter bwriter = new BufferedWriter(writer);
                bwriter.write(current.getFirst());
                bwriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
