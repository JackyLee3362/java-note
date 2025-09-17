package edu.note.spock;

/**
 * @author jackylee
 * @date 2025/7/1 15:28
 */
public class NumberUtil {


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
        if (NumberUtil.isOdd(num)) {
            return "Odd";
        } else {
            return "Even";
        }
    }
}
