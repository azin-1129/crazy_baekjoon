package Code;

import java.util.*;
import java.io.*;

class Main{
    static int[][] graph;
    static int[] parent;
    static int finalCost;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());
        int M=Integer.parseInt(br.readLine());

        graph=new int[M][3]; // M+1로 하면 0,0,0 때문에 정렬 잘못 됨 ..
        parent=new int[N+1];
        for(int i=1;i<=N;i++){
            parent[i]=i;
        }
        
        finalCost=0;

        for(int i=0;i<M;i++){
            int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[i][0]=inputs[0];
            graph[i][1]=inputs[1];
            graph[i][2]=inputs[2];
        }

        // weight 순 오름차순 정렬
        Arrays.sort(graph, (o1, o2)->Integer.compare(o1[2], o2[2]));

        for(int i=0;i<M;i++){
            if(find(graph[i][0])!=find(graph[i][1])){
                union(graph[i][0], graph[i][1]);
                finalCost+=graph[i][2];
            }
        }

        System.out.println(finalCost);
        br.close();
    }
    private static void union(int a, int b){
        a=find(a);
        b=find(b);

        if(a>b){
            parent[a]=b;
        }else{
            parent[b]=a;
        }
    }

    private static int find(int x){
        if(parent[x]==x){
            return x;
        }else{
            return find(parent[x]);
        }
    }
}