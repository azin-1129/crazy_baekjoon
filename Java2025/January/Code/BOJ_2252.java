package Code;

import java.util.*;
import java.io.*;

class BOJ_2252{
    static int[] degree;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2252;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringBuilder sb=new StringBuilder();

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N=inputs[0];
        List<List<Integer>> graph=new ArrayList<>();

        for(int i=0;i<N+1;i++){
            graph.add(new ArrayList<>());
        }

        int M=inputs[1];

        degree=new int[N+1];

        for(int i=0;i<M;i++){
            inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            degree[inputs[1]]+=1;
            graph.get(inputs[0]).add(inputs[1]);
        }

        // 위상정렬
        Queue<Integer> q=new LinkedList<>();

        for(int i=1;i<=N;i++){
            if(degree[i]==0){
                q.offer(i);
            }
        }

        while(!q.isEmpty()){
            int nodeNo=q.poll();

            sb.append(nodeNo+" ");

            List<Integer> nearBy=graph.get(nodeNo);

            for(int nearByNode : nearBy){
                degree[nearByNode]-=1;
                if(degree[nearByNode]==0){
                    q.offer(nearByNode);
                }
            }
        }

        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb);
        
        br.close();
    }
}