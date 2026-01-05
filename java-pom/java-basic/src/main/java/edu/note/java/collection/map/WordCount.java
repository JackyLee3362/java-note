package edu.note.java.collection.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025/9/26 10:14
 */
@Slf4j
public class WordCount {

    private static void demo2() {

        Map<String, Integer> collect = IntStream.range(1, 27).parallel()
            .mapToObj(WordCount::readFromFile)
            .flatMap(Collection::stream)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(w -> 1)));
        System.out.println(collect);
    }

    static <V> void demo(Supplier<Map<String, V>> supplier, BiConsumer<Map<String, V>, List<String>> consumer) {
        Map<String, V> counterMap = supplier.get();
        // key value
        // a 200
        // b 200
        List<Thread> ts = new ArrayList<>();
        for (int i = 1; i <= 26; i++) {
            int idx = i;
            Thread thread = new Thread(() -> {
                List<String> words = readFromFile(idx);
                consumer.accept(counterMap, words);
            });
            ts.add(thread);
        }

        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        });

        System.out.println(counterMap);
    }

    public static List<String> readFromFile(int i) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
            Files.newInputStream(Paths.get("tmp/" + i + ".txt"))))) {
            while (true) {
                String word = in.readLine();
                if (word == null) {
                    break;
                }
                words.add(word);
            }
            return words;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
