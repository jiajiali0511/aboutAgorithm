package com.qunar.fresh.leetCode;


/**
 * create by lijiajia on 2017/12/18
 */
public class LetCode5LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(getLongestPalindromicString(null));
        System.out.println(getPalindromicStrUsingDynamicPlanningf("ab"));
    }

    public static String getLongestPalindromicString(String str) {
        String result = "";
        if (str == null || str.length() == 0) {
            return result;
        }
        int maxLen = 0;
        int strLen = str.length();
        for (int i = 0; i < 2 * strLen - 1; i++) {
            int left = i / 2;
            int right = i / 2;
            if (i % 2 == 1) {
                right++;
            }
            String tempStr = leftAndRightMove(str, left, right, strLen);
            if (tempStr.length() > maxLen) {
                maxLen = tempStr.length();
                result = tempStr;
            }
        }
        return result;
    }

    private static String leftAndRightMove(String str, int left, int right, int strLen) {
        while (left >= 0 && right < strLen && str.charAt(left) == str.charAt(right)) {
            --left;
            ++right;
        }
        ++left;
        return str.substring(left, right);
    }

    public static String getPalindromicStrUsingDynamicPlanningf(String str) {
        String result = "";
        if (str == null || str.length() == 0) {
            return result;
        }

        boolean[][] plan = new boolean[str.length()][str.length()];
        int maxLen = 0;
        
        for (int i = str.length() - 1; i >= 0; i--) {
            for (int j = i; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j) && (j - i < 2 || plan[i + 1][j - 1])) {
                    plan[i][j] = true;
                    if (maxLen < j - i + 1) {
                        maxLen = j - i + 1;
                        result = str.substring(i, j + 1);
                    }
                }
            }
        }
        return result;
    }
}
