package Code;

import java.util.*;
import java.io.*;

class BOJ_15681{
    static int[] size;
    static List<List<Integer>> graph;
    static List<List<Integer>> parentInfo;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=15681;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        
        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N=inputs[0];
        int R=inputs[1];
        int Q=inputs[2];

        size=new int[N+1];

        graph=new ArrayList<>();
        for(int n=0;n<=N;n++){
            graph.add(new ArrayList<>());
        }

        parentInfo=new ArrayList<>();
        for(int n=0;n<=N;n++){
            parentInfo.add(new ArrayList<>());
        }

        for(int n=0;n<(N-1);n++){
            inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph.get(inputs[0]).add(inputs[1]);
            graph.get(inputs[1]).add(inputs[0]);
        }

        makeTree(R, -1);
        countSubTreeNodes(R);

        for(int q=0;q<Q;q++){
            int nodeNumber=Integer.parseInt(br.readLine());
            System.out.println(size[nodeNumber]);
        }

        br.close();
    }
    static void makeTree(int currentNode, int parent){
        for(int node:graph.get(currentNode)){
            if(node!=parent){
                // node를 currentNode의 자식으로
                parentInfo.get(currentNode).add(node);
                makeTree(node, currentNode);
            }
        }
    }

    static void countSubTreeNodes(int currentNode){
        size[currentNode]=1;

        for(int node:parentInfo.get(currentNode)){
            countSubTreeNodes(node);
            size[currentNode]+=size[node];
        }
    }
}