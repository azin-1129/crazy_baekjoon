package Code;
import java.util.*;
import java.io.*;

public class BOJ_15665 {
    static int N,M;
    static int[] numbers, result;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws Exception{
        String path=System.getProperty("user.dir");
        BufferedReader br=new BufferedReader(new FileReader(path+"\\input15665.txt"));
    
        String[] inputs=br.readLine().split(" ");

        N=Integer.parseInt(inputs[0]);
        M=Integer.parseInt(inputs[1]);

        result=new int[M];
        numbers=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(numbers);

        duplicatedPermutation(0);

        System.out.print(sb.toString());

        br.close();
    }

    static void duplicatedPermutation(int depth){
        if(depth==M){
            for(int i=0;i<M;i++){
                sb.append(result[i]);
                if(i!=(M-1)){
                    sb.append(" ");
                }else{
                    sb.append("\n");
                }
            }
            return;
        }

        int prev=-1; // depth마다 다른 prev

        for(int i=0;i<N;i++){
            if(prev!=numbers[i]){
                prev=numbers[i];

                result[depth]=prev;

                duplicatedPermutation(depth+1);
            }
        }
    }
}
