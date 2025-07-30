package Code;

import java.util.*;
import java.io.*;

class BOJ_11055{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=11055;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        int[] numbers=new int[N+1];
        int[] dp=new int[N+1];
        int result=0;
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=N;i++){
            numbers[i]=Integer.parseInt(st.nextToken());
            dp[i]=numbers[i];
            result=Math.max(result, numbers[i]);
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=i;j++){
                if(numbers[j]<numbers[i] & dp[i]<(dp[j]+numbers[i])){
                    dp[i]=(dp[j]+numbers[i]);
                }
            }
            result=Math.max(result, dp[i]);
        }
        System.out.println(result);

        br.close();
    }
}