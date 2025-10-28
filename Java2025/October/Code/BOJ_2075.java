package Code;

import java.util.*;
import java.io.*;

class BOJ_2075{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2075;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        
        PriorityQueue<Long> pq=new PriorityQueue<>(new Comparator<Long>(){
            @Override
            public int compare(Long o1, Long o2){
                return Long.compare(o2, o1);
            }
        });
        
        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            while(st.hasMoreTokens()){
                pq.offer(Long.parseLong(st.nextToken()));
            }
        }

        for(int i=0;i<N-1;i++){
            pq.poll();
        }

        System.out.println(pq.poll());

        br.close();
    }
}