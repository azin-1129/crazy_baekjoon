package Code;

import java.util.*;
import java.io.*;

class BOJ_2720{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2720;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int[] coins={25, 10, 5, 1}; 
        int T=Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            int change=Integer.parseInt(br.readLine());

            StringBuilder sb=new StringBuilder();
            for(int c=0;c<4;c++){
                sb.append(change/coins[c]+" ");
                change%=coins[c];
            }
            System.out.println(sb);
        }
        br.close();
    }
}