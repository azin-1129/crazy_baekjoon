package Code;

import java.util.*;
import java.io.*;

class BOJ_1655 {
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 1655;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));

        // -10,000에 가까운 값을 좌 pq, (내림차순)
        // 10,000에 가까운 값들 우 pq, (오름차순)
        // 큐 밸런스를 맞춰야 하나? 음양만 따져서는 안될 듯
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

            // // 2개 까지는 그냥 queue에 삽입
            // if(i<2){
            //     pqLeft.offer(number);
            //     System.out.println("L:"+pqLeft);
            //     System.out.println("R:"+pqRight);
            //     System.out.println("mid:"+pqLeft.peek());
            //     continue;
            // }

            if(pqLeft.isEmpty()){
                if(!pqRight.isEmpty()){
                    if(number>pqRight.peek()){
                        pqRight.offer(number);
                    }else if(number<pqRight.peek()){
                        pqLeft.offer(number);
                    }
                }else{
                    pqLeft.offer(number);
                }
            }else if(pqRight.isEmpty()){
                if(!pqLeft.isEmpty()){
                    if(number<pqLeft.peek()){
                        pqLeft.offer(number);
                    }else if(number>pqLeft.peek()){
                        pqRight.offer(number);
                    }
                }else{
                    pqLeft.offer(number);
                }
            }else{
                if(number<pqLeft.peek()){
                    pqLeft.offer(number);
                }else if(number>pqRight.peek()){
                    pqRight.offer(number);
                }else{
                    pqLeft.offer(number);
                }
            }

            // i(원소 개수)가 짝수일 때, 밸런싱 가능
            if((i+1)%2!=0){
                System.out.println(pqLeft.peek());
                continue;
            }

            int leftSize=pqLeft.size();
            int rightSize=pqRight.size();
            if(leftSize<rightSize){
                while(pqLeft.size()!=pqRight.size()){
                    pqLeft.offer(pqRight.poll());
                }
            }else if(leftSize>rightSize){
                while(pqLeft.size()!=pqRight.size()){
                    pqRight.offer(pqLeft.poll());
                }
            }

            // System.out.println("L:"+pqLeft);
            // System.out.println("R:"+pqRight);
            System.out.println(pqLeft.peek());
        }

        br.close();
    }
}