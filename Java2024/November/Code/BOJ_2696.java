package Code;

import java.util.*;
import java.io.*;

class BOJ_2696{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2696;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringBuilder sb;

        int T=Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            System.out.println("Test Case:"+t);
            sb=new StringBuilder();

            int M=Integer.parseInt(br.readLine());

            int[] nums=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int maxHeapCnt=0;
            int minHeapCnt=0;

            PriorityQueue<Integer> maxHeap=new PriorityQueue<>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2){
                    return Integer.compare(o2, o1);
                }
            });

            PriorityQueue<Integer> minHeap=new PriorityQueue<>();

            int[] numsCopy=nums.clone();
            Arrays.sort(numsCopy);

            int middle=numsCopy[M/2]; // nums 배열의 중앙값
            int resultCnt=1; // 총 출력할 값 수

            sb.append(nums[0]+" "); // 처음 중앙값은 0번째 입력값
            System.out.println("초기 중앙값은 "+nums[0]+"입니다.");
            for(int i=1;i<M;i++){
                System.out.println("iteration no."+(i+1));
                int now=nums[i];
                if(now==middle){
                    continue;
                }

                if(now<middle){
                    maxHeap.offer(now);
                    maxHeapCnt+=1;
                }else{
                    minHeap.offer(now);
                    minHeapCnt+=1;
                }
                System.out.println("최대힙 상태:"+maxHeap);
                System.out.println("최소힙 상태:"+minHeap);
                System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

                if(i%2==0){
                    System.out.println("홀수 번째 입니다.");

                    if(maxHeapCnt>minHeapCnt){
                        sb.append(maxHeap.peek()+" ");
                        System.out.println(maxHeap.peek()+"추가");
                    }else{
                        sb.append(minHeap.peek()+" ");
                        System.out.println(minHeap.peek()+"추가");
                    }
                    resultCnt+=1;
                }
            }

            sb.insert(0, resultCnt+"\n");

            sb.deleteCharAt(sb.length()-1);

            System.out.println(sb);

        }
        br.close();
    }
}