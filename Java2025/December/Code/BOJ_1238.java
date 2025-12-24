package Code;

import java.util.*;
import java.io.*;

class BOJ_1238{
    static class Node implements Comparable<Node>{
        int idx;
        int weight;

        Node(int idx, int weight){
            this.idx=idx;
            this.weight=weight;
        }

        @Override
        public int compareTo(Node node){
            return Integer.compare(this.weight, node.weight);
        }
    }
    static List<List<Node>> graph=new ArrayList<>();
    static int[] result;
    static int N, X, INF, answer;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1238;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        // 마을 X에서 출발했을 때, 가장 멀리 있는 마을?
        // 마을 수 N, 간선 수 M, 파티가 열린 마을 X(시작점)

        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken()); // 시작점

        result=new int[N+1];
        INF=100*M+1;
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, weight));
        }

        for(int i=1;i<=N;i++){
            if(i==X){
                continue;
            }
            dijkstra(i, false);
        }
        dijkstra(X, true);

        System.out.println(answer);
        br.close();
    }
    static void dijkstra(int start, boolean isReverse){
        // 최단 거리 테이블 N+1
        int[] shortcut=new int[N+1];
        Arrays.fill(shortcut, INF);
        shortcut[start]=0; // 시작점은 비용이 0이다.

        // 우선순위 큐(최단거리 우선)
        PriorityQueue<Node> pq=new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        while(!pq.isEmpty()){
            Node current=pq.poll();
            int idx=current.idx;
            int weight=current.weight;

            if(shortcut[idx]!=weight){ // 정보 불일치, 패스
                continue;
            }

            for(Node next : graph.get(idx)){
                int nextIdx=next.idx;
                int nextWeight=next.weight;
                int newVal=weight+nextWeight;
                if(shortcut[nextIdx]>newVal){
                    shortcut[nextIdx]=newVal;
                    pq.offer(new Node(nextIdx, newVal));
                }
            }
        }

        if(isReverse){ // X to 마을: 역방향
            for(int i=1;i<=N;i++){
                if(i==X){
                    continue;
                }
                result[i]+=shortcut[i];
                if(answer<result[i]){
                    answer=result[i];
                }
            }
        }else{ // 마을 to X: 정방향
            result[start]+=shortcut[X];
        }
    }
}