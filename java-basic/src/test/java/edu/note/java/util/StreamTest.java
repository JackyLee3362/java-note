package edu.note.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import edu.note.java.model.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jackylee
 * @date 2025/7/3 19:17
 */
public class StreamTest {

    @Test
    public void testStreamToList() {
        List<String> list = Arrays.asList("1", "2", "3", "4", "5");
        // List<Integer> collect = list.stream().map(Integer::parseInt).collect(Collectors.toList());
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, list.stream().map(Integer::parseInt).toArray());
    }

    @Test
    public void testStreamToMap() {
        List<User> users = new ArrayList<>();
        users.add(new User("Alice", 12));
        users.add(new User("Bob", 13));
        users.add(new User("Charlie", 14));
        users.add(new User("David", 15));
        users.add(new User("Alice", 16));
        Map<String, Integer> map = users.stream().collect(
            Collectors.toMap(
                User::getName,
                User::getAge,
                (oldValue, newValue) -> newValue)
        );
        System.out.println(map);
    }
}
