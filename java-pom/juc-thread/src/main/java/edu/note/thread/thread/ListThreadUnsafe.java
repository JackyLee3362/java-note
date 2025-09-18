package edu.note.thread.thread;

import java.util.ArrayList;

/**
 * @description: list 是全局变量
 * @author: Jacky Lee
 * @date: 2024/4/1 16:03
 */
class ListThreadUnsafe {

    ArrayList<String> list = new ArrayList<>();

    public void batch(int loopNumber) {
        for (int i = 0; i < loopNumber; i++) {
            listAdd();
            listRemove();
        }
    }

    private void listAdd() {
        list.add("1");
    }

    private void listRemove() {
        list.remove(0);
    }
}
