package Code;

import java.util.*;
import java.io.*;

class BOJ_1941{
    static int result=0;
    static int BOARD_SIZE=5;
    static int MAX_DEPTH=7;
    static Character[][] map;
    static boolean[][] visited;
    static int[] dx={-1, 1, 0, 0};
    static int[] dy={0, 0, -1, 1};
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1941;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        map=new Character[BOARD_SIZE][BOARD_SIZE];
        visited=new boolean[BOARD_SIZE][BOARD_SIZE];
        for(int i=0;i<BOARD_SIZE;i++){
            String inputs=br.readLine();
            for(int j=0;j<BOARD_SIZE;j++){
                map[i][j]=inputs.charAt(j);
            }
        }

        for(int x=0;x<BOARD_SIZE;x++){
            for(int y=0;y<BOARD_SIZE;y++){
                visited[x][y]=true;
                dfs(x, y, 0, 0);
                visited[x][y]=false;
            }
        }

        System.out.println(result);
        br.close();
    }
    static void dfs(int x, int y, int depth, int dasomCount){
        System.out.println("("+x+","+y+") depth:"+depth+", dasomCount:"+dasomCount);
        if(depth==7){
            if(dasomCount>=4){
                result+=1;
            }
            return;
        }
        
        for(int d=0;d<4;d++){
            int nx=x+dx[d];
            int ny=y+dy[d];
            if(nx<0 || ny<0 || nx>=BOARD_SIZE || ny>=BOARD_SIZE){
                continue;
            }
            
            if(visited[nx][ny]){
                continue;
            }
        
            visited[nx][ny]=true;
            if(map[nx][ny]=='S'){
                dfs(nx, ny, depth+1, dasomCount+1);
            }else{
                dfs(nx, ny, depth+1, dasomCount);
            }	
            visited[nx][ny]=false;
        }
    }
}