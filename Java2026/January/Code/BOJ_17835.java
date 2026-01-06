package Code;

import java.util.*;
import java.io.*;

class BOJ_17835 {
    static class Node implements Comparable<Node>{
        int idx;
        long weight;

        Node(int idx, long weight){
            this.idx=idx;
            this.weight=weight;
        }

        @Override
        public String toString(){
            return "(idx:"+idx+", weight:"+weight+")";
        }
        @Override
        public int compareTo(Node n){
            return Long.compare(this.weight, n.weight);
        }
    }
    static List<List<Node>> graph=new ArrayList<>();
    static int[] resultIdx;
    static int N, longestCity;
    static long longestWeight;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 17835;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;

        // K개의 면접장에서 시작한다.
        // 각 도시 별로 최단 거리를 산정한다.
        // 최단 거리의 도시 정보가 갱신되는 경우,
        // 해당 도시의 번호가 더 앞선 경우에만 갱신한다.

        st=new StringTokenizer(br.readLine(), " ");
        // 도시 수 N, 도로 수 M, 면접장 수 K
        N=Integer.parseInt(st.nextToken());
        // 그래프 초기화
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }
        int M=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());
        // 도로 정보 M개. U to V = C
        // 면접장에서 출발하기 위해, edge를 역방향으로 잡는다.
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());

            graph.get(to).add(new Node(from, weight));
        }

        // 면접장 도시 번호
        resultIdx=new int[K];
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<K;i++){
            resultIdx[i]=Integer.parseInt(st.nextToken());
        }

        dijkstra();

        System.out.println(longestCity);
        System.out.println(longestWeight);
        br.close();
    }
    static void dijkstra(){
        long[] shortWeight=new long[N+1];
        Arrays.fill(shortWeight, Long.MAX_VALUE);
        PriorityQueue<Node> pq=new PriorityQueue<>();
        for(int idx:resultIdx){ // 멀티소스 다익스트라
            pq.offer(new Node(idx, 0));
        }
        while(!pq.isEmpty()){
            Node node=pq.poll();
            int idx=node.idx;
            long weight=node.weight;
            if(shortWeight[idx]<=weight){
                continue;
            }
            // System.out.println(node);
            shortWeight[idx]=weight;

            for(Node nextNode : graph.get(idx)){
                pq.offer(new Node(nextNode.idx, weight+nextNode.weight));
            }
        }

        for(int i=1;i<=N;i++){
            long weight=shortWeight[i];
            if(weight>longestWeight){
                longestCity=i;
                longestWeight=weight;
            }
        }
    }
}