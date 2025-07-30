package Code;

import java.util.*;
import java.io.*;

class BOJ_2193{
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        int N=Integer.parseInt(br.readLine());
        if(N==1){
            System.out.println(1);
        }else if(N==2){
            System.out.println(1);
        }else{
            long[] dp=new long[N+1];
            dp[1]=1;
            dp[2]=1;
            
            for(int i=3;i<=N;i++){
                dp[i]=dp[i-1]+dp[i-2];
            }
        
            System.out.println(dp[N]);
        }
        
        br.close();
    }
}