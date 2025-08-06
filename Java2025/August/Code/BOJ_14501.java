package Code;

import java.util.*;
import java.io.*;

class BOJ_14501{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=14501;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        int[][] reserv=new int[N][2];
        int[] dp=new int[N+1];

        StringTokenizer st;
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine(), " ");
            reserv[i][0]=Integer.parseInt(st.nextToken());
            reserv[i][1]=Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++){
            for(int j=i+reserv[i][0];j<=N;j++){
                if(dp[j]<(dp[i]+reserv[i][1])){
                    dp[j]=dp[i]+reserv[i][1];
                }
            }
        }
        System.out.println(dp[N]);
        br.close();
    }
}