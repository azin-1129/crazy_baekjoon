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
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, weight));
        }

        // 각 도시 -> 면접장 다익스트라 해서,
        // 면접장 최단 거리가 얼마인지 알아내기

        // 면접장 도시 번호
        resultIdx=new int[K];
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<K;i++){
            resultIdx[i]=Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=N;i++){
            dijkstra(i);
        }

        System.out.println(longestCity);
        System.out.println(longestWeight);
        br.close();
    }
    static void dijkstra(int start){
        long[] shortWeight=new long[N+1];
        Arrays.fill(shortWeight, Long.MAX_VALUE);
        PriorityQueue<Node> pq=new PriorityQueue<>();
        pq.offer(new Node(start, 0));
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

        // 최단거리 : start로 부터 가장 가까운 면접장 거리
        long shortestWeight=Long.MAX_VALUE;
        for(int resIdx : resultIdx){
            long dist=shortWeight[resIdx];
            if(dist<shortestWeight){
                shortestWeight=dist;
            }
        }

        if(longestWeight<shortestWeight){
            longestWeight=shortestWeight;
            longestCity=start;
        }
    }
}