package edu.note.spock;

/**
 * @author jackylee
 * @date 2025/7/1 15:28
 */
public class Calculator {

    public Integer add(Integer n1, Integer n2) {
        if (n1 == null || n2 == null) {
            throw new IllegalArgumentException("不能为 null");
        }
        return n1 + n2;
    }


    public static boolean isOdd(Integer num) {
        if (num == null) {
            throw new BaseException(300, "num must not be null");
        }
        if (num < 0) {
            throw new BaseException(400, "num must be positive");
        }
        return num % 2 == 1;
    }

    public static boolean isEven(Integer num) {
        return !isOdd(num);
    }

    public static String getNumberStr(Integer num) {
        if (Calculator.isOdd(num)) {
            return "Odd";
        } else {
            return "Even";
        }
    }
}
