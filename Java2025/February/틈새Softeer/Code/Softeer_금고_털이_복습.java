package Code;

import java.util.*;
import java.io.*;

class MetalPractice{
    int weight;
    int value;

    MetalPractice(int weight, int value){
        this.weight=weight;
        this.value=value;
    }
}
public class Softeer_금고_털이_복습 {
    public static void main(String[] args) throws Exception {
        String filepath=System.getProperty("user.dir")+"/틈새Softeer/Input/";
        String probName="금고_털이";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int W=inputs[0];
        int N=inputs[1];

        PriorityQueue<MetalPractice> pq=new PriorityQueue<MetalPractice>(new Comparator<MetalPractice>(){
            @Override
            public int compare(MetalPractice m1, MetalPractice m2){
                return m2.value-m1.value;
            }
        });

        for(int i=0;i<N;i++){
            inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pq.offer(new MetalPractice(inputs[0], inputs[1]));
        }

        // Fractional Knapsack
        MetalPractice currentMetal=pq.poll();
        int currentWeight=0;
        int currentValue=0;
        int weightMax=W;
        while(!pq.isEmpty()){
            if((currentWeight+currentMetal.weight)<=weightMax){
                currentWeight+=currentMetal.weight;
                currentValue+=(currentMetal.value*currentMetal.weight);
                currentMetal=pq.poll();
            }else{
                break;
            }
        }

        int diff=(weightMax-currentWeight);
        if(diff>0){
            currentWeight=weightMax;
            currentValue+=currentMetal.value*diff;
        }

        System.out.println(currentValue);
        br.close();
    }
}
