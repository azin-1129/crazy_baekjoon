package Code;

import java.util.*;
import java.io.*;

class BOJ_10159{
    static boolean[][] visited;
    static int N, origin;
    static int[] result;
    static int[][] adj;
    static int[] dx={-1,-1, 0, 1, 1, 1, 0, -1}, dy={0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=10159;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        int M=Integer.parseInt(br.readLine());

        result=new int[N];
        adj=new int[N][N];
        visited=new boolean[N][N];

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());

            int big=Integer.parseInt(st.nextToken());
            int small=Integer.parseInt(st.nextToken());

            adj[big-1][small-1]=1;
        }

        for(int[] arr: adj){
            System.out.println(Arrays.toString(arr));
        }

        System.out.println();

        for(int x=0;x<N;x++){
            for(int y=0;y<N;y++){
                if(adj[x][y]==1){
                    origin=x;
                    dfs(x, y, 0);
                    System.out.println("재귀 끝");
                }
            }
            visited=new boolean[N][N];
        }

        System.out.println(Arrays.toString(result));

        br.close();
    }
    public static void dfs(int x, int y, int depth){
        System.out.println("dfs:"+x+", "+y+", "+depth);
        if(depth==N){
            System.out.println("재귀 종료");
            return;
        }

        for(int i=0;i<8;i++){
            int nx=x+dx[i];
            int ny=y+dy[i];

            if(nx<0 || ny<0 || nx>=N || ny>=N){
                continue;
            }
            if(visited[nx][ny]){
                continue;
            }

            if(adj[nx][ny]==1){
                result[origin]+=1;
                System.out.println("x:"+x+", y:"+y+" 함수에서 nx:"+nx+", ny:"+ny+"로 재귀");
                visited[nx][ny]=true;
                dfs(nx, ny, depth+1);
            }else{
                continue;
            }
        }
    }
}