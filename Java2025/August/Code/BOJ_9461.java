package Code;

import java.util.*;
import java.io.*;

class BOJ_9461{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=9461;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        int[] temp={1, 1, 1, 2, 2, 3, 4, 5};
        int[] dp=new int[100];
        for(int i=0;i<temp.length;i++){
            dp[i]=Integer.valueOf(temp[i]);
        }

        // System.out.println(Arrays.toString(dp));
        int maximum=7;
        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            int number=Integer.parseInt(br.readLine())-1;
            if(maximum<number){ // 연산 필요
                for(int j=maximum+1;j<=number;j++){
                    dp[j]=dp[j-1]+dp[j-5];
                }
                // System.out.println(Arrays.toString(dp));
                maximum=number;
                System.out.println(dp[number]);
            }else{
                System.out.println(dp[number]);
            }
        }

        br.close();
    }
}