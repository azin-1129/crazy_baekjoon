package Java_9월;

import java.io.*;
import java.util.*;

public class 시험감독 {
    public static void main(String[] args) throws Exception{
        String path=System.getProperty("user.dir")+"/Java_9월/";
        BufferedReader br=new BufferedReader(new FileReader(path+"input13458.txt"));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int[] A=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        st=new StringTokenizer(br.readLine());
        int B=Integer.parseInt(st.nextToken());
        int C=Integer.parseInt(st.nextToken());

        // System.out.println(N);
        // System.out.println(Arrays.toString(A));
        // System.out.println(B+", "+C);

        long answer=N;

        for(int i=0;i<A.length;i++){
            A[i]-=B;
            // System.out.println("총감독관 제외 인원:"+A[i]);

            if(A[i]<=0){
                // System.out.println("answer 모니터링:"+answer);
                continue;
            }

            if(A[i]%C!=0){
                answer+=(A[i]/C)+1;
            }else{
                answer+=(A[i]/C);
            }

            // System.out.println("answer 모니터링:"+answer);
        }

        System.out.println(answer);
        
        br.close();
    }
    
}
