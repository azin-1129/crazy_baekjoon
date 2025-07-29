package Code;

import java.util.*;
import java.io.*;

class BOJ_11727{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=11727;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());

        if(N==1){
            System.out.println(1);
        }else if(N==2){
            System.out.println(3);
        }else{
            int[] dp=new int[N+1];
            dp[1]=1;
            dp[2]=3;

            for(int i=3;i<=N;i++){
                dp[i]=(dp[i-1]+2*dp[i-2]%10007);
            }

            System.out.println(dp[N]);
        }
        br.close();
    }
}