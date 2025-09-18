package edu.note.thread.thread;

import java.util.ArrayList;

/**
 * @author jackylee
 * @date 2025/9/18 10:34
 */
class ListThreadSafeSubClass extends ListThreadSafe {

    public void listRemove(ArrayList<String> list) {
        new Thread(() -> {
            list.remove(0);
        }).start();
    }
}
