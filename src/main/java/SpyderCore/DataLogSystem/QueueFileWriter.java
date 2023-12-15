package SpyderCore.DataLogSystem;


import SpyderCore.Tool.Pair;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class QueueFileWriter {
    private static final Queue<Pair<String,File>> queue = new LinkedList<>();

    public static Queue<Pair<String, File>> getQueue() {
        return queue;
    }

}
