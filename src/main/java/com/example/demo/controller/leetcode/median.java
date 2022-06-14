package com.example.demo.controller.leetcode;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping("median")
public class median {



    public List<List<Integer>> threeSum(int[] nums) {
        for (int num : nums) {
            System.out.println(num);
        }
        return null;
    }


    @GetMapping("max")
    public double findMedianSortedArrays(int nums1, int nums2) {
        int max = Math.max(nums1, nums2);
        System.out.println(max);
        return 0.0;
    }

    /**
     * 正则匹配
     *   1.动态规划
     * @return
     */
    @GetMapping("test")
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }


    public int findKthNumber(int m, int n, int k) {
        //失败
        int left = 1, right = m * n;
        while (left < right) {
            int x = left + (right - left) / 2;
            int count = x / n * n;
            for (int i = x / n + 1; i <= m; ++i) {
                count += x / i;
            }
            if (count >= k) {
                right = x;
            } else {
                left = x + 1;
            }
        }
       /* int left = 1, right = m * n;
        while (left < right) {
            int x = left + (right - left) / 2;
            int count = x / n * n;
            for (int i = x / n + 1; i <= m; ++i) {
                count += x / i;
            }
            if (count >= k) {
                right = x;
            } else {
                left = x + 1;
            }
        }*/
        return left;
    }

    /**
     * 获取最大合法括号长度
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
       /* int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        System.out.println(maxans);
        return maxans;*/
        Stack<Integer> st = new Stack<Integer>();
        int ans = 0;
        for (int i = 0, start = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') st.add(i);
            else {
                if (!st.isEmpty()) {
                    st.pop();
                    if (st.isEmpty()) ans = Math.max(ans, i - start + 1);
                    else ans = Math.max(ans, i - st.peek());
                } else start = i + 1;
            }
        }
        return ans;
    }


}
