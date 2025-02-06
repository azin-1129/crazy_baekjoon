package Code;

import java.io.*;
import java.util.*;

public class BOJ_2559 {
    public static void main(String[] args) throws Exception {
        String filepath=System.getProperty("user.dir")+"/Input/input";
        String probNum="2559";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probNum+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N=inputs[0];
        int K=inputs[1];

        int[] numbers=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i=1;i<N;i++){
            numbers[i]+=numbers[i-1];
        }
        
        int res=numbers[K-1];
        for(int k=(K-1);k<N;k++){
            if(k==(K-1)){
                continue;
            }
            res=Math.max(numbers[k]-numbers[k-K], res);
        }
        System.out.println(res);

        br.close();
    }   
}
