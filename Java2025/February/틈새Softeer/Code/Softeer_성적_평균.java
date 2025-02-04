package Code;

import java.util.*;
import java.io.*;

public class Softeer_성적_평균 {
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"/Input/";
        String probName="성적_평균";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt")); 
        StringBuilder sb=new StringBuilder();

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N=inputs[0];
        int K=inputs[1];

        int[] scores=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i=1;i<scores.length;i++){
            scores[i]+=scores[i-1];
        }

        for(int i=0;i<K;i++){
            inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int A=inputs[0]-1;
            int B=inputs[1]-1;
            
            if(A==0){
                sb.append(String.format("%.2f",(Math.round(scores[B]/(double)(B+1)*100)/100.0))+"\n");
            }else{
                sb.append(String.format("%.2f",(Math.round((scores[B]-scores[A-1])/(double)((B+1)-A)*100)/100.0))+"\n");
            }
        }

        sb.deleteCharAt(sb.length()-1);
        
        System.out.println(sb);
        br.close();
    }    
}
