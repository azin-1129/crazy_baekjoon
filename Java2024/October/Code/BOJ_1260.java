package Code;

import java.util.*;
import java.io.*;

public class BOJ_1260 {
    static List<Integer> result=new ArrayList<>();
    static int N;
    static List<List<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        String path=System.getProperty("user.dir")+"\\Java2024\\October\\";

        BufferedReader br=new BufferedReader(new FileReader(path+"input1260.txt"));

        int M,V;

        String[] inputs=br.readLine().split(" ");

        graph=new ArrayList<>();

        N=Integer.parseInt(inputs[0]);
        M=Integer.parseInt(inputs[1]);
        V=Integer.parseInt(inputs[2]);

        // 그래프 추가
        for(int n=0;n<=N;n++){
            graph.add(new ArrayList<>());
        }

        int start=0;
        int end=0;

        // 그래프 정보 삽입
        for(int m=0;m<M;m++){
            inputs=br.readLine().split(" ");

            start=Integer.parseInt(inputs[0]);
            end=Integer.parseInt(inputs[1]);

            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        // 그래프 정보 정렬
        for(int n=0;n<=N;n++){
            Collections.sort(graph.get(n));
        }

        visited=new boolean[N+1];
        DFS(V);
        System.out.println(result.toString().replaceAll("[\\[\\],]",""));
        result=new ArrayList<>(); // 답안 초기화

        BFS(V);
        System.out.println(result.toString().replaceAll("[\\[\\],]",""));

        br.close();
    }

    static void BFS(int start){
        Queue<Integer> q=new LinkedList<>();
        boolean[] visited=new boolean[N+1];

        q.add(start);

        int nextVertex;
        while(!q.isEmpty()){
            nextVertex=q.poll();

            if(visited[nextVertex]){
                continue;
            }else{
                visited[nextVertex]=true;
                result.add(nextVertex);

                for(int relatedVertex:graph.get(nextVertex)){
                    q.add(relatedVertex);
                }
            }
        }
    }

    static void DFS(int start){
        if(visited[start]){
            return;
        }

        visited[start]=true;
        result.add(start);

        for(int relatedVertex:graph.get(start)){
            if(!visited[relatedVertex]){
                DFS(relatedVertex);
            }
        }
    }
}
