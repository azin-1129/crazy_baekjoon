package Code;

import java.util.*;
import java.io.*;

class BOJ_2910{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2910;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;
        
        st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        long C=Long.parseLong(st.nextToken());

        List<Long> numbers=new ArrayList<>();
        int[] count=new int[N];
        PriorityQueue<Long> orderList=new PriorityQueue<>(
            new Comparator<Long>(){
                @Override
                public int compare(Long o1, Long o2){
                    if(count[numbers.indexOf(o1)]==count[numbers.indexOf(o2)]){
                        return Integer.compare(numbers.indexOf(o1), numbers.indexOf(o2));
                    }
                    return -Integer.compare(count[numbers.indexOf(o1)], count[numbers.indexOf(o2)]);
                }
            }
        );

        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++){
            Long value=Long.parseLong(st.nextToken());

            if(!numbers.contains(value)){
                numbers.add(value);
            }
            count[numbers.indexOf(value)]+=1;
        }

        for(Long num: numbers){
            orderList.offer(num);
        }
        
        StringBuilder sb=new StringBuilder();
        while(!orderList.isEmpty()){
            Long value=orderList.poll();

            for(int j=0;j<count[numbers.indexOf(value)];j++){
                sb.append(value+" ");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
}