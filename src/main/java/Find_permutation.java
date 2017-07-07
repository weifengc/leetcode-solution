import java.util.Arrays;

//https://leetcode.com/problems/find-permutation/#/description
public class Find_permutation {
    public static void main(String[] args){
        Solution solution = new Solution();
        String s = "DDDI";
        int[] res = solution.findPermutation(s);
        System.out.println(Arrays.toString(res));
    }

    /**
     * This solution is from :
     * https://discuss.leetcode.com/topic/76213/greedy-o-n-java-solution-with-explanation
     */
    static class Solution {
        public int[] findPermutation(String s) {
            s = s + ".";
            int[] res = new int[s.length()];

            int i = 0;
            int min = 1;
            while (i < res.length) {
                if (s.charAt(i) != 'D') {
                    res[i++] = min++;
                } else {
                    int j = i;
                    while (s.charAt(j) == 'D') j++;
                    for (int k = j; k >= i; k--) {
                        res[k] = min++;
                    }
                    i = j + 1;
                }
            }
            return res;
        }
    }

}


