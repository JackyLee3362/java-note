package edu.note.java.exception;

import org.junit.jupiter.api.Test;

public class TryCatchTest {

    @Test
    void test() {

        int[] arr = { 1, 2, 3, 4, 5, 6 };
        try {
            // 此处出现了异常，程序就会在这里创建一个ArrayIndexOutOfBoundsException对象
            arr[10] = 1;
        } catch (ArrayIndexOutOfBoundsException e) {
        }

    }
}
