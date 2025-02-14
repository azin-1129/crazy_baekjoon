package Code;

import java.util.*;
import java.io.*;

class Node1647{
    int next;
    int weight;

    Node1647(int next, int weight){
        this.next=next;
        this.weight=weight;
    }

    @Override
    public String toString(){
        return "next:"+this.next+", weight:"+this.weight;
    }
}
class BOJ_1647{
    static List<List<Node1647>> graph;
    static int N;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1647;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N=inputs[0];
        int M=inputs[1];

        graph=new ArrayList<>();
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int A=inputs[0];
            int B=inputs[1];
            int C=inputs[2];

            graph.get(A).add(new Node1647(B, C));
            graph.get(B).add(new Node1647(A, C));
        }

        System.out.println(prim(1));
        br.close();
    }
    static int prim(int start){
        boolean[] visited=new boolean[N+1];
        PriorityQueue<Node1647> pq=new PriorityQueue<>(new Comparator<Node1647>(){
            @Override
            public int compare(Node1647 n1, Node1647 n2){
                return n1.weight-n2.weight;
            }
        });

        pq.offer(new Node1647(start, 0));

        int totalWeight=0;
        int maxWeight=0;
        while(!pq.isEmpty()){
            Node1647 node=pq.poll();
            int current=node.next;
            int weight=node.weight;

            if(visited[current]){
                continue;
            }
            visited[current]=true;
            maxWeight=Math.max(weight, maxWeight);
            totalWeight+=weight;

            for(Node1647 nextNode:graph.get(current)){
                if(!visited[nextNode.next]){
                    pq.offer(nextNode);
                }
            }
        }

        return totalWeight-maxWeight;
    }
}