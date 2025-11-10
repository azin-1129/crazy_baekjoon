package Code;

import java.util.*;
import java.io.*;

class BOJ_1655 {
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 1655;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));

        // 좌 pq (내림차순)
        // 우 pq (오름차순)

        // int leftSize=0;
        // int rightSize=0;

        PriorityQueue<Integer> pqLeft=new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return Integer.compare(o2, o1);
            }
        });

        PriorityQueue<Integer> pqRight=new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return Integer.compare(o1, o2);
            }
        });
        int N=Integer.parseInt(br.readLine());
        // 현 카운트가 짝수라면, 좌pq peek()
        // 외에는 사이즈가 더 큰 q의 peek값을 출력한다.
        for(int i=0;i<N;i++){
            int number=Integer.parseInt(br.readLine());

            // 원소 개수 밸런싱(항상 pqLeft 원소 개수가 pqRight 이상)
            if(pqLeft.size()==pqRight.size()){
                pqLeft.offer(number);
            }else{
                pqRight.offer(number);
            }

            // 원소 크기 밸런싱
            if(!pqLeft.isEmpty() & !pqRight.isEmpty()){
                if(pqLeft.peek() > pqRight.peek()){
                    int leftVal=pqLeft.poll();
                    int rightVal=pqRight.poll();
                    pqRight.offer(leftVal);
                    pqLeft.offer(rightVal);
                }
            }

            System.out.println(pqLeft.peek());
        }

        br.close();
    }
}