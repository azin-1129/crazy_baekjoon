package Code;

import java.util.*;
import java.io.*;

class BOJ_24445{
    static List<PriorityQueue<Integer>> graph;
    static int[] visited;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=24445;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        graph=new ArrayList<>();

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N=inputs[0];
        int M=inputs[1];
        int R=inputs[2];

        for(int i=0;i<=N;i++){
            graph.add(new PriorityQueue<Integer>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2){
                    return o2-o1;
                }
            }));
        }

        visited=new int[N+1];

        for(int i=0;i<M;i++){
            inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph.get(inputs[0]).offer(inputs[1]);
            graph.get(inputs[1]).offer(inputs[0]);
        }

        bfs(R);

        StringBuilder sb=new StringBuilder();

        for(int i=1;i<=N;i++){
            sb.append(visited[i]+"\n");
        }

        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb);

        br.close();
    }
    public static void bfs(int start){
        Queue<Integer> q=new ArrayDeque<>();

        int index=1;

        q.offer(start);
        visited[start]=index;

        index+=1;

        while(!q.isEmpty()){
            int current=q.poll();

            PriorityQueue<Integer> next=graph.get(current);

            while(!next.isEmpty()){
                int n=next.poll();

                if(visited[n]!=0){
                    continue;
                }

                q.offer(n);
    
                visited[n]=index;
                index+=1;
            }
        }
    }
}