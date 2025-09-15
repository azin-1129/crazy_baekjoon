package Code;

import java.util.*;
import java.io.*;

class BOJ_2960{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2960;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        boolean[] isDeleted=new boolean[N+1];
        int count=0;
        for(int x=2;x<=N;x++){
            if(isDeleted[x]){
                continue;
            }
            for(int y=1;(x*y)<=N;y++){
                if(!isDeleted[(x*y)]){
                    isDeleted[(x*y)]=true;
                    count+=1;
                    if(count==K){
                        System.out.println((x*y));
                        break;
                    }
                }
            }
        }

        br.close();
    }
}