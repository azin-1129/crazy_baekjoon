package Code;

import java.util.*;
import java.io.*;

class BOJ_1912{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1912;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        int[] numbers=new int[N+1];
        int[] dp=new int[N+1];
        int result=-1001;

        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=N;i++){
            int value=Integer.parseInt(st.nextToken());
            numbers[i]=value;
            result=Math.max(numbers[i], result);
        }
        
        dp[1]=numbers[1];
        for(int i=2;i<=N;i++){
            dp[i]=Math.max(dp[i-1]+numbers[i], numbers[i]);
            result=Math.max(result, dp[i]);
        }

        System.out.println(result);
        br.close();
    }
}