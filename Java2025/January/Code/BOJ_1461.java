package Code;

import java.util.*;
import java.io.*;

class BOJ_1461{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1461;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        // 0부터 시작해 책을 원래 위치에 놔둘 경우 최소 걷는 걸음 수

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N=inputs[0];
        int M=inputs[1];

        int[] bookPosition=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(bookPosition);
        
        System.out.println(Arrays.toString(bookPosition));

        // pivot(0 위치) 탐색
        int pivot=Integer.MAX_VALUE;

        for(int i=0;i<N;i++){
            if(bookPosition[i]>0){
                pivot=i;
                break;
            }
        }

        System.out.println(pivot);
        // pivot-1까지 음수, pivot부터 N-1까지 양수. position에는 0 값이 없다.

        int result=0;

        if(pivot==Integer.MAX_VALUE){ // 양수 없음
            // 음수 파트 처리
            for(int i=0;i<N;i+=M){
                result+=(Math.abs(bookPosition[i])*2);
            }
        } else if(pivot==0){ // 음수 없음
            // 양수 파트 처리
            for(int i=N-1;i>=0;i-=M){
                result+=(bookPosition[i]*2);
            }
        }else{
            // 음수 파트 처리
            for(int i=0;i<pivot;i+=M){
                result+=(Math.abs(bookPosition[i])*2);
                System.out.println(result);
            }
            // 양수 파트 처리
            for(int i=N-1;i>=pivot;i-=M){
                result+=(bookPosition[i]*2);
                System.out.println(result);
            }
        }

        result-=Math.max(Math.abs(bookPosition[0]), Math.abs(bookPosition[N-1]));

        System.out.println(result);
        br.close();
    }
}