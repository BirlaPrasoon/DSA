package leetcode.problems.google.hard.dp.much_asked.word_break_on_steroids;

import java.util.HashMap;

public class StikersToSpellWord {
    /*
    We are given n different types of stickers. Each sticker has a lowercase English word on it.

    You would like to spell out the given string target by cutting individual letters from your collection of stickers and rearranging them. You can use each sticker more than once if you want, and you have infinite quantities of each sticker.

    Return the minimum number of stickers that you need to spell out target. If the task is impossible, return -1.

    Note: In all test cases, all words were chosen randomly from the 1000 most common US English words, and target was chosen as a concatenation of two random words.

    Example 1:
    Input: stickers = ["with","example","science"], target = "thehat"
    Output: 3
    Explanation:
    We can use 2 "with" stickers, and 1 "example" sticker.
    After cutting and rearrange the letters of those stickers, we can form the target "thehat".
    Also, this is the minimum number of stickers necessary to form the target string.

    Example 2:
    Input: stickers = ["notice","possible"], target = "basicbasic"
    Output: -1
    Explanation:
    We cannot form the target "basicbasic" from cutting letters from the given stickers.


    Constraints:
    n == stickers.length
    1 <= n <= 50
    1 <= stickers[i].length <= 10
    1 <= target.length <= 15
    stickers[i] and target consist of lowercase English letters.
    */

    // O(2^T∗S∗T)
    // O(2^T)
    class SolutionDP {
        public int minStickers(String[] stickers, String target) {
            int N = target.length();
            int MAX = 1 << N;
            int[] dp = new int[MAX];
            for (int i = 1; i < MAX; i++) dp[i] = -1;

            for (int state = 0; state < MAX; state++) {
                if (dp[state] == -1) continue;
                for (String sticker : stickers) {
                    int now = state;
                    for (char letter : sticker.toCharArray()) {
                        for (int i = 0; i < N; i++) {
                            if (((now >> i) & 1) == 1) continue;
                            if (target.charAt(i) == letter) {
                                now |= 1 << i;
                                break;
                            }
                        }
                    }
                    if (dp[now] == -1 || dp[now] > dp[state] + 1) {
                        dp[now] = dp[state] + 1;
                    }
                }
            }
            return dp[MAX - 1];
        }
    }

    public class SolutionRecursiveDP {
        public int minStickers(String[] stickers, String target) {
            // Step 1: Calculate the frequency of each letter in each sticker
            int n = stickers.length;
            int[][] stickerCounts = new int[n][26];
            for (int i = 0; i < n; i++) {
                for (char c : stickers[i].toCharArray()) {
                    stickerCounts[i][c - 'a']++;
                }
            }

            // Step 2: Memoization map to store minimum stickers required for each target state
            HashMap<String, Integer> memo = new HashMap<>();
            memo.put("", 0); // Base case: no stickers needed for an empty target

            // Step 3: Call the helper function with the target
            int result = dfs(target, stickerCounts, memo);
            return result == Integer.MAX_VALUE ? -1 : result;
        }

        private int dfs(String target, int[][] stickerCounts, HashMap<String, Integer> memo) {
            // If already computed, return the result from memo
            if (memo.containsKey(target)) {
                return memo.get(target);
            }

            int[] targetCount = new int[26];
            for (char c : target.toCharArray()) {
                targetCount[c - 'a']++;
            }

            int minStickers = Integer.MAX_VALUE;

            // Try to use each sticker
            for (int[] sticker : stickerCounts) {
                // Skip this sticker if it doesn't contain the first letter of the target
                if (sticker[target.charAt(0) - 'a'] == 0) continue;

                // Create a new target after using this sticker
                StringBuilder newTarget = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    if (targetCount[i] > sticker[i]) {
                        for (int j = 0; j < targetCount[i] - sticker[i]; j++) {
                            newTarget.append((char) ('a' + i));
                        }
                    }
                }

                // Recursively solve for the new target and add one sticker to the result
                int result = dfs(newTarget.toString(), stickerCounts, memo);
                if (result != Integer.MAX_VALUE) {
                    minStickers = Math.min(minStickers, 1 + result);
                }
            }

            // Store the result in memo to avoid recomputation
            memo.put(target, minStickers);
            return minStickers;
        }
    }


}
