package Code;

import java.io.*;
import java.util.*;

public class BOJ_1697 {
    static int N,K;
    static int result=Integer.MAX_VALUE;
    static int[] table;
    public static void main(String[] args) throws Exception {
        String path=System.getProperty("user.dir");
        BufferedReader br=new BufferedReader(new FileReader(path+"\\input1697.txt"));

        String[] inputs=br.readLine().split(" ");
        N=Integer.parseInt(inputs[0]);
        K=Integer.parseInt(inputs[1]);

        if(N==K){
            System.out.println("0");
        }else{
            table=new int[K*2+1];

            backTracking(N-1, 1);
            backTracking(N+1, 1);
            backTracking(N*2, 1);

            System.out.println(result);
        }
        br.close();
    }

    static void backTracking(int start, int second){
        // System.out.println("start:"+start+", second:"+second);
        if(second>result){
            return;
        }

        if(start<0 || start>K){
            return;
        }

        if(start==K){ // 도착한거임
            result=Math.min(result, second);
            // System.out.println("도착했으니 갱신할게요.");
            return;
        }
        
        if(table[start]<=second & table[start]!=0){ // 더 걸리거나 동일. 안 감
            return;
        }else{ // 더 짧게 걸리거나 안 가봄
            table[start]=second;

            backTracking(start-1, second+1);
            backTracking(start+1, second+1);
            backTracking(start*2, second+1);
        }
    }
}
