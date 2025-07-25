package Code;

import java.util.*;
import java.io.*;

class BOJ_5648{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=5648;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;
        Queue<Long> q=new PriorityQueue<>();

        st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        int idx=0;
        while(true){
            while(st.hasMoreTokens()){
                String value=st.nextToken();
                String temp="";
                for(int i=value.length()-1;i>=0;i--){
                    temp+=value.charAt(i);
                }
                q.offer(Long.valueOf(temp));
                idx++;
            }
            if(idx>=N){
                break;
            }
            st=new StringTokenizer(br.readLine(), " ");
        }

        while(!q.isEmpty()){
            System.out.println(q.poll());
        }
        br.close();
    }
}