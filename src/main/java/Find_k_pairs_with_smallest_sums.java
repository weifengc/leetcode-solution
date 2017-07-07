import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/find-k-pairs-with-smallest-sums/#/description
public class Find_k_pairs_with_smallest_sums {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {2, 5, 6};
        List<int[]> list = solution.kSmallestPairs(nums1, nums2, 5);
        for(int[] arr : list){
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * This is not the best solution. This is a O( Mlog(M)) solution, M = nums1.length * nums2.length;
     */
    static class Solution {
        public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            List<int[]> list = new ArrayList<>();
            if (nums1 == null || nums2 == null) return list;
            int M = nums1.length;
            int N = nums2.length;
            if (M == 0 || N == 0) return list;

            for (int i : nums1) {
                for (int j : nums2) {
                    list.add(pair(i, j));
                }
            }

            list.sort((a1, a2) -> a1[0] + a1[1] - a2[0] - a2[1]);
            if (list.size() > k) {
                list = list.subList(0, k);
            }

            return list;
        }

        int[] pair(int i, int j) {
            int[] arr = {i, j};
            return arr;
        }
    }
}


