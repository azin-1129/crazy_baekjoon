package Code;

import java.io.*;
import java.util.*;

public class Softeer_장애물_인식_프로그램 {
    static int[] dx={-1,1,0,0}, dy={0,0,-1,1};
    static boolean[][] visited;
    static int[][] map;
    static int N, sectorCount;
    public static void main(String[] args) throws Exception {
        String filepath=System.getProperty("user.dir")+"/Input/";
        String probName="장애물_인식_프로그램";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt"));

        N=Integer.parseInt(br.readLine());
        map=new int[N][N];
        visited=new boolean[N][N];

        for(int i=0;i<N;i++){
            map[i]=Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int dfsCount=0;
        PriorityQueue<Integer> sectorCounts=new PriorityQueue<>();
        for(int x=0;x<N;x++){
            for(int y=0;y<N;y++){
                if(map[x][y]==1 & !visited[x][y]){
                    dfs(x,y);
                    sectorCounts.offer(Integer.valueOf(sectorCount));
                    sectorCount=0;
                    dfsCount+=1;
                }
            }
        }

        System.out.println(dfsCount);
        while(!sectorCounts.isEmpty()){
            System.out.println(sectorCounts.poll());
        }

        br.close();
    }
    static void dfs(int x, int y){
        if(visited[x][y]){
            return;
        }

        visited[x][y]=true;
        sectorCount+=1;

        for(int i=0;i<4;i++){
            int nx=x+dx[i];
            int ny=y+dy[i];

            if(nx<0 || ny<0 || nx>=N || ny>=N){
                continue;
            }
            if(map[nx][ny]!=1){
                continue;
            }

            dfs(nx, ny);
        }
    }
}
