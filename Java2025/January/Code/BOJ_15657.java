package Code;

import java.util.*;
import java.io.*;

class BOJ_15657{
    static int N,M;
    static int[] numbers, result;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=15657;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N=inputs[0];
        M=inputs[1];

        numbers=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result=new int[M];

        Arrays.sort(numbers);

        combination(0,0);

        br.close();
    }
    // 중복 조합
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