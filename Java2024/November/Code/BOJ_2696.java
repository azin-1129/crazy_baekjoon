package Code;

import java.util.*;
import java.io.*;

class BOJ_2696{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2696;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringBuilder sb=new StringBuilder();
        StringTokenizer st;

        int T=Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            // System.out.println("Test Case:"+t);

            int M=Integer.parseInt(br.readLine());

            int[] nums=new int[M];
            

            int cnt=0;

            int hasMore= (M%10)==0 ? 0 : 1;

            for(int i=0;i<M/10+hasMore;i++){
                st=new StringTokenizer(br.readLine());

                while(st.hasMoreTokens()){
                    nums[cnt++]=Integer.parseInt(st.nextToken());
                }
            }

            int maxHeapCnt=0;
            int minHeapCnt=0;

            PriorityQueue<Integer> maxHeap=new PriorityQueue<>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2){
                    return Integer.compare(o2, o1);
                }
            });

            PriorityQueue<Integer> minHeap=new PriorityQueue<>();

            int resultCnt=(M+1)/2; // 총 출력할 값 수

            sb.append(resultCnt+"\n"); // 수열에서 중앙값의 개수

            for(int i=0;i<M;i++){
                if(i>10 && i%10==0){
                    sb.deleteCharAt(sb.length()-1);
                    sb.append("\n");
                }

                // System.out.println("iteration no."+(i+1));
                
                int now=nums[i];

                if(maxHeapCnt==minHeapCnt){
                    maxHeap.offer(now);
                    maxHeapCnt+=1;
                }else{
                    minHeap.offer(now);
                    minHeapCnt+=1;
                }

                // maxHeap이 중앙값 이하 숫자만 갖도록 보정
                if(!minHeap.isEmpty()){
                    if(maxHeap.peek()>minHeap.peek()){
                        int t1=maxHeap.poll();
                        int t2=minHeap.poll();

                        maxHeap.offer(t2);
                        minHeap.offer(t1);
                    }
                }

                // System.out.println("최대힙 상태:"+maxHeap);
                // System.out.println("최소힙 상태:"+minHeap);

                if(i%2==0){
                    // System.out.println("홀수 번째 입니다.");

                    sb.append(maxHeap.peek()+" ");
                }

                // System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
        }

        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb);
        br.close();
    }
}