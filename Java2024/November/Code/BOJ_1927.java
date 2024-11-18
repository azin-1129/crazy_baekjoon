package Code;

import java.util.*;
import java.io.*;

class BOJ_1927{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1927;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        PriorityQueue<Integer> pq=new PriorityQueue<>();

        int N=Integer.parseInt(br.readLine());

        for(int n=0;n<N;n++){
            int command=Integer.parseInt(br.readLine());

            if(command==0){
                if(!pq.isEmpty()){
                    System.out.println(pq.poll());
                }else{
                    System.out.println(0);
                }
            }else{
                pq.offer(command);
            }
        }
        br.close();
    }
}