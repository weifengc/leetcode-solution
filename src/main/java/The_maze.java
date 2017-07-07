import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/the-maze/#/description
public class The_maze {
    public static void main(String[] args){


    }

    /**
     * Idea
     * The destination should be both stoppable and reachable.
     * A point is stoppable if it has a path from one side and a wall on the other side.
     * Reachable can be checked using a dfs search.
     */
    static class Solution {
        int[][] maze;
        int[] start, dest;
        boolean[][] vMatrix;


        public boolean hasPath(int[][] maze, int[] start, int[] dest) {
            this.maze  = maze;
            this.start = start;
            this.dest = dest;

            if(maze == null || maze.length == 0 || maze[0].length == 0) return false;
            if(!inMaze(start) || !inMaze(dest)) return false;
            if(isWall(start) || isWall(dest)) return false;
            vMatrix = new boolean[maze.length][maze[0].length];

            if(!stopable(dest)) return false;
            return reachable();
        }


        //whether dest is reachable from start
        boolean reachable(){
            dfs(start);
            return visited(dest);
        }

        void dfs(int[] pos){
            if(visited(dest)) return;
            if(visited(pos)) return;
            mark(pos);
            for(int[] next : adj(pos)){
                dfs(next);
            }
        }

        Set<int[]> adj(int[] pos){
            //go to four direction until hit a wall
            //to up, i --
            int i = pos[0];
            int j = pos[1];
            while(i >= 0 && !isWall(i, j)) i --;
            //stop at wall
            int[] pos1 = {i + 1, j};

            //to down i++
            i = pos[0];
            j = pos[1];
            while(i < maze.length && !isWall(i, j)) i++;
            int[] pos2 = {i - 1, j};

            //to left, j --
            i = pos[0];
            j = pos[1];
            while (j >=0 && !isWall(i, j)) j--;
            int[] pos3 = {i, j+ 1};

            //to right, j ++
            i = pos[0];
            j = pos[1];
            while (j < maze[0].length && !isWall(i, j)) j++;
            int[] pos4 = {i, j-1};

            int[][] all = {pos1, pos2, pos3, pos4};
            HashSet<int[]> set = new HashSet<>();
            for(int[] p : all){
                if(!same(p, pos)) set.add(p);
            }
            return set;
        }

        boolean same(int[] pos1, int[] pos2){
            return pos1[0] == pos2[0] && pos1[1] == pos2[1];
        }

        boolean visited(int[] pos){
            return vMatrix[pos[0]][pos[1]];
        }

        void mark(int[] pos){
            vMatrix[pos[0]][pos[1]] = true;
        }


        //it must be able to stop there.
        boolean stopable(int[] pos){
            if(isWall(pos)) return false;
            int i = pos[0];
            int j = pos[1];
            boolean b1, b2, b3, b4;
            b1 = isWall(i, j - 1);
            b2 = isWall(i , j+1);
            b3 = isWall(i + 1, j);
            b4 = isWall(i - 1, j);
            return (b1 ^ b2) || (b3 ^ b4);
        }

        boolean isWall(int i, int j){
            if(i < 0 || j < 0 || i >= maze.length || j >= maze[0].length) return true;
            return maze[i][j] == 1;
        }

        boolean isWall(int[] pos){
            return isWall(pos[0], pos[1]);
        }

        boolean inMaze(int[] pos){
            int i = pos[0];
            int j = pos[1];
            return i >=0 && i < maze.length && j >=0 && j < maze[0].length;
        }


    }
}
