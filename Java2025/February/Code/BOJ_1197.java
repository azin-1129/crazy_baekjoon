package Code;

import java.util.*;
import java.io.*;

class Node1197{
    int next;
    int weight;

    Node1197(int next, int weight){
        this.next=next;
        this.weight=weight;
    }

    @Override
    public String toString(){
        return "next:"+this.next+", weight:"+this.weight;
    }
}
class BOJ_1197{
    static List<List<Node1197>> graph;
    static int V;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1197;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        V=inputs[0];
        int E=inputs[1];

        graph=new ArrayList<>();

        for(int i=0;i<=V;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<E;i++){
            // A to B = C
            inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int A=inputs[0];
            int B=inputs[1];
            int C=inputs[2];

            graph.get(A).add(new Node1197(B, C));
            graph.get(B).add(new Node1197(A, C));
        }

        int result=Integer.MAX_VALUE;
        for(int i=1;i<=V;i++){
            result=Math.min(prim(i), result);
        }

        System.out.println(result);
        br.close();
    }
    static int prim(int start){
        boolean[] visited=new boolean[V+1];

        PriorityQueue<Node1197> pq=new PriorityQueue<>(new Comparator<Node1197>(){
            @Override
            public int compare(Node1197 n1, Node1197 n2){
                return n1.weight-n2.weight;
            }
        });

        pq.offer(new Node1197(start, 0));

        int totalWeight=0;
        while(!pq.isEmpty()){
            Node1197 node=pq.poll();

            int current=node.next;
            int weight=node.weight;

            if(visited[current]){
                continue;
            }

            visited[current]=true;
            totalWeight+=weight;

            for(Node1197 nextNode:graph.get(current)){
                if(!visited[nextNode.next]){
                    pq.offer(nextNode);
                }
            }
        }

        return totalWeight;
    }
}