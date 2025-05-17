package Code;

import java.util.*;
import java.io.*;

class BOJ_2576{
    static int NUMBER_COUNT=7;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int[] arr=new int[NUMBER_COUNT];
        for(int i=0;i<NUMBER_COUNT;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }

        int minimum=Integer.MAX_VALUE;
        int sum=0;
        for(int i=0;i<NUMBER_COUNT;i++){
            if(arr[i]%2!=0){
                minimum=Math.min(minimum, arr[i]);
                sum+=arr[i];
            }
        }

        if(minimum==Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(sum);
            System.out.println(minimum);
        }

        br.close();
    }
}