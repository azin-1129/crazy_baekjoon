package Code;

import java.util.*;
import java.io.*;

class BOJ_9372{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        List<List<Integer>> graph;

        int bojNum=9372;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringBuilder sb=new StringBuilder();

        int T=Integer.parseInt(br.readLine());
        
        for(int t=0;t<T;t++){
            graph=new ArrayList<>();

            int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int N=inputs[0];
            int M=inputs[1];

            for(int n=0;n<=N;n++){
                graph.add(new ArrayList<>());
            }

            for(int m=0;m<M;m++){
                inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                graph.get(inputs[0]).add(inputs[1]);
                graph.get(inputs[1]).add(inputs[0]);
            }

            sb.append((N-1)+"\n");
        }

        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb);
        br.close();
    }
}