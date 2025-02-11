package Code;

import java.util.*;
import java.io.*;

class BOJ_11725{
    static int[] parent;
    static List<List<Integer>> graph;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=11725;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());

        parent=new int[N+1];
        graph=new ArrayList<>();

        for(int n=0;n<=N;n++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<(N-1);i++){
            int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph.get(inputs[0]).add(inputs[1]);
            graph.get(inputs[1]).add(inputs[0]);
        }

        // makeTree
        makeTree(1, -1);

        StringBuilder sb=new StringBuilder();

        for(int i=2;i<=N;i++){
            sb.append(parent[i]+"\n");
        }

        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb);
        br.close();
    }
    static void makeTree(int currentNode, int parentNode){
        for(int node:graph.get(currentNode)){
            if(node!=parentNode){
                parent[node]=currentNode;
                makeTree(node, currentNode);
            }
        }
    }
}