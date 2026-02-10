package com.bfhl.bfhl.util;

import java.util.ArrayList;
import java.util.List;

public class MathUtil {

    public static List<Integer> getFibonacci(int n) {
        List<Integer> series = new ArrayList<>();
        if (n < 0)
            return series;
        int a = 0, b = 1;
        for (int i = 0; i <= n; i++) {
            series.add(a);
            int next = a + b;
            a = b;
            b = next;
        }
        return series;
    }

    public static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static List<Integer> filterPrimes(List<Integer> numbers) {
        if (numbers == null)
            return new ArrayList<>();
        return numbers.stream().filter(n -> n != null && MathUtil.isPrime(n)).toList();
    }

    public static long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static long findGCD(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty())
            return 0;
        long result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            result = gcd(result, numbers.get(i));
        }
        return result;
    }

    public static long lcm(long a, long b) {
        if (a == 0 || b == 0)
            return 0;
        return Math.abs(a * b) / gcd(a, b);
    }

    public static long findLCM(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty())
            return 0;
        long result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            result = lcm(result, numbers.get(i));
        }
        return result;
    }
}
