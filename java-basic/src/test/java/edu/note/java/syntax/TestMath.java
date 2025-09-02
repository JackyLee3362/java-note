package edu.note.java.syntax;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestMath {
    // @Test
    // @DisplayName("")
    // void test() {
    public static void main(String[] args) {
        long divisor = 10_0000;
        long rem = 1;
        long hit = 0;
        long total = (long) Integer.MAX_VALUE - (long) Integer.MIN_VALUE + 1;
        for (long i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE; i++) {
            if (i % divisor > rem) {
                continue;
            }
            if (i % 100_0000 == 0) {
                System.out.println(i);
            }
            hit++;
        }
        double rate = (double) hit / total;
        System.out.println(rate);
    }
}
