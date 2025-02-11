package Code;

import java.util.*;
import java.io.*;

class BOJ_11437{
    static List<List<Integer>> graph;
    static int[] depthInfo;
    static int[][] parent;
    static int H;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=11437;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());

        graph=new ArrayList<>();
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        depthInfo=new int[N+1];
        // 트리 높이 계산
        H=(int)Math.ceil(Math.log(N)/Math.log(2))+1;
        parent=new int[N+1][H];
        for(int i=0;i<(N-1);i++){
            int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph.get(inputs[0]).add(inputs[1]);
            graph.get(inputs[1]).add(inputs[0]);
        }

        init(1, 0, -1);

        // parent 연산
        for(int k=1;k<H;k++){
            for(int x=1;x<=N;x++){
                parent[x][k]=parent[parent[x][k-1]][k-1];
            }
        }

        // LCA
        StringBuilder sb=new StringBuilder();
        int M=Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            sb.append(LCA(inputs[0], inputs[1])+"\n");
        }

        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb);
        br.close();
    }
    static void init(int currentNode, int depth, int parentNode){
        depthInfo[currentNode]=depth;

        for(int next:graph.get(currentNode)){
            if(next!=parentNode){
                init(next, depth+1, currentNode);
                parent[next][0]=currentNode; // 2**0 (1)번째 parent Node initialization
            }
        }
    }
    
    static int LCA(int node1, int node2){
        if(depthInfo[node1]>depthInfo[node2]){ // node2가 깊도록 세팅
            int temp=node1;
            node1=node2;
            node2=temp;
        }

        for(int i=(H-1);i>=0;i--){ // H-1부터 수행해서 이진 점프 횟수 최소화
            if((depthInfo[node2]-depthInfo[node1])>=Math.pow(2, i)){ // node2가 node1보다 최소 2**i만큼 더 깊다면, node2를 2**i단계 위로 점프
                node2=parent[node2][i];
            }
        }

        if(node1==node2){
            return node1;
        }

        for(int i=(H-1);i>=0;i--){
            if(parent[node1][i]!=parent[node2][i]){
                node1=parent[node1][i];
                node2=parent[node2][i];
            }
        }

        return parent[node2][0];
    }
}