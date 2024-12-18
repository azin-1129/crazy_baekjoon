package Code;

import java.util.*;
import java.io.*;

class Node1240{
    int next;
    int weight;

    Node1240(int next, int weight){
        this.next=next;
        this.weight=weight;
    }
}

class BOJ_1240{
    static LinkedList<List<Node1240>> graph;
    static int N;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1240;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        graph=new LinkedList<>();

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N=inputs[0]; // 노드의 개수

        for(int i=0;i<=N;i++){
            graph.add(new LinkedList<Node1240>());
        }

        int M=inputs[1]; // 거리를 알고싶은 노드 쌍 개수

        for(int i=0;i<N-1;i++){
            inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph.get(inputs[0]).add(new Node1240(inputs[1], inputs[2]));
            graph.get(inputs[1]).add(new Node1240(inputs[0], inputs[2]));
        }

        for(int i=0;i<M;i++){
            inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            searchNode(inputs[0], inputs[1], 0, -1);
        }

        br.close();
    }

    public static void searchNode(int from, int to, int weight, int parent){
        if(from==to){
            System.out.println(weight);
            return;
        }

        for(Node1240 nextNode: graph.get(to)){
            if(nextNode.next!=parent){
                searchNode(from, nextNode.next, weight+nextNode.weight, to);
            }
        }
    }
}