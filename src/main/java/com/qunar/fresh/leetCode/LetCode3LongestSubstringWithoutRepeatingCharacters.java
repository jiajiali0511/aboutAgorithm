package com.qunar.fresh.leetCode;

/**
 * create by lijiajia on 2017/12/16
 */
public class LetCode3LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aaaa"));
        System.out.println(lengthOfLongestSubstringUseHashTable("aaab"));
    }

    public static int lengthOfLongestSubstring(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        if (str.length() == 1) {
            return 1;
        }
        int index = 0;
        int max = 0;
        for (int i = 1; i < str.length(); i++) {
            for (int j = i - 1; j >= index; j--) {
                if (str.charAt(i) == str.charAt(j)) {
                    index++;
                    break;
                } else {
                    max  = max < i - j + 1 ? i - j + 1 : max;
                }
            }
        }
        if (index == str.length() - 1) {
            return 1;
        }
        return max;
    }

    public static int lengthOfLongestSubstringUseHashTable(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        if (str.length() == 1) {
            return 1;
        }
        int start = 0;
        int max = 0;
        int[] table = new int[255];
        for (int i = 0 ; i < 255; i++) {
            table[i] = -1;
        }
        for (int i = 0; i < str.length(); i++) {
            if (table[i] != -1) {
                while (start < table[str.charAt(i)]) {
                    table[str.charAt(start++)] = -1;
                }
            }
            max = max < i - start + 1 ? i - start + 1 : max;
            table[str.charAt(i)] = i;
        }
        return max;
    }
}
