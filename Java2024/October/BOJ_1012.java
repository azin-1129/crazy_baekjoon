import java.util.*;
import java.util.stream.*;
import java.io.*;

public class BOJ_1012{
    static int M,N;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> result=new ArrayList<>();
    public static void main(String[] args) throws Exception {
        String path=System.getProperty("user.dir");
        BufferedReader br=new BufferedReader(new FileReader(path+"\\input1012.txt"));

        int T=Integer.parseInt(br.readLine());

        int K; // 배추 좌표 수

        for(int t=1;t<=T;t++){
            String[] inputs=br.readLine().split(" ");

            M=Integer.parseInt(inputs[0]); // 가로
            N=Integer.parseInt(inputs[1]); // 세로
            K=Integer.parseInt(inputs[2]); // x,y 갯수

            int baechuCount=0;

            map=new int[M][N];
            visited=new boolean[M][N];

            // 배추 위치 입력
            for(int k=0;k<K;k++){
                inputs=br.readLine().split(" ");

                int x=Integer.parseInt(inputs[0]);
                int y=Integer.parseInt(inputs[1]);

                map[x][y]=1;
            }

            for(int x=0;x<M;x++){
                for(int y=0;y<N;y++){
                    if(map[x][y]==1 & !visited[x][y]){
                        baechuCount+=1;
                        BFS(x,y);
                    }
                }
            }

            result.add(baechuCount);
        }

        System.out.println(
            result.toString().replaceAll("[\\[\\]]","")
            .replace(", ","\n")
        );

        br.close();
    }

    static void BFS(int start, int end){
        Queue<int[]> q=new LinkedList<>(); //ArrayDeque, LinkedList 차이?

        int[] dx={-1,1,0,0};
        int[] dy={0,0,-1,1};

        q.add(new int[]{start, end});

        while(!q.isEmpty()){
            int[] next=q.poll();
            int nextX=next[0];
            int nextY=next[1];

            if(visited[nextX][nextY]){
                continue;
            }

            visited[nextX][nextY]=true;

            for(int d=0;d<4;d++){
                int nx=nextX+dx[d];
                int ny=nextY+dy[d];

                if(nx<0 || nx>=M || ny<0 || ny>=N){
                    continue;
                }

                if(map[nx][ny]!=1){
                    continue;
                }

                q.add(new int[]{nx, ny});
            }
        }
    }
}