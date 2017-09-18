import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        //use a priority queue to store the most samllest number,
        //pop out a number from pq, add its bottom and right to the pq

        this.matrix = matrix;
        int M = matrix.length;
        int N = matrix[0].length;
        if(k > M * N) return -1;

        Num n0 = new Num(0, 0);
        PriorityQueue<Num> pq = new PriorityQueue<Num>(1, (i,j) ->i.val - j.val );
        pq.add(n0);

        while(k > 1){
            Num num = pq.poll();
            for(Num n : num.next()) pq.add(n);
            k--;
        }
        return pq.poll().val;
    }

    int[][] matrix;
    int M,N;

    class Num{
        int i, j;
        int val;

        Num(int i, int j){
            this.i = i;
            this.j = j;
            this.val = matrix[i][j];
        }

        List<Num> next(){
            List<Num> list = new ArrayList();
            if(i + 1 < M) list.add(new Num(i+1, j));
            if(j + 1 < N) list.add(new Num(i, j+1));
            return list;
        }
    }
}