package Code;

import java.util.*;
import java.io.*;

class BOJ_1475{
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int[] cnt=new int[10];
        String[] inputs=br.readLine().split("");
        int[] numbers=new int[inputs.length];
        for(int i=0;i<inputs.length;i++){
            numbers[i]=Integer.valueOf(inputs[i]);
        }

        for(int i=0;i<numbers.length;i++){
            // 만약 inputs[i]가 6이나 9일 경우?
            if(numbers[i]==6 || numbers[i]==9){
                // 6, 9 수 카운트 같을 경우: 일단 6을 사용
                if(cnt[6]==cnt[9]){
                    cnt[6]+=1;
                }else if(cnt[6]>cnt[9]){ // 6>9 일 경우: 9를 사용
                    cnt[9]+=1;
                }else{ // 6<9 일 경우: 6을 사용
                    cnt[6]+=1;
                }
            }else{
                cnt[numbers[i]]+=1;
            }
        }
        
        // System.out.println(Arrays.toString(cnt));
        
        int result=-1;
        for(int i=0;i<cnt.length;i++){
            result=Math.max(cnt[i], result);
        }

        System.out.println(result);
        br.close();
    }
}