package edu.note.thread.thread;

import java.util.ArrayList;

/**
 * @description: 把 list 变成局部变量
 * @author: Jacky Lee
 * @date: 2024/4/1 16:02
 */
class ListThreadSafe {

    public final void batch(int loopNumber) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            listAdd(list);
            listRemove(list);
        }
    }

    public void listAdd(ArrayList<String> list) {
        list.add("1");
    }

    // 方法是 private 可以限制子类Override，从而达到线程安全
    private void listRemove(ArrayList<String> list) {
        list.remove(0);
    }
}
