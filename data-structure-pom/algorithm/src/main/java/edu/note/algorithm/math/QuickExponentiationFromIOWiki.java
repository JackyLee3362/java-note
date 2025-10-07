package edu.note.algorithm.math;

/**
 * https://oi-wiki.org/math/binary-exponentiation/
 * 
 * @description: 计算 a^n MOD m
 * @author: Jacky Lee
 * @date: 2024/3/16 22:19
 */
public class QuickExponentiationFromIOWiki {
    public static final long MOD = 1_000_000_007L;

    public static long calculateWithMod(long base, long power, long m) {
        long res = 1;
        while (power > 0) {
            if ((power & 1) == 1) {
                res = (res * base) % m;
            }
            base = (base * base) % m;
            power >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(calculateWithMod(2, 1000, MOD));
    }

    /**
     * leetcode 函数 - myPow
     * https://leetcode.cn/problems/powx-n/
     * 
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        double res = 1.0;
        return res;
    }

}
