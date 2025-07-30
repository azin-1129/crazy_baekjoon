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
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=N;i++){
            int value=Integer.parseInt(st.nextToken());
            numbers[i]=value;
            dp[i]=-1001;
        }

        int dpIdx=1;
        int originIdx=0;
        for(int i=1;i<=N;i++){
            System.out.println(Arrays.toString(dp));
            if(dp[dpIdx]<numbers[i]){
                if((originIdx+1)!=i){
                    dpIdx=1;
                }else{
                    dp[dpIdx++]=numbers[i];
                    originIdx=i;
                }
            }
        }
        System.out.println(Arrays.toString(dp));

        // System.out.println(result);
        br.close();
    }
}