package edu.note.java.uuid;

import java.util.UUID;

/**
 * @author jackylee
 * @date 2025-10-19 13:48
 */
public class UuidDemo {
    public static void main(String[] args) {
        String str = UUID.randomUUID().toString().replace("-", "");
        System.out.println(str);// 9f15b8c356c54f55bfcb0ee3023fce8a
    }

}
