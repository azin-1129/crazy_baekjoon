package Code;

import java.io.*;
import java.util.*;

public class BOJ_2631 {
    public static void main(String[] args) throws Exception {
        String path=System.getProperty("user.dir")+"\\Java2024\\October\\";

        BufferedReader br=new BufferedReader(new FileReader(path+"input2631.txt"));

        int N=Integer.parseInt(br.readLine());

        int[] nums=new int[N];
        int[] lisDP=new int[N];

        for(int i=0;i<N;i++){
            nums[i]=Integer.parseInt(br.readLine());
        }

        for(int current=0;current<N;current++){
            lisDP[current]=1;

            for(int before=0;before<current;before++){
                if(nums[current]>nums[before]){
                    lisDP[current]=Math.max(lisDP[before]+1, lisDP[current]);
                }
            }
        }

        // System.out.println("lisDP 상태:"+Arrays.toString(lisDP));
        System.out.println(N-Arrays.stream(lisDP).max().getAsInt());
    }
}
