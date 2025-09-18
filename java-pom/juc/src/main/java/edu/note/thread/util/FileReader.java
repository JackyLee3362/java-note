package edu.note.thread.util;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j(topic = "c.FileReader")
public class FileReader {

    public static void pseudoRead(String filename) {
        Random random = new Random();
        int cnt = 0;
        int total = 1000;
        while (cnt < total) {
            cnt += random.nextInt(100);
            int percent =  cnt * 100 / total;
            System.out.println("已读取 " + percent + "/100");
            Sleeper.sleep(0.1);
        }
        System.out.println(filename);

    }

    public static void read(String filename) {
        int idx = filename.lastIndexOf(File.separator);
        String shortName = filename.substring(idx + 1);
        try (FileInputStream in = new FileInputStream(filename)) {
            long start = System.currentTimeMillis();
            log.debug("read [{}] start ...", shortName);
            byte[] buf = new byte[1024];
            int n = -1;
            do {
                n = in.read(buf);
            } while (n != -1);
            long end = System.currentTimeMillis();
            log.debug("read [{}] end ... cost: {} ms", shortName, end - start);
        } catch (IOException e) {
            // log.error(e.getMessage());
        }
    }
}