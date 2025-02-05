package Code;

import java.io.*;
import java.util.*;

class Metal{
    int weight;
    int value;

    Metal(int weight, int value){
        this.weight=weight;
        this.value=value;
    }

    @Override
    public String toString(){
        return "weight: "+this.weight+", value: "+this.value;
    }
}
public class Softeer_금고_털이 {
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"/Input/";
        String probName="금고_털이";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int knapsackWeightLimit=inputs[0];
        int N=inputs[1];

        PriorityQueue<Metal> pq=new PriorityQueue<Metal>(new Comparator<Metal>(){
            @Override
            public int compare(Metal m1, Metal m2){
                return m2.value-m1.value;
            }
        });

        for(int i=0;i<N;i++){
            inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pq.offer(new Metal(inputs[0], inputs[1]));
        }

        // Fractional Knapsack
        int totalKnapsackWeight=0;
        int totalKnapsackValue=0;

        Metal currentMetal=pq.poll();

        while(!pq.isEmpty()){
            if((totalKnapsackWeight+currentMetal.weight)<=knapsackWeightLimit){
                totalKnapsackWeight+=currentMetal.weight;
                totalKnapsackValue+=currentMetal.value*currentMetal.weight;

                currentMetal=pq.poll();
            }else{
                break;
            }
        }

        if((knapsackWeightLimit-totalKnapsackWeight)>0){
            totalKnapsackValue+=(currentMetal.value*(knapsackWeightLimit-totalKnapsackWeight));
            totalKnapsackWeight=knapsackWeightLimit;
        }

        System.out.println(totalKnapsackValue);

        br.close();
    }
}
