package Code;

import java.util.*;
import java.io.*;

class BOJ_2587{
    static int NUMBER_COUNT=5;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int[] arr=new int[NUMBER_COUNT];

        int sum=0;
        for(int i=0;i<NUMBER_COUNT;i++){
            arr[i]=Integer.parseInt(br.readLine());
            sum+=arr[i];
        }

        Arrays.sort(arr);
        
        System.out.println(sum/NUMBER_COUNT);
        System.out.println(arr[NUMBER_COUNT/2]);
        br.close();
    }
}