package edu.note.java.util.concurrent;

import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileSaver {
    private String filename;

    private boolean changed;
    

    public FileSaver(String filename) {
        this.filename = filename;
    }

    public void save(ConcurrentHashMap<String, String> info) {
        log.debug("need save {}? {}", info, changed ? "yes" : "no");
        if (!changed) {
            return;
        }
        // 这里原先使用 jackson 中的 ObjectMapper 写入，主要用来测试 ConcurrentHashMap
        // try {
        //     mapper.writeValue(new File(filename), info);
        // } catch (IOException e) {
        //     log.error(e.getMessage());
        // }
        changed = false;
    }

    public void change() {
        this.changed = true;
    }
}
