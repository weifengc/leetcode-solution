

import java.util.*;

//https://leetcode.com/problems/the-maze/#/description
public class The_maze_ii {
    public static void main(String[] args) {


    }

    /**
     * I got the PriorityQueue idea from this solution.
     * https://discuss.leetcode.com/topic/77472/similar-to-the-maze-easy-understanding-java-bfs-solution/6
     *
     * My code is not as simple as the above solution.
     * Idea is to use bfs, but need to handle well about how to assign distance for some visited point.
     *
     */
    static class Solution {
        int[][] maze, dMaze;
        int[] start, dest;

        public int shortestDistance(int[][] maze, int[] start, int[] dest) {
            this.maze = maze;
            this.start = start;
            this.dest = dest;

            if (maze == null || maze.length == 0 || maze[0].length == 0) return -1;
            if (!inMaze(start) || !inMaze(dest)) return -1;
            if (isWall(start) || isWall(dest)) return -1;
            if (!stopable(dest)) return -1;
            dMaze = new int[maze.length][maze[0].length];

            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[0].length; j++) {
                    int[] p = {i, j};
                    dis(p, Integer.MAX_VALUE);
                }
            }
            dis(start, 0);
            bfs();
            int res = dis(dest);

            for(int[] arr : dMaze) System.out.println(Arrays.toString(arr));

            return (res == Integer.MAX_VALUE) ? -1 : res;
        }


        int dis(int[] pos) {
            return dMaze[pos[0]][pos[1]];
        }

        void dis(int[] pos, int val) {
            dMaze[pos[0]][pos[1]] = val;
        }

        int between(int[] p1, int[] p2) {
            if (p1[0] == p2[0]) return Math.abs(p1[1] - p2[1]);
            return Math.abs(p1[0] - p2[0]);
        }

        void bfs() {
            PriorityQueue<int[]> queue = new PriorityQueue<>(1, (i, j) -> dis(i) - dis(j));
            queue.add(start);

            while (!queue.isEmpty()){
                int[] pos = queue.poll();
                int dis = dis(pos);
                for(int[] next  : adj(pos)){
                    int dis2 = dis + between(pos, next);
                    if(dis(next) < dis2) continue;
                    dis(next, dis2);
                    queue.add(next);
                }
            }
        }


        Set<int[]> adj(int[] pos) {
            //go to four direction until hit a wall
            //to up, i --
            int i = pos[0];
            int j = pos[1];
            while (i >= 0 && !isWall(i, j)) i--;
            //stop at wall
            int[] pos1 = {i + 1, j};

            //to down i++
            i = pos[0];
            j = pos[1];
            while (i < maze.length && !isWall(i, j)) i++;
            int[] pos2 = {i - 1, j};

            //to left, j --
            i = pos[0];
            j = pos[1];
            while (j >= 0 && !isWall(i, j)) j--;
            int[] pos3 = {i, j + 1};

            //to right, j ++
            i = pos[0];
            j = pos[1];
            while (j < maze[0].length && !isWall(i, j)) j++;
            int[] pos4 = {i, j - 1};

            int[][] all = {pos1, pos2, pos3, pos4};
            HashSet<int[]> set = new HashSet<>();
            for (int[] p : all) {
                if (!same(p, pos)) set.add(p);
            }
            return set;
        }

        boolean same(int[] pos1, int[] pos2) {
            return pos1[0] == pos2[0] && pos1[1] == pos2[1];
        }


        //it must be able to stop there.
        boolean stopable(int[] pos) {
            if (isWall(pos)) return false;
            int i = pos[0];
            int j = pos[1];
            boolean b1, b2, b3, b4;
            b1 = isWall(i, j - 1);
            b2 = isWall(i, j + 1);
            b3 = isWall(i + 1, j);
            b4 = isWall(i - 1, j);
            return (b1 ^ b2) || (b3 ^ b4);
        }

        boolean isWall(int i, int j) {
            if (i < 0 || j < 0 || i >= maze.length || j >= maze[0].length) return true;
            return maze[i][j] == 1;
        }

        boolean isWall(int[] pos) {
            return isWall(pos[0], pos[1]);
        }

        boolean inMaze(int[] pos) {
            int i = pos[0];
            int j = pos[1];
            return i >= 0 && i < maze.length && j >= 0 && j < maze[0].length;
        }


    }
}
