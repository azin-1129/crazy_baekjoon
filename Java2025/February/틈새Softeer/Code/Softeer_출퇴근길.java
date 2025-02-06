package Code;

import java.io.*;
import java.util.*;

public class Softeer_출퇴근길 {
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"/Input/";
        String probName="출퇴근길";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N=inputs[0];
        int M=inputs[1];

        boolean[] visitedForward=new boolean[N+1];
        boolean[] visitedReverse=new boolean[N+1];
        boolean[] visitedForwardR=new boolean[N+1];
        boolean[] visitedReverseR=new boolean[N+1];

        List<List<Integer>> graph=new ArrayList<>();
        List<List<Integer>> graphR=new ArrayList<>();

        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
            graphR.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph.get(inputs[0]).add(inputs[1]);
            graphR.get(inputs[1]).add(inputs[0]);
        }

        inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int S=inputs[0];
        int T=inputs[1];

        // 출근길
        visitedForward[T]=true; // 목적지 도착 시 움직이지 않는다.
        // visitedForward: S에서 도달 가능한 노드
        // visitedReverse: T까지 도달 가능한 노드
        dfs(S, graph, visitedForward);
        dfs(T, graphR, visitedReverse);

        // 퇴근길
        visitedForwardR[S]=true; // 목적지 도착 시 움직이지 않는다.
        // x->S, x->T 경우의 수 고려(DFS 처리 상 고려되지 못했던 중간 노드 x 처리)
        // visitedForwardR: T에서 도달 가능한 노드
        // visitedReverseR: S까지 도달 가능한 노드
        dfs(T, graph, visitedForwardR);
        dfs(S, graphR, visitedReverseR);

        int count=0;

        for(int i=1;i<=N;i++){
            if(i==S || i==T){
                continue;
            }

            if(visitedForward[i] & visitedReverse[i] & visitedForwardR[i] & visitedReverseR[i]){
                count+=1;
            }
        }

        System.out.println(count);

        br.close();
    }
    static void dfs(int start, List<List<Integer>> graph, boolean[] visited){
        if(visited[start]){
            return;
        }
        visited[start]=true;

        for(int next:graph.get(start)){
            dfs(next, graph, visited);
        }
    }
}
