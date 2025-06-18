package Code;

import java.util.*;
import java.io.*;

class BOJ_9466{
    // static ArrayList<Integer> NodesInDfs;
    static int start;
    static boolean[] enabled;
    static boolean[] visited;
    static int[] graph;
    static int result;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=9466;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int t=0;t<T;t++){
            result=0;
            int N=Integer.parseInt(br.readLine());
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            graph=new int[N+1];
            enabled=new boolean[N+1];
            for(int i=1;i<=N;i++){
                graph[i]=Integer.parseInt(st.nextToken());
                if(i==graph[i]){
                    enabled[i]=true;
                    result+=1;
                }
            }

            visited=new boolean[N+1];
            for(int i=1;i<=N;i++){
                if(!enabled[i]){
                    start=i;
                    dfs(graph[i]);
                }
                // System.out.println("DFS가 끝났습니다.");
                // System.out.println();
            }

            sb.append((N-result)+"\n");

            // System.out.println("팀을 결성한 노드 이력입니다.");
            // System.out.println(Arrays.toString(enabled));
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void dfs(int current){
        if(visited[current]){ // 사이클 발생
            enabled[current]=true;
            result+=1;
        }else{
            visited[current]=true;
        }

        if(!enabled[graph[current]]){
            dfs(graph[current]);
        }

        visited[current]=false;
        enabled[current]=true;
    }
}