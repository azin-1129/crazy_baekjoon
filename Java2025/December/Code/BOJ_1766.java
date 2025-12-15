package Code;

import java.util.*;
import java.io.*;

class BOJ_1766 {
    static PriorityQueue<Integer> pq=new PriorityQueue<Integer>(
        new Comparator<Integer>(){
            @Override
            public int compare(Integer num1, Integer num2){
                return Integer.compare(num1, num2);
            }
        }
    );
    static StringBuilder sb=new StringBuilder();
    static List<List<Integer>> graph=new ArrayList<>();
    static int[] inDegree;
    static int N;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 1766;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }
        inDegree=new int[N+1];
        int M=Integer.parseInt(st.nextToken());
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            inDegree[to]++;
        }

        for(int i=1;i<=N;i++){
            if(inDegree[i]==0){
                pq.offer(i);
            }
        }

        topologySort();
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void topologySort(){
        boolean[] visited=new boolean[N+1];
        while(!pq.isEmpty()){
            int current=pq.poll();
            if(visited[current]){
                continue;
            }
            visited[current]=true;
            sb.append(current+" ");
            for(int next : graph.get(current)){
                inDegree[next]--;
                if(inDegree[next]==0){
                    pq.offer(next);
                }
            }
        }
    }
}