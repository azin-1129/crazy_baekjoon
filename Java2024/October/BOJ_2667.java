package Java2024.October;

import java.io.*;
import java.util.*;

public class BOJ_2667 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> result=new ArrayList<>();
    static int[] dx={-1,1,0,0};
    static int[] dy={0,0,-1,1};
    static int roomCount;

    public static void main(String[] args) throws Exception {
        String path=System.getProperty("user.dir")+"\\Java2024\\October\\";
        BufferedReader br=new BufferedReader(new FileReader(path+"input2667.txt"));

        N=Integer.parseInt(br.readLine());
        map=new int[N][N];
        visited=new boolean[N][N];

        for(int n=0;n<N;n++){
            map[n]=Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            // System.out.println(Arrays.toString(map[n]));
        }

        for(int x=0;x<N;x++){
            for(int y=0;y<N;y++){
                if(map[x][y]==1 & !visited[x][y]){
                    roomCount=1; // 초기화
                    DFS(x,y);

                    result.add(roomCount);
                }
            }
        }

        Collections.sort(result);

        System.out.println(result.size());

        for(int apt:result){
            System.out.println(apt);
        }
    }

    static void DFS(int x, int y){
        if(visited[x][y]){
            return;
        }

        visited[x][y]=true;

        int nx,ny;
        for(int d=0;d<4;d++){
            nx=x+dx[d];
            ny=y+dy[d];

            if(nx<0 || nx>=N || ny<0 || ny>=N){
                continue;
            }

            if(!visited[nx][ny] & map[nx][ny]==1){
                roomCount+=1;
                DFS(nx, ny);
            }
        }
    }
}
