package Code;

import java.util.*;
import java.io.*;

class BOJ_15652{
    static int N,M;
    static int[] result, numbers;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=15652;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N=inputs[0];
        M=inputs[1];
        
        numbers=new int[N];
        result=new int[M];

        for(int i=1;i<=N;i++){
            numbers[i-1]=i;
        }

        combination(0, 0);

        br.close();
    }
    // 중복조합
    public static void combination(int start, int depth){
        if(depth==M){
            System.out.println(Arrays.toString(result).replaceAll("[\\[\\],]",""));
            return;
        }

        for(int i=start;i<N;i++){
            result[depth]=numbers[i];
            combination(i, depth+1);
        }
    }
}