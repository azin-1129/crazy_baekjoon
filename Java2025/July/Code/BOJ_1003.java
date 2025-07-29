package Code;

import java.util.*;
import java.io.*;

class BOJ_1003{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1003;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringBuilder sb=new StringBuilder();

        int T=Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            int N=Integer.parseInt(br.readLine());

            // dp[0]=0, dp[1]=1, dp[k]=dp[k-1]+dp[k-2];
            // if n==0: return 0
            // if n==1: return 1
            // else: return dp[n]
            // fib(N) 호출 시, 0과 1이 각각 몇 번 출력되는지?
            int zeroCount=0;
            int oneCount=0;
            if(N==0){
                zeroCount+=1;
                sb.append(zeroCount+" "+oneCount+"\n");
            }else if(N==1){
                oneCount+=1;
                sb.append(zeroCount+" "+oneCount+"\n");
            }else{
                int[] dp=new int[N+1]; // 1-index
                dp[0]=0;
                dp[1]=1;
                for(int i=2;i<=N;i++){
                    dp[i]=dp[i-1]+dp[i-2];
                }
                sb.append(dp[N-1]+" "+dp[N]+"\n");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
}