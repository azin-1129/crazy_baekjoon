package Code;

import java.util.*;
import java.io.*;

class BOJ_6497{
    static int[][] graph;
    static int[] parent;
    static int N;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=6497;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;
        
        while(true){
            st=new StringTokenizer(br.readLine());
            int M=Integer.parseInt(st.nextToken()); // 노드의 수
            N=Integer.parseInt(st.nextToken()); // 간선의 수

            if(M==0 & N==0){
                break;
            }

            int maxWeight=0;
    
            graph=new int[N][3];
            parent=new int[M+1];
    
            for(int i=1;i<=M;i++){
                parent[i]=i;
            }

            for(int i=0;i<N;i++){
                st=new StringTokenizer(br.readLine());
        
                // x to y=z
                int x=Integer.parseInt(st.nextToken());
                int y=Integer.parseInt(st.nextToken());
                int z=Integer.parseInt(st.nextToken());
    
                graph[i][0]=x;
                graph[i][1]=y;
                graph[i][2]=z;
                maxWeight+=graph[i][2];
            }
    
            Arrays.sort(graph, (o1, o2)->o1[2]-o2[2]);
    
            System.out.println(maxWeight-kruskal());    
        }

        br.close();
    }
    public static int kruskal(){
        int totalWeight=0;

        for(int i=0;i<N;i++){
            if(find(graph[i][0])!=find(graph[i][1])){
                union(graph[i][0], graph[i][1]);
                totalWeight+=graph[i][2];
            }
        }
        return totalWeight;
    }
    public static void union(int x, int y){
        int xParent=find(x);
        int yParent=find(y);

        if(xParent<yParent){
            parent[yParent]=xParent;
        }else{
            parent[xParent]=yParent;
        }
    }
    public static int find(int x){
        if(parent[x]==x){
            return x;
        }else{
            return find(parent[x]);
        }
    }
}