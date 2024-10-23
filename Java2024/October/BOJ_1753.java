import java.util.*;
import java.io.*;

public class BOJ_1753 {
    static class Node implements Comparable<Node>{
        int node;
        int value;
        Node(int node, int value){
            this.node=node;
            this.value=value;
        }

        @Override
        public int compareTo(Node o){
            if(this.value>o.value){
                return 1;
            }else if(this.value==o.value){
                return 0;
            }else{
                return -1;
            }
        }
        
    }

    static int V;
    static List<List<Node>> graph=new ArrayList<>();
    public static void main(String[] args) throws Exception {
        String path=System.getProperty("user.dir");
        BufferedReader br=new BufferedReader(new FileReader(path+"\\input1753.txt"));
    
        String[] inputs=br.readLine().split(" ");
        V=Integer.parseInt(inputs[0]);
        int E=Integer.parseInt(inputs[1]);

        int K=Integer.parseInt(br.readLine());

        for(int v=0;v<=V;v++){
            graph.add(new ArrayList<>());
        }

        for(int e=0;e<E;e++){
            inputs=br.readLine().split(" ");

            int u=Integer.parseInt(inputs[0]);
            int v=Integer.parseInt(inputs[1]);
            int w=Integer.parseInt(inputs[2]);

            graph.get(u).add(new Node(v,w));
        }

        dijkstra(K);
    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq=new PriorityQueue<>();
        int[] dist=new int[V+1]; // 무한 초기화 필요

        for(int v=0;v<=V;v++){
            dist[v]=Integer.MAX_VALUE;
        }

        dist[start]=0;

        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node curNode=pq.poll();

            if(dist[curNode.node]<curNode.value){
                continue;
            }

            for(Node nextNode:graph.get(curNode.node)){
                if(dist[nextNode.node]>dist[curNode.node]+nextNode.value){
                    dist[nextNode.node]=dist[curNode.node]+nextNode.value;

                    pq.add(new Node(nextNode.node, dist[curNode.node]+nextNode.value));
                }
            }
        }

        for(int v=1;v<=V;v++){
            if(dist[v]!=Integer.MAX_VALUE){
                System.out.println(dist[v]);
            }else{
                System.out.println("INF");
            }
            
        }

    }
}
