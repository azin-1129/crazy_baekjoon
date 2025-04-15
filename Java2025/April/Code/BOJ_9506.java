package Code;

import java.util.*;
import java.io.*;

class BOJ_9506{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=9506;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        while(true){
            int N=Integer.parseInt(br.readLine());

            if(N==-1){
                break;
            }

            List<Integer> nums=new ArrayList<>();
            int sum=0;
            for(int i=1;i<N;i++){
                if(N%i==0){
                    sum+=i;
                    nums.add(i);
                }
            }

            if(sum!=N){
                System.out.println(N+" is NOT perfect.");
            }else{
                StringBuilder sb=new StringBuilder();
                sb.append(N+" =");
    
                for(int n:nums){
                    sb.append(" "+n+" +");
                }
                sb.deleteCharAt(sb.length()-1);
                System.out.println(sb);
            }   
        }

        br.close();
    }
}