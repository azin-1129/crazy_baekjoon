package Code;

import java.io.*;
import java.util.*;

public class Softeer_연탄의_크기 {
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"/Input/";
        String probName="연탄의_크기";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt"));
        int N=Integer.parseInt(br.readLine());
        int[] briquetR=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int maxCount=0;
        for(int heaterR=2;heaterR<=100;heaterR++){
            int count=0;
            for(int i=0;i<N;i++){
                if(briquetR[i]%heaterR==0){
                    count+=1;
                }
            }
            if(maxCount<count){
                maxCount=count;
            }
        }

        System.out.println(maxCount);

        br.close();
    }
}
