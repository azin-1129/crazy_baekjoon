package Code;

import java.util.*;
import java.io.*;

class BOJ_1956{
    static int INF, result=0;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1956;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        // 방법 1
        // 시작점 dfs(int current, int start, int weight)
        // 다음 노드 탐색 재귀(재귀할 때마다 visited 처리, 후 해제로 백트래킹)
        // 도착점과 start가 같다면, 길이 합의 최소를 갱신하고 return

        // 방법 2
        // 플로이드 도장깨기

        st=new StringTokenizer(br.readLine(), " ");
        int V=Integer.parseInt(st.nextToken());
        int E=Integer.parseInt(st.nextToken());
        INF=E*10000+1;
        int[][] floyd=new int[V+1][V+1];
        for(int x=1;x<=V;x++){
            Arrays.fill(floyd[x], INF);
            floyd[x][x]=0;
        }
        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());
            floyd[from][to]=weight;
        }

        for(int z=1;z<=V;z++){
            for(int x=1;x<=V;x++){
                for(int y=1;y<=V;y++){
                    floyd[x][y]=Math.min(floyd[x][z]+floyd[z][y], floyd[x][y]);
                }
            }
        }

        // for(int x=1;x<=V;x++){
        //     for(int y=1;y<=V;y++){
        //         if(floyd[x][y]==INF){
        //             System.out.print("x ");
        //         }else{
        //             System.out.print(floyd[x][y]+" ");
        //         }
        //     }
        //     System.out.println();
        // }

        int result=INF;
        for(int x=1;x<=V;x++){
            for(int y=1;y<=V;y++){
                if(x==y){
                    continue;
                }
                if(floyd[x][y]==INF || floyd[y][x]==INF){
                    continue;
                }
                int length=floyd[x][y]+floyd[y][x];
                result=Math.min(result, length);
            }
        }

        if(result==INF){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }

        br.close();
    }
}