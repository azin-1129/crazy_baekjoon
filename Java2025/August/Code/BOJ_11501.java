package Code;

import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            int N=Integer.parseInt(br.readLine());
            Long[] stocks=new Long[N];
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            for(int i=0;i<N;i++){
                stocks[i]=Long.parseLong(st.nextToken());
            }
            
            Long result=0L;
            long maxVal=Long.MIN_VALUE;
            for(int i=N-1;i>=0;i--){
                if(maxVal<=stocks[i]){
                    maxVal=stocks[i];
                }else{
                    result+=maxVal-stocks[i];
                }
            }
            
            sb.append(result+"\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }
}
