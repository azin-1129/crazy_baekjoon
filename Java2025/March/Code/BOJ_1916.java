package Code;

import java.util.*;
import java.io.*;

class BOJ_1916{
    static class Node1916 implements Comparable<Node1916>{
        int next;
        int weight;

        public Node1916(int next, int weight){
            this.next=next;
            this.weight=weight;
        }

        @Override
        public int compareTo(Node1916 node){
            return Integer.compare(this.weight, node.weight);
        }
    }
    
    static int N,A,B;
    static List<List<Node1916>> graph;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1916;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        int M=Integer.parseInt(br.readLine());

        graph=new ArrayList<>();
        for(int i=0;i<=N;i++){ // 버스 번호는 1부터
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());

            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node1916(to, weight));
        }

        st=new StringTokenizer(br.readLine());
        A=Integer.parseInt(st.nextToken());
        B=Integer.parseInt(st.nextToken());
        System.out.println(dijkstra(A));

        br.close();
    }
    static int dijkstra(int start){
        boolean[] visited=new boolean[N+1];
        int[] dist=new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start]=0;

        PriorityQueue<Node1916> pq=new PriorityQueue<>();
        pq.offer(new Node1916(start, 0));
        while(!pq.isEmpty()){
            Node1916 currentNode=pq.poll();

            if(visited[currentNode.next]){
                continue;
            }

            for(Node1916 nextNode:graph.get(currentNode.next)){
                if(visited[nextNode.next]){
                    continue;
                }

                visited[currentNode.next]=true;

                if((currentNode.weight+nextNode.weight)<dist[nextNode.next]){
                    dist[nextNode.next]=dist[currentNode.next]+nextNode.weight;
                    pq.offer(new Node1916(nextNode.next, dist[nextNode.next]));
                }
            }
        }

        return dist[B];
    }
}