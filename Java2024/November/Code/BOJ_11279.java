package Code;

import java.util.*;
import java.io.*;

class BOJ_11279{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=11279;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringBuilder sb=new StringBuilder();

        PriorityQueue<Integer> pq=new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return Integer.compare(o2, o1);
            }
        });

        int N=Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            int input=Integer.parseInt(br.readLine());

            if(input==0){
                if(!pq.isEmpty()){
                    sb.append(pq.poll()+"\n");
                }else{
                    sb.append("0\n");
                }
            }else{
                pq.offer(input);
            }
        }

        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb);
        br.close();
    }
}