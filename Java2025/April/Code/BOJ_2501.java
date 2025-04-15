package Code;

import java.util.*;
import java.io.*;

class BOJ_2501{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2501;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());
        
        boolean flag=false;
        int count=0;
        for(int i=1;i<N;i++){
            if(N%i==0){
                count+=1;
                if(count==K){
                    System.out.println(i);
                    flag=true;
                    break;
                }
            }
        }

        if(!flag){
            System.out.println(0);
        }

        br.close();
    }
}