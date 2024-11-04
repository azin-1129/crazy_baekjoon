package Code;

import java.util.*;
import java.io.*;

class BOJ_13335{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=13335;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));


        Queue<Integer> trucks=new LinkedList<>();
        Queue<Integer> timeLine=new LinkedList<>();

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N=inputs[0];
        int W=inputs[1];
        int L=inputs[2];
        int result=0;
        int bridgeWeight=0;

        inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i=0;i<inputs.length;i++){
            trucks.offer(inputs[i]);
        }

        for(int w=0;w<W;w++){
            timeLine.offer(0); // 첫 트럭부터 시간 재기
        }

        while(!timeLine.isEmpty()){
            result+=1;
            bridgeWeight-=timeLine.poll();

            if(!trucks.isEmpty()){  // 남은 트럭이 있을 때만
                if(trucks.peek()+bridgeWeight<=L){
                    bridgeWeight+=trucks.peek();
                    timeLine.offer(trucks.poll());
                }else{ // 대기
                    timeLine.offer(0);
                }
            }
        }

        System.out.println(result);

        br.close();
    }
}