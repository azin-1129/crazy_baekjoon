package Code;

import java.util.*;
import java.io.*;
import java.util.stream.*;

class BOJ_2210{
    static int BOARD_LENGTH=5, DFS_DEPTH=6;
    static int[] dx=new int[]{-1,1,0,0}, dy=new int[]{0,0,-1,1};
    static int[] temp=new int[DFS_DEPTH];
    static int[][] board;
    static HashSet<String> result=new HashSet<>();
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2210;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        board=new int[BOARD_LENGTH][BOARD_LENGTH];

        for(int i=0;i<BOARD_LENGTH;i++){
            board[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int x=0;x<BOARD_LENGTH;x++){
            for(int y=0;y<BOARD_LENGTH;y++){
                dfs(x, y, 0);
            }
        }

        System.out.println(result.size());
        br.close();
    }
    public static void dfs(int x, int y, int depth){
        if(depth==DFS_DEPTH){
            StringBuilder num = new StringBuilder();
            for(int n:temp){
               num.append(n);
            }
            result.add(num.toString());
            return;
        }

        temp[depth]=board[x][y];

        for(int i=0;i<4;i++){
            int nx=x+dx[i];
            int ny=y+dy[i];

            if(nx<0 || ny<0 || nx>=BOARD_LENGTH || ny>=BOARD_LENGTH){
                continue;
            }

            dfs(nx, ny, depth+1);
        }
    }
}