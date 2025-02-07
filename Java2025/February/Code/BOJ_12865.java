package Code;

import java.io.*;
import java.util.*;

public class BOJ_12865 {
    public static void main(String[] args) throws Exception {
        String filepath=System.getProperty("user.dir")+"/Input/input";
        String probNum="12865";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probNum+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N=inputs[0];
        int K=inputs[1];

        int[][] obj=new int[N+1][2];
        int[][] knapsack=new int[N+1][K+1];
        for(int i=1;i<=N;i++){
            inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            obj[i]=inputs;
        }

        for(int k=1;k<=N;k++){
            for(int w=1;w<=K;w++){
                if(obj[k][0]>w){
                    knapsack[k][w]=knapsack[k-1][w];
                }else{
                    knapsack[k][w]=Math.max(knapsack[k-1][w], knapsack[k-1][w-obj[k][0]]+obj[k][1]);
                }
            }
        }

        System.out.println(knapsack[N][K]);
        br.close();
    }    
}
