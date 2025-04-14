package Code;

import java.util.*;
import java.io.*;

class BOJ_11005{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=11005;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        String N=st.nextToken();
        int NLength=N.length();
        int B=Integer.parseInt(st.nextToken());

        System.out.println((char)35);
        int num=Integer.valueOf(N);
        for(int i=NLength-1;i>=0;i--){
            double val=num/(Math.pow(B,i));
            if(val>=10){
                System.out.println((char)(val+55));
            }else if(val>0){
                System.out.println(val);
            }
            num%=Math.pow(B,i);
        }
        // 10^0*5 + 10^1*7 + ...
        // to 36^0*? + 36^1*? + ...
        // 36^7

        // System.out.println(res);
        br.close();
    }
}