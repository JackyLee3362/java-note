package edu.note.socket.a10_重命名uuid;

import java.util.UUID;

public class UUIDTest {
    public static void main(String[] args) {
        String str = UUID.randomUUID().toString().replace("-", "");
        System.out.println(str);// 9f15b8c356c54f55bfcb0ee3023fce8a
    }
}
