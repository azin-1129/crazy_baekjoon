package Code;

import java.util.*;
import java.io.*;

class BOJ_1912{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1912;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int result=-1001;
        int N=Integer.parseInt(br.readLine());
        int[] numbers=new int[N+1];
        int[][] dp=new int[N+1][N+1];
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++){
            int value=Integer.parseInt(st.nextToken());
            if(result<value){
                result=value;
            }
            numbers[i]=value;
            dp[i][i]=value;
        }

        for(int x=1;x<=N;x++){
            for(int y=(x+1);y<=N;y++){
                dp[x][y]=dp[x][y-1]+numbers[y];

                result=Math.max(dp[x][y], result);
            }
        }

        System.out.println(result);
        br.close();
    }
}