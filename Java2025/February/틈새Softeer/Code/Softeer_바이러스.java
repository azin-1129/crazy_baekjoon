package Code;

import java.io.*;
import java.util.*;

public class Softeer_바이러스 {
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"/Input/";
        String probName="바이러스";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long K=inputs[0];
        long P=inputs[1];
        long N=inputs[2];

        long temp=Long.valueOf(P);

        for(int i=1;i<N;i++){
            temp=(temp*P)%1000000007;
        }

        System.out.println((K*temp)%1000000007);
        br.close();
    }
}
